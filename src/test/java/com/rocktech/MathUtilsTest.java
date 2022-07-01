package com.rocktech;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** TestInstance.
 * TestInstance annotation with TestInstance.Lifecycle.PER_CLASS,
 * permits singleton by creating only one instance for the class,
 * but uses TestInstance.Lifecycle.PER_METHOD by default
*/

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathUtilsTest {

    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;

    // beforeAll must be static in order to run because it would run before instance is created
    @BeforeAll
    static void beforeAllInit(){
        System.out.println("Before all methods");
    }

    // execute this before any method runs
    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        mathUtils = new MathUtils();
        testReporter.publishEntry("Running "+ testInfo.getDisplayName() + " with tags "+testInfo.getTags());
    }

    @AfterEach
    void cleanup(){
        System.out.println("cleaning up...");
    }

    @Test
    @Tag("Math")
    public void testAdd(){
        int expected = 10;
        int actual = mathUtils.add(-1, 11);
        assertEquals(expected, actual, "The add method should add 2 numbers");
    }

    @Test
    @Tag("Math")
    public void testMultiply(){
        System.out.println("Running "+ testInfo.getDisplayName() + " with tags "+testInfo.getTags());
        int expected = 20;
        int actual = mathUtils.multiply(4, 5);
        assertEquals(expected, actual, "The multiply method should return appropriate result");
    }

    @Test
    @Tag("Math")
    public void divideTest(){
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "Divide by zero should throw an error");
    }

    @Test
    @Tag("Circle")
    public void testComputeCircleRadius(){
        assertEquals(314.1592653589793, mathUtils.computerCircleArea(10), "should return right circle area");
    }
}
