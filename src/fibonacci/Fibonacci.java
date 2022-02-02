/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacci;

import java.math.BigInteger;


/**
 * 
 * @author Charles Fagan
 */
public final class Fibonacci {
     
    BigInteger[] mem;
    BigInteger num;
    
    public Fibonacci(int n) {
        mem = new BigInteger[n+1];
        num = itFibCalc(n);
    }
    
    public BigInteger getNum() {
        return num;
    }
    
    public BigInteger itFibCalc(int n) {
        if(n==0||n==1) return new BigInteger(Integer.toString(n));
        BigInteger oldNum = new BigInteger("0");
        BigInteger newNum = new BigInteger("1");
        BigInteger temp;
        for(int i = 1; i < n; i++) {
            temp = newNum;
            newNum = oldNum.add(newNum);
            oldNum = temp;
        }
        return newNum;
    }
    
    public BigInteger fibCalc(int n) {
        if(n==0||n==1) return new BigInteger(Integer.toString(n));
        if(mem[n] == null) 
            return mem[n] = fibCalc(n - 1).add(fibCalc(n - 2));
        return mem[n];
    }
    
    // Main method
    public static void main(String[] args) {
        //for(int i = 0; i <= 10000; i++){
        //    System.out.println(i + " = " + (new Fibonacci(i)).getNum());
        //}
        System.out.println(new Fibonacci(10000000).getNum());
    }
}
