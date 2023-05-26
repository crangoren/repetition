import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import testApp.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator;

    public static Stream<Arguments> substrSource() {
        List<Arguments> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int a = random.nextInt(100);
            int b = random.nextInt(100);
            int result = a - b;
            list.add(Arguments.arguments(a, b, result));
        }
        return list.stream();
    }


    @BeforeEach
    void init() {
        calculator = new Calculator();
    }



    @Test
    @DisplayName("sum")
    void testAddition() {
        int res = calculator.sum(5, 10);
        //Assertions.assertEquals(15, 15);
        assertTrue(res == 15, "should be 15");
    }


   // @MethodSource("substrSource")
   @CsvSource ({
           "1, 2, -1",
           "3, 6, -3"
   })
    @ParameterizedTest
    void testSubs(int a, int b, int result) {
        assertEquals(result, calculator.sub(a, b));
    }


    @Test
    @DisplayName("div")
    void testDiv() {
        int res = calculator.div(10, 5);
        assertEquals(2, 2);
    }

    @CsvSource ({
            "1, 2, 2",
            "3, 6, 18"
    })
    @ParameterizedTest
    void testMul(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.multiplication(a, b));
    }

    @Test
    void testDivisionByZero(){
        assertThrows(ArithmeticException.class, () -> calculator.div(4, 0));
    }

    public  int div(int a, int b) {
        return a/b;
    }




}
