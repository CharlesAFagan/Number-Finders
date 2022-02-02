/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primenumbers;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author chuck
 */
public class Interface {
    
    public static void menuSelection(){
        int selection = 0;        
        boolean valid = false;
        System.out.println("""
                      \nPlease select from the following:
                      1: Test a single number for primality
                      2: Search for prime numbers within a specified range
                      3: Exit Program""");
        while(!valid){
            try{
                selection = new Scanner(System.in).nextInt();
                while(selection>3 || selection<1){
                System.out.println("Invalid entry, please try again");
                selection = new Scanner(System.in).nextInt();
                }
                valid = true;
            }
            catch(InputMismatchException e){
                System.out.println("Invalid entry, Please try again\n");
            }
            
        if(selection==1) testSingle();
        if(selection==2) testRange();
        if(selection==3) return;
        }
    }
    
    public static void testSingle(){
        System.out.println("\nPlease enter a positive integer");
        try{
            long x = new Scanner(System.in).nextLong();
            if(PrimeNumbers.primeTest(x))
                System.out.println(x + " is a prime number!");
            else System.out.println(x + " is not prime");
        } catch(InputMismatchException e){
            System.out.println("Your entry was invalid");
        }
        menuSelection();
    } 
    
    public static void testRange(){
        long x = 0;
        long y = 0;
        boolean valid = false;            
        System.out.println("\nPlease enter the starting integer");
        while(!valid){
            try{
                x = new Scanner(System.in).nextLong();
                valid = true;
            } catch(InputMismatchException e){
                System.out.println("Your entry was invalid, "
                        + "please try again");
            }
        }

        valid = false;
        System.out.println("\nPlease enter the ending integer");
        while(!valid){
            try{
                y = new Scanner(System.in).nextLong();
                valid = true;
            } catch(InputMismatchException e){
                System.out.println("Your entry was invalid, "
                        + "please try again");
            }
        }
        PrimeNumbers cust = new PrimeNumbers(x,y);
        cust.primeSearch();
        menuSelection();
    }
    
    public static void main(String[] Args){
        menuSelection();
    }    
}
