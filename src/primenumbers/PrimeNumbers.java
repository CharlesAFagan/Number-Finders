 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primenumbers;

import java.util.stream.LongStream;



/**
 *
 * @author Charles Fagan
 */
public class PrimeNumbers {

    //declare variables for start and stop points 
    private final long start;
    private final long stop;
    //declare array to store prime numbers for testing purposes
    private long[] table;
    
    //Constructor     
    public PrimeNumbers(long s, long p) {
        //increase the start number by one if it is even
        if(s%2==0) s++;
        start = s;
        stop = p;
    }
    
    //Method to build an array of all prime numbers that are less than  
    //the square root of the largest number to be tested
    private void tableBuild() {
        //Find the root of stop
        int root = (int)Math.ceil(Math.sqrt(stop)+1);
        //instantiate the table array. Size of array determined using algorithm
        //that estimates the amount of prime numbers and adds a little extra space
        table = new long[(int)(Math.pow(root, 1/1.15))];
        //set first position in table array to 2
        table[0] = 2;
        //declare integer a to represent the current position in the table array
        int a = 1;
        //test all numbers up to a little more than the root of stop and add
        //all numbers that are prime to the array in order from low to high
        for(int i = 3; i<=(root*1.15)+2; i++)
            //the method for testing primality uses the table array for testing
            //as it is built
            if(quickTest(i)) {table[a] = i; a++;}        
    }
    
    //method to check a single number for primality and present the result
    public static void singleNumCheck(long num) {
        if(primeTest(num)) System.out.println(num + " is a prime number!");
        else System.out.println(num +" is NOT prime!");        
    }
    
    //method to determine the primality of a number by testing it against
    //odd numbers less the square root of the number under test
    public static boolean primeTest(long num) {
        //get the easy ones out of the way, gets past issues with the algorithm
        //when the numbers are too low
        if(num==1) return false;
        if(num==2 ||  num==3 || num==5)return true;
        if(num%2==0 || num%3==0 || num%5==0) return false;
        //Algorithm to determine primality using nested for loops
        //The outer loop starts at 7 and increases by 10 every cycle
        //while the inner loop will run 4 cycles testing against numbers that
        //end in 1, 3, 7, or 9 for that group of ten. this is done because it 
        //is unnecessary to test against numbers that are even or end in 5.
        for(long t = 7; t*t<=num; t=t+10)
            for(long i=t; i<t+7; i=i+2)
                //once the number is found to be divisible by something other
                //than 1, a boolean is false is returned by the method to 
                //indicate that it is not prime
                if(num%i==0) return false;
        //If it is not found to be divisible by anything, true is returned
        return true; 
    }
    
    //method to determine the primality of a number by testing it against
    //only prime numbers less than the square root of the number under test
    private boolean quickTest(long num) {
        //automatically return false for 1 as the algorithm will produce a 
        //false positive for 1 because it is only divisible by 1.
        if(num==1) return false;
        //Algorithm to determine primality using a single for loop and the table
        //array of prime numbers less than the square root of the number under test       
        for(int i=0; table[i]*table[i]<=num; i++)
            //false is returned if it is found to be divisible by anything
            if(num%table[i]==0) return false;
        //true is returned if it is not to indicate that the number is prime
        return true;
    }  
    
    //method to carry out a search for prime numbers within the given range
    public void primeSearch() {
        //because 2 is the only even prime number, it is immediatly declared
        //as such if it is within the search range, all other even numbers
        //will not be tested.
        if(start<=1) System.out.println("2");
        //Formula to determine which test algorithm will be faster for the
        //given range. the formula was designed based on real world performance
        //testing for each algorithm. It was found that the primeTest method is 
        //generally much faster for shorter ranges of lower numbers while the 
        //quickTest method is exponentially faster for longer ranges and    
        //very large numbers.
        if(stop-start > (Math.pow(2, Math.log10(stop))*0.095)) {
            //if the quickTest found to be better, build the table of primes
            tableBuild();  
            //Test each odd number within the range and print if it is prime            
            LongStream.iterate(start, i -> i + 2).limit((stop-start+2)/2)
                    .forEach(index -> {
                        if(quickTest(index)) System.out.println(index);
                    });   
        }
        //if the primeTest is found to be a better choice then it is used in
        //the same way, testing each odd number within the range
        else for(long num = start; num <= stop; num=num+2)
                if(primeTest(num)) System.out.println(num);
    }
        
    public static void main(String[] args){
        PrimeNumbers test = new PrimeNumbers
        (Long.parseLong("1000000000000000"),Long.parseLong("1000000000000100"));
        test.primeSearch();
    }
}
