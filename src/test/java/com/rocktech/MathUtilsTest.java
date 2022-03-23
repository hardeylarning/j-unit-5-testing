package com.rocktech;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** TestInstance
 * TestInstance annotation with TestInstance.Lifecycle.PER_CLASS
 * permits singleton of creating only one instance for the class
*/

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathUtilsTest {

    MathUtils mathUtils;

    // beforeAll must be static in order to run because it would run before instance is created
    @BeforeAll
    static void beforeAllInit(){
        System.out.println("Before all methods");
    }

    // execute this before any method runs
    @BeforeEach
    void init(){
        mathUtils = new MathUtils();
    }

    @AfterEach
    void cleanup(){
        System.out.println("cleaning up...");
    }

    @Test
    public void testAdd(){
        int expected = 10;
        int actual = mathUtils.add(-1, 11);
        assertEquals(expected, actual, "The add method should add 2 numbers");
    }

    @Test
    public void divideTest(){
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "Divide by zero should throw an error");
    }

    @Test
    public void testComputeCircleRadius(){
        assertEquals(314.1592653589793, mathUtils.computerCircleArea(10), "should return right circle area");
    }
}
