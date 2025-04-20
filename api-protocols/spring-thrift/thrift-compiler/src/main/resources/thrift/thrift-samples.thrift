namespace java com.lazarev.thrift.service.samples

// -------- oneway --------

struct TMessage {
    1: string text = "some text";
}

service OneWayService {
    oneway void nowait(TMessage message);
}

// -------- enum --------

enum TPaymentChoice {
    UNSPECIFIED, CREDIT_CARD, CASH, CRYPTO;
}

// -------- union --------

union TPaymentType {
    1: string creditCard;
    2: string cash;
    3: string crypto;
}

struct TPaymentRequest {
    1: string id;
    2: TPaymentType paymentType;
}

// -------- optional & default --------

struct Person {
    1: string name;
//    2: optional string nickname;
    3: i32 value;
    4: bool agreement;
}

service TPersonInfo {
    void sendInfo(1: Person person);
}