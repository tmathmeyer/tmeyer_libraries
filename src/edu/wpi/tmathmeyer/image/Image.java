package edu.wpi.tmathmeyer.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	public static BufferedImage getImageFromFile(String filename) throws IOException{
		BufferedImage img = null;
		img = ImageIO.read(new File(filename));
		return img;
	}
	
	public static int[] getImageData(BufferedImage i){
		int w = i.getWidth();
		int h = i.getHeight();
		int[] ar = new int[w*h];
		for(int k = 0; k < h; k++){
			for(int j = 0; j < w; j++){
				ar[j+w*k] = (int)(lum(new Color(i.getRGB(j, k))));
			}
		}
		return ar;
	}
	
	public static double[] getImageHueData(BufferedImage i){
		int w = i.getWidth();
		int h = i.getHeight();
		double[] ar = new double[w*h];
		for(int k = 0; k < h; k++){
			for(int j = 0; j < w; j++){
				ar[j+w*k] = new HSLColor(new Color(i.getRGB(j, k))).getHue();
			}
		}
		return ar;
	}
	
	public static double lum(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return .299*r + .587*g + .114*b;
    }
	
	public static int[] convert(int[][] ar){
		int[] res = new int[ar.length * ar[0].length];
		int c = 0;
		for(int[] a : ar){
			for(int i : a){
				res[c] = i;
				c++;
			}
		}
		return res;
	}
	
	public static int[][] convert(int[] ar, int w, int h){
		int[][] res = new int[h][w];
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				res[i][j] = ar[j+w*i];
			}
		}
		return res;
	}
	
	public static double[] convert(double[][] ar){
		double[] res = new double[ar.length * ar[0].length];
		int c = 0;
		for(double[] a : ar){
			for(double i : a){
				res[c] = i;
				c++;
			}
		}
		return res;
	}
	
	public static double[][] convert(double[] ar, int w, int h){
		double[][] res = new double[h][w];
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				res[i][j] = ar[j+w*i];
			}
		}
		return res;
	}
	
	
	public static BufferedImage getImageFromArray(int[] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0,0,width,height,pixels);
        return image;
    }
}
