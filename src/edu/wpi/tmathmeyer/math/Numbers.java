package edu.wpi.tmathmeyer.math;

import java.util.ArrayList;

public class Numbers {
	/**
	 * 
	 * @param n1 the first number
	 * @param n2 the second number
	 * @return the greatest common divisor (GCD) of n1 and n2
	 */
	public static int gcd(int n1, int n2){
		return n2==0 ? n1 : (n1<n2 ? gcd(n2,n1) : gcd(n2,n1%n2));
	}
	
	/**
	 * 
	 * @param n1 the first number
	 * @param n2 the second nummber
	 * @return the least common multiple (LCM) of n1 and n2
	 */
	public static int lcm(int n1, int n2) {
		return (n1*n2)/gcd(n1,n2);
	}
	
	/**
	 * 
	 * @param i the number to check
	 * @return whether i is prime
	 */
	public static boolean isPrime(int i){
		if(i==1)return false;
		if(i==2)return true;
		for(int j = 3; j < Math.sqrt(j); j++)
			if (i%j==0)return false;
		return true;
	}
	
	/**
	 * 
	 * @param i the factorial
	 * @return i!
	 */
	public static long factorial(int i){
		return i==0 ? 1 : i*factorial(i-1);
	}
	
	/**
	 * 
	 * @param i the integer to factor
	 * @return the list of factors (including repeats)
	 */
	public static ArrayList<Integer> listAllPrimeFactors(int i){
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (isPrime(i)){
			res.add(i);
			return res;
		}
		for (int j = 2; j < i/2; j++){
			if (i%j==0){
				res.add(j);
				res.addAll(listAllPrimeFactors(i/j));
				j=i;
			}
		}
		return res;
	}
	
	/**
	 * 
	 * @param i the integer to factor
	 * @return the list of factors (no repeats)
	 */
	public static ArrayList<Integer> listPrimeFactors(int i){
		if (i==1)return new ArrayList<Integer>();
		int divisor = 2;
		ArrayList<Integer> pfs = new ArrayList<Integer>();
		while(divisor < Math.sqrt(i)+1){
			if (i%divisor==0){
				while(i%divisor==0)i = i%divisor;
				pfs = listPrimeFactors(i);
				pfs.add(divisor);
				return pfs;
			}
		}
		pfs.add(i);
		return pfs;
	}
	
	/**
	 * 
	 * @param i the number to factor
	 * @return all the factors of the number (no repeats)
	 */
	public static ArrayList<Integer> ListAllFactors(int i){
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.add(1);
		for (int j = 2; j <=Math.sqrt(i) ; j++){
			if (i%j==0){
				if (!res.contains(j))res.add(j);
				if (!res.contains(i/j))res.add(i/j);
			}
		}
		return res;
	}
	
	
	public static boolean isPowerOfTwo(int i){
		return (i!=0) && (i & (i-1)) == 0;
	}
}
