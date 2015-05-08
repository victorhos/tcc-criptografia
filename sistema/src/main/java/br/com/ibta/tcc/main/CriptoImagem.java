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

	public void montaNovaImagemCriptografada(Image img, int x, int y,
			Color[] listColor) {

		System.out.println("---------------------------");

		int startX = x * 3;
		int endX = startX + 2;
		int startY = y * 2;
		int endY = startY + 1;
		int posicaoColor = 0;

		listColor[0] = new Color(-1);
		listColor[1] = new Color(-2);
		listColor[2] = new Color(-3);
		listColor[3] = new Color(-4);
		listColor[4] = new Color(-5);
		listColor[5] = new Color(-6);

		System.out.println(">> w: " + img.getNewImage().getWidth());
		System.out.println(">> h: " + img.getNewImage().getHeight());

		for (int i = startX; i <= endX; i++) {

			for (int j = startY; j <= endY; j++) {
				System.out.println(j + "" + i + " color: "
						+ listColor[posicaoColor].getRGB());

				img.getNewImage()
						.setRGB(i, j, listColor[posicaoColor].getRGB());

				posicaoColor++;

			}

		}

	}

	public void descriptografarImagem(Image img, EngineRSA eng) {

		int width = img.getNewImage().getWidth() / 3;
		int height = img.getNewImage().getHeight() / 2;

		//System.out.println("Tamnho antigo da foto");
		//System.out.println("width: " + width);
		//System.out.println("height " + height);

		BufferedImage imgDescriptografada = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < imgDescriptografada.getWidth(); x++) {

			for (int y = 0; y < imgDescriptografada.getHeight(); y++) {
				//System.out.println("Passando posicoes");
				//System.out.println("x: " + x + " y: " + y);
				remontaPixel(img, x, y);
			}

		}

	}

	public String remontaPixel(Image img, int x, int y) {
		
		System.out.println("------------------------------");
		
		int startX = x * 3;
		int endX = startX + 2;
		int startY = y * 2;
		int endY = startY + 1;

		for (int i = startX; i <= endX; i++) {

			for (int j = startY; j <= endY; j++) {
				System.out.println("----------start---------");
				System.out.println(j + "" + i);
				Integer a = img.getNewImage().getRGB(i, j);
				String b = a.toString(2);
				System.out.println(">>"+b);
				System.out.println(">>>" + b.length());
				System.out.println("----------end-----------");

			}

		}

		return "aa";
	}

}