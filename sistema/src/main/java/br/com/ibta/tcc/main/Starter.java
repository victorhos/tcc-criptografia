package br.com.ibta.tcc.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Starter {

	public static void main(String[] args) throws IOException {

		/*
		Image qq = new Image("/Users/victor/Pictures/image2px.jpg");
		qq.loadImage();
		qq.setGeradaPathImage("/Users/victor/Pictures/teste.png");
		qq.createImage();
		qq.listRGB();
		qq.alterarImg();
		*/
		BufferedImage img = null;

		try {
			img = ImageIO.read(new File("/Users/victor/image2px.jpg"));
		} catch (IOException e) {
			System.out.println("Erro ao carregar a img");
		}

		//ByteArrayOutputStream baos = new ByteArrayOutputStream();

		System.out.println();

		img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0,
				img.getWidth());

		img.setRGB(0, 0, -16777216);
		img.setRGB(1, 0, -1);
		
		BufferedImage aa = img;
		
		try {
			ImageIO.write(aa, "png", new File("/Users/victor/image2pxx.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Integer q = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0,
				img.getWidth())[0];
		Integer b = convert(q);

		// System.out.println(">>>>>>>>>>>" + q.getClass());

		// COR
		System.out.println(q);

		System.out.println(b);

		System.out.println(Integer.toHexString(0));

		Color color = new Color(-16777216);
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
