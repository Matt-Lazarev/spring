package com.example.springtest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ParamTest {

    Calculator calculator = new Calculator();

    @ParameterizedTest(name = "square test with parameter = {0}")
    @ValueSource(ints = {1, 2, 3, 4})
    public void square(int parameter){
        assertEquals(parameter*parameter, calculator.square(parameter));
    }

    @ParameterizedTest(name = "square test with null parameter")
    @NullSource
    public void square_withNullParameter(Integer parameter){
        assertThrows(NullPointerException.class, ()->calculator.square(parameter));
    }

    @ParameterizedTest(name = "square test with method source parameter = {0}")
    @MethodSource("squareArgumentProvider")
    public void square_withMethodSource(Integer parameter){
        assertEquals(parameter*parameter, calculator.square(parameter));
    }

    private static Stream<Integer> squareArgumentProvider(){
        return Stream.of(1,2,3,4,5);
    }

    @ParameterizedTest(name = "add test with method source parameters = {0}, {1}")
    @MethodSource("numbersArgumentProvider")
    public void square_withMethodSource(Integer p1, Integer p2){
        assertEquals(p1+p2, calculator.add(p1, p2));
    }

    private static Stream<Arguments> numbersArgumentProvider() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 3)
        );
    }


    @ParameterizedTest(name = "multiple arguments provider test")
    @MethodSource("objectsArgumentProvider")
    public void test_withObjects(LocalDate p1, Calculator p2){
        assertNotNull(p1);
        assertNotNull(p2);
    }

    private static Stream<Arguments> objectsArgumentProvider() {
        return Stream.of(
                Arguments.of(LocalDate.now(), new Calculator()),
                Arguments.of(LocalDate.now(), new Calculator())
        );
    }


}

class Calculator{
    public int square(int number){
        return number * number;
    }

    public int add(int n1, int n2){
        return n1 + n2;
    }
}
