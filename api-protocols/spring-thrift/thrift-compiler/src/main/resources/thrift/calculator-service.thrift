namespace java com.lazarev.thrift.service.calculator

exception CalculatorException {
    1: i32 code,
    2: string description
}

struct CalculatorResult {
    1: string operation,
    2: optional double result
    3: optional list <i8> arrayResult
}

service CalculatorService {

    CalculatorResult add(1:i32 n1, 2:i32 n2) throws (1:CalculatorException e),

    CalculatorResult div(1:i32 n1, 2:i32 n2) throws (1:CalculatorException e),

    CalculatorResult digits(1:i32 num) throws (1:CalculatorException e)
}