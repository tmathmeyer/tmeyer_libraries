package edu.wpi.tmathmeyer.math.geom;

import java.util.Arrays;

public class Triangle {
	int a,b,c;
	
	/**
	 * 
	 * @param sides the side lengths of the triangle
	 */
	public Triangle(int[] sides){
		Arrays.sort(sides);
		a = sides[0];
		b = sides[1];
		c = sides[2];
	}
	
	public boolean isLegitimate(){
		return a<b+c && b<a+c && c<a+b && a>0 && b>0 && c>0;
	}
	
	public boolean equals(Triangle other){
		return other.a==a && other.b==b && other.c==c;
	}
	
	public double computeRadius(){
		return (a*b*c)/(4*computeArea(a,b,c));
	}
	
	public double computeArea(int a, int b, int c){
		int s = (a+b+c)/2;
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}
}
