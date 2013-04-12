package edu.wpi.tmathmeyer.math;

public class Complex {
	private double re, im;
	
	/**
	 * Default constructor for 0+0i
	 */
	public Complex(){
		this(0,0);
	}
	
	/**
	 * this makes the complex number a+bi
	 * 
	 * @param a the real component
	 * @param b the imaginary component
	 */
	public Complex(double a, double b){
		this.re = a;
		this.im = b;
	}
	
	/**
	 * 
	 * @param num complex number to add
	 * @return the sum of this and num
	 */
	public Complex add(Complex num){
		double re = this.re+num.re;
		double im = this.im+num.im;
		return new Complex(re,im);
	}
	
	/**
	 * 
	 * @param num the number to multiply this by
	 * @return the sum of this and num
	 */
	public Complex multiply(Complex num){
		double re = this.re*num.re - this.im*num.im;
		double im = this.re*num.im + this.im*num.re;
		return new Complex(re,im);
	}
	
	/**
	 * 
	 * @param exp the exponent
	 * @return this^exp
	 */
	public Complex exp(int exp){
		if (exp==1)return this;
		if (exp==2)return this.multiply(this);
		if (exp%2==1)return this.multiply(this.exp(exp-1));
		return this.exp(2).exp(exp/2);
	}
	
	/**
	 * 
	 * @return the real component of this complex number
	 */
	public double getReal(){
		return this.re;
	}
	
	/**
	 * 
	 * @return the imaginary component of this complex number
	 */
	public double getImaginary(){
		return this.im;
	}
	
	/**
	 * 
	 * @return the magnitude of this complex number, otherwise known as its distace on the complex plane from (0+0i)
	 */
	public double getMagnitude(){
		return Math.sqrt(this.re*this.re + this.im*this.im);
	}
}
