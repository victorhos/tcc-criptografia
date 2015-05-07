package br.com.ibta.tcc.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.math.BigInteger;

import br.com.ibta.RSA.EngineRSA;

public class CriptoImagem {

	public void criptografarImagem(Image img, EngineRSA eng) {

		for (int x = 0; x < img.getImage().getWidth(); x++) {
			for (int y = 0; y < img.getImage().getHeight(); y++) {

				BigInteger p, pixelCripto;

				p = BigInteger.valueOf(img.getImage().getRGB(x, y) * (-1));
				pixelCripto = eng.encript(p);

				Pixel pixel = new Pixel();
				pixel.setPixelCriptografado(pixelCripto);

				montaNovaImagemCriptografada(img, x, y, pixel.getListColor());

			}
		}

	}

	public void descriptografarImagem(Image img, EngineRSA eng) {

		int width = img.getNewImage().getWidth() / 3;
		int height = img.getNewImage().getHeight() / 2;
		
		System.out.println(width + ":" + height);

		BufferedImage imgDescriptografada = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < imgDescriptografada.getWidth(); x++) {

			for (int y = 0; y < imgDescriptografada.getHeight(); y++) {
				remontaPixel(img, x, y);
			}

		}

	}

	public String remontaPixel(Image img, int x, int y) {

		int startX = x * 3;
		int endX = startX + 2;
		int startY = y * 2;
		int endY = startY + 1;

		for (int i = startX; i <= endX; i++) {

			for (int j = startY; j <= endY; j++) {

				System.out.println(">>>" + i + " : " + j);

			}

		}

		return "aa";
	}

	public void montaNovaImagemCriptografada(Image img, int x, int y,
			Color[] listColor) {
		
		System.out.println("---------------------------");
		
		int startX = x * 2;
		int endX = startX + 1;
		int startY = y * 3;
		int endY = startY + 2;
		int posicaoColor = 0;

		System.out.println(">> w: " + img.getNewImage().getWidth());
		System.out.println(">> h: " + img.getNewImage().getHeight());
		
		for (int i = startX; i <= endX; i++) {

			for (int j = startY; j <= endY; j++) {
				System.out.println(i + "" + j /*+ " c: " + posicaoColor*/);
				
				img.getNewImage()
						.setRGB(i, j, listColor[posicaoColor].getRGB());

				posicaoColor++;

			}

		}

	}

}