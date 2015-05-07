package br.com.ibta.tcc.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import javax.imageio.ImageIO;

import br.com.ibta.RSA.EngineRSA;

public class Image {

	private BufferedImage image;
	private BufferedImage newImage;
	private String extensao;

	private String pathImage;
	private String newPathImage;

	public Image(String path, String newPath) {

		this.pathImage = path;
		this.newPathImage = newPath;
		this.extensao = extension(path);

	}

	/**
	 * Carrega a imagem original
	 */
	public void loadImage() {

		try {

			File file = new File(getPathImage());

			setImage(ImageIO.read(file));

			Utils.imgSucesso();

		} catch (IOException e) {

			Utils.imgFalha();
			System.out.println(e.getLocalizedMessage());

		}

	}

	/**
	 * Cria uma nova imagem
	 */	
	public void createNewImage() {

		setNewImage();
		
	}

	/**
	 * Salva a nova imagem 
	 */	
	public void saveNewImage() {
		
		File file = new File(getNewPathImage());

		try {

			ImageIO.write(getNewImage(), getExtensao(), file);
			Utils.imgCriada(file.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void transformImage(EngineRSA eng) {

		// for (int x = 0; x < getImage().getWidth(); x++) {
		for (int x = 0; x < 1; x++) {
			// for (int y = 0; y < getImage().getHeight(); y++) {
			for (int y = 0; y < 1; y++) {
				BigInteger t1, t2, t3;

				t1 = BigInteger.valueOf(getImage().getRGB(x, y) * (-1));
				t2 = eng.encript(t1);
				t3 = eng.decript(t2);

				Integer a = 16777215;
				System.out.println("----------------------------------");
				System.out.println("plain = " + t1.toString(10));
				System.out.println("cipher = " + t2.toString(10));
				System.out.println("cipher bin = " + t2.toString(2));
				System.out.println("branco "
						+ Integer.toBinaryString(a).length());
				System.out.println("plain = " + t3.toString(10));
				System.out.println("----------------------------------");

				getImage().setRGB(x, y,
						Integer.parseInt(t3.toString(10)) * (-1));

				// System.out.println(getImage().getRGB(x, y));

			}

		}

	}

	public int[] listRGB() {

		int[] list;

		list = getNewImage().getRGB(0, 0, getNewImage().getWidth(),
				getNewImage().getHeight(), null, 0, getNewImage().getWidth());

		list[0] = -1;

		return list;

	}

	public String extension(String exts) {

		int dot = exts.lastIndexOf(".");

		return exts.substring(dot + 1);

	}

	/* Getters E Setters */

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getNewImage() {
		return newImage;
	}

	public void setNewImage() {

		int width = getImage().getWidth() * 3;
		int height = getImage().getHeight() * 2;

		this.newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getNewPathImage() {
		return newPathImage;
	}

	public void setNewPathImage(String newPathImage) {
		this.newPathImage = newPathImage;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

}
