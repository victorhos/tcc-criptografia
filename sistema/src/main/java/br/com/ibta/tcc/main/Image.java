package br.com.ibta.tcc.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private BufferedImage image;
	private String path_image;

	public Image(String path) {

		this.path_image = path;

	}

	public void loadImage() {

		try {
			setImage(ImageIO.read(new File(getPath_image())));
		} catch (IOException e) {
			System.out.println("Load img failed");
			System.out.println(e.getLocalizedMessage());
		}

	}

	public int[] listRGB() {

		int[] list;

		list = getImage().getRGB(0, 0, getImage().getWidth(),
				getImage().getHeight(), null, 0, getImage().getWidth());

		return list;

	}

	public BufferedImage getImage() {

		return image;

	}

	public void setImage(BufferedImage img) {

		this.image = img;

	}

	public String getPath_image() {
		return path_image;
	}

	public void setPath_image(String path_image) {
		this.path_image = path_image;
	}

}
