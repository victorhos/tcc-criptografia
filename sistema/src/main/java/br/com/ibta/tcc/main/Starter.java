package br.com.ibta.tcc.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Starter {

	public static void main(String[] args) {
		
		
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(new File("/Users/victor/Pictures/image2px.jpg"));
		} catch (IOException e) {
			//System.out.println("Erro ao carregar a img");
		}
	
		/*
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(img, "jpg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		int q = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth())[0];
		int b = convert(q);
		
		
		//COR
		System.out.println(q);
		
		
		System.out.println(b);
		
		System.out.println(Integer.toHexString(0));
		
		Color color = new Color(q);
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();

		System.out.println("_________________");
		System.out.println(Integer.toHexString(red));
		System.out.println(Integer.toBinaryString(green));
		System.out.println(blue);
	
	}

	public static int convert(int n) {
		return Integer.valueOf(String.valueOf(n), 16);
	}
	
	

}
