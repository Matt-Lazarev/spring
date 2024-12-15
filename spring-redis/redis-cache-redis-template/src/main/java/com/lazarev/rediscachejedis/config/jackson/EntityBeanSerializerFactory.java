package com.lazarev.rediscachejedis.config.jackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;

import java.util.Collection;

public class EntityBeanSerializerFactory extends BeanSerializerFactory {
    public EntityBeanSerializerFactory() {
        this(null);
    }

    public EntityBeanSerializerFactory(SerializerFactoryConfig config) {
        super(config);
    }

    @Override
    public JsonSerializer<Object> findBeanOrAddOnSerializer(SerializerProvider prov, JavaType type,
                                                            BeanDescription beanDesc, boolean staticTyping)
                                                            throws JsonMappingException {
        JsonSerializer<Object> ser = super.findBeanOrAddOnSerializer(prov, type, beanDesc, staticTyping);
        ser.properties().forEachRemaining(p -> {
            if(Collection.class.isAssignableFrom(p.getType().getRawClass()) && p instanceof BeanPropertyWriter bp){
                bp.assignSerializer(new StdDelegatingSerializer(new PersistentCollectionConverter()));
            }
        });
        return ser;
    }
}
