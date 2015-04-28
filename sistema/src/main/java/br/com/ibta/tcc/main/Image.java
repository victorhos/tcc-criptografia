package br.com.ibta.tcc.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private BufferedImage image;
	private BufferedImage generatedImage;
	private String pathImage;
	private String generatedPathImage;

	public Image(String path) {

		this.pathImage = path;

	}

	public void loadImage() {

		try {
			setImage(ImageIO.read(new File(getPathImage())));
			System.out.println("----------------------------------");
			System.out.println("Imagem carregada com sucesso");
		} catch (IOException e) {
			System.out.println("----------------------------------");
			System.out.println("Erro ao carregar imagem");
			System.out.println(e.getLocalizedMessage());
		}

	}

	public void createImage() throws IOException {

		setGeneratedImage(new BufferedImage(getImage().getWidth(), getImage()
				.getHeight(), BufferedImage.TYPE_INT_RGB));

		File imageFile = new File(getPathImage());

		ImageIO.write(getGeneratedImage(), "png", imageFile);

		System.out.println("----------------------------------");
		System.out.println("Imagem criada em:");
		System.out.println(imageFile.getPath());

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

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getGeneratedImage() {
		return generatedImage;
	}

	public void setGeneratedImage(BufferedImage generatedImage) {
		this.generatedImage = generatedImage;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getGeneratedPathImage() {
		return generatedPathImage;
	}

	public void setGeneratedPathImage(String generatedPathImage) {
		this.generatedPathImage = generatedPathImage;
	}

}
