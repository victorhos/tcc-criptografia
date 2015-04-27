package br.com.ibta.tcc.main;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private static BufferedImage image;
	private static String path_image;

	public static void loadImage() {

		try {
			setImage(ImageIO.read(new File(getPath_image())));
		} catch (IOException e) {
			System.out.println("Erro ao carregar a img");
		}

	}

	public BufferedImage getImage() {
		return image;
	}

	public static void setImage(BufferedImage img) {
		image = img;
	}

	public static String getPath_image() {
		return path_image;
	}

	public void setPath_image(String path_image) {
		Image.path_image = path_image;
	}

}
