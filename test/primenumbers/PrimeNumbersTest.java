/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package primenumbers;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.System.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chuck
 */
public class PrimeNumbersTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
    public PrimeNumbersTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Test of singleNumCheck method, of class PrimeNumbers.
     */
    @Test
    public void testSingleNumCheck() {
        System.out.println("singleNumCheck");
        outContent.reset();
        long num = 2;
        String expResult = num + " is a prime number!";
        PrimeNumbers.singleNumCheck(num);
        assertEquals(expResult, outContent.toString().trim());
        
        outContent.reset();
        num = 0;
        expResult = num + " is NOT prime!";
        PrimeNumbers.singleNumCheck(num);
        assertEquals(expResult, outContent.toString().trim());
        
        outContent.reset();
        num = 5;
        expResult = num + " is a prime number!";
        PrimeNumbers.singleNumCheck(num);
        assertEquals(expResult, outContent.toString().trim());
        
        outContent.reset();
        num = 56419815;
        expResult = num + " is NOT prime!";
        PrimeNumbers.singleNumCheck(num);
        assertEquals(expResult, outContent.toString().trim());
        
        outContent.reset();
        num = 1000000011;
        expResult = num + " is NOT prime!";
        PrimeNumbers.singleNumCheck(num);
        assertEquals(expResult, outContent.toString().trim());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
        
    /**
     * Test of primeTest method, of class PrimeNumbers.
     */
    @Test
    public void testPrimeTest() {
        System.out.println("primeTest");
        long num = 4;
        boolean expResult = false;
        boolean result = PrimeNumbers.primeTest(num);
        assertEquals(expResult, result);
        
        num = 2;
        expResult = true;
        result = PrimeNumbers.primeTest(num);
        assertEquals(expResult, result);
        
        num = 10025;
        expResult = false;
        result = PrimeNumbers.primeTest(num);
        assertEquals(expResult, result);
        
        num = Long.parseLong("1000000000000037");
        expResult = true;
        result = PrimeNumbers.primeTest(num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of primeSearch method, of class PrimeNumbers.
     */
    @Test
    public void testPrimeSearch() {
        System.out.println("primeSearch");
        outContent.reset();
        PrimeNumbers instance = new PrimeNumbers(90,100);
        String expResult = "97";
        instance.primeSearch();
        assertEquals(expResult, outContent.toString().trim());
        
        outContent.reset();
        instance = new PrimeNumbers(900000000,900000040);
        expResult = "900000011";
        instance.primeSearch();
        assertEquals(expResult, outContent.toString().trim());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }        
}
