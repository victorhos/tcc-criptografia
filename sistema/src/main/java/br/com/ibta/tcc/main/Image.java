package br.com.ibta.tcc.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

	public void loadImage() {

		try {

			File file = new File(getPathImage());

			setImage(ImageIO.read(file));

			Mensagem.imgSucesso();

		} catch (IOException e) {

			Mensagem.imgFalha();
			System.out.println(e.getLocalizedMessage());

		}

	}

	public void createNewImage() {

		File file = new File(getNewPathImage());
		setNewImage(getImage());

		try {

			ImageIO.write(getNewImage(), getExtensao(), file);
			Mensagem.imgCriada(file.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void transformImage() {

		for (int x = 0; x < getImage().getWidth(); x++) {

			for (int y = 0; y < getImage().getHeight(); y++) {

				System.out.println(getImage().getRGB(x, y));
				getImage().setRGB(x, y, -1);
				System.out.println(getImage().getRGB(x, y));

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

	/* GET E SET */

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getNewImage() {
		return newImage;
	}

	public void setNewImage(BufferedImage newImage) {
		this.newImage = newImage;
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
