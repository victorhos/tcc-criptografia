package br.com.ibta.tcc.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private BufferedImage image;
	private BufferedImage geradaImage;
	private String pathImage;
	private String geradaPathImage;

	public Image(String path) {

		this.pathImage = path;

	}

	public void loadImage() {

		try {
			
			setImage(ImageIO.read(new File(getPathImage())));
			Mensagem.imgSucesso();
			
		} catch (IOException e) {
			
			Mensagem.imgFalha();
			System.out.println(e.getLocalizedMessage());
		
		}

	}

	public void createImage() {

		setGeradaImage(
				new BufferedImage(
						getImage().getWidth(), 
						getImage().getHeight(), 
						BufferedImage.TYPE_INT_RGB));

	}
	
	public void alterarImg() throws IOException{

		File imageFile = new File(getGeradaPathImage());
		
		ImageIO.write(getGeradaImage(), "png", imageFile);

		Mensagem.imgCriada(imageFile.getPath());
		
	}

	public int[] listRGB() {

		int[] list;

		list = getGeradaImage().getRGB(0, 0, getGeradaImage().getWidth(),
				getGeradaImage().getHeight(), null, 0, getGeradaImage().getWidth());

		list[0] = -1;
		
		return list;

	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getGeradaImage() {
		return geradaImage;
	}

	public void setGeradaImage(BufferedImage geradaImage) {
		this.geradaImage = geradaImage;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getGeradaPathImage() {
		return geradaPathImage;
	}

	public void setGeradaPathImage(String geradaPathImage) {
		this.geradaPathImage = geradaPathImage;
	}

}
