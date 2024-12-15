package com.lazarev.kafkabasics;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.KafkaHeaderMapper;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class KafkaBasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaBasicsApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate){
		return args -> {

//			for(int i=0; i<50; i++){
//				String key = i % 3 + "0".repeat(2);
//				String data = i + " with key: " + key;
//				kafkaTemplate.send("t_multipart", key, data);
//			}
//
//			kafkaTemplate.send("first", 1, 12434L, "100", "Test message")
//					.addCallback(new KafkaSendCallback<>(){
//
//						@Override
//						public void onSuccess(SendResult<String, String> result) {
//							handleRecordMetadata(result.getRecordMetadata());
//							handleProducerRecord(result.getProducerRecord());
//						}
//
//						@Override
//						public void onFailure(KafkaProducerException ex) {
//							ProducerRecord<String, String> failedProducerRecord = ex.getFailedProducerRecord();
//						}
//					});


			Properties properties = new Properties();
			properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			AdminClient adminClient = AdminClient.create(properties);
			ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
			listTopicsOptions.listInternal(true);

			//adminClient.deleteTopics(List.of("first"));
			System.out.println("topics:" + adminClient.listTopics(listTopicsOptions).names().get());
		};
	}

	void handleRecordMetadata(RecordMetadata recordMetadata){
		System.out.println("RecordMetadata timestamp: " + recordMetadata.timestamp());
		System.out.println("RecordMetadata topic: " + recordMetadata.topic());
		System.out.println("RecordMetadata partition: " + recordMetadata.partition());
		System.out.println("RecordMetadata offset: " + recordMetadata.offset());
		System.out.println("RecordMetadata key-size: " + recordMetadata.serializedKeySize());
		System.out.println("RecordMetadata value-size: " + recordMetadata.serializedValueSize());
	}

	<K, V> void handleProducerRecord(ProducerRecord<K, V> producerRecord){
		System.out.println("ProducerRecord timestamp: " + producerRecord.timestamp());
		System.out.println("ProducerRecord topic: " + producerRecord.topic());
		System.out.println("ProducerRecord partition: " + producerRecord.partition());
		System.out.println("ProducerRecord key: " + producerRecord.key());
		System.out.println("ProducerRecord value: " + producerRecord.value());
		System.out.println("ProducerRecord headers: " + producerRecord.headers());
	}

	Message<String> createMessage(){
		return MessageBuilder
				.withPayload("value")
				.setHeader(KafkaHeaders.TOPIC, "first")
				.setHeader(KafkaHeaders.MESSAGE_KEY, "999")
				.setHeader(KafkaHeaders.PARTITION_ID, 0)
				.setHeader("X-Custom-Header", "header-value")
				.build();
	}

	ProducerRecord<String, String> createProducerRecord(){
		return new ProducerRecord<>(
				"first", 1, 1000L,
				"key", "value");
	}

}
