package br.com.ibta.tcc.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import javax.imageio.ImageIO;

import br.com.ibta.RSA.EngineRSA;

public class CriptoImagem {

	/**
	 * @param img
	 * @param eng
	 */
	public void criptografarImagem(Image img, EngineRSA eng) {
		
		for (int x = 0; x < img.getImage().getWidth(); x++) {
			for (int y = 0; y < img.getImage().getHeight(); y++) {

				BigInteger p, pixelCripto;

				p = BigInteger.valueOf(img.getImage().getRGB(x, y) * (-1));
				System.out.println("Pixel: " + p);
				
				pixelCripto = eng.encript(p);
				System.out.println("Pixel criptografado: " + pixelCripto);
				System.out.println("TESTE" + eng.decript(pixelCripto).toString(10));

				Pixel pixel = new Pixel();
				pixel.setPixelCriptografado(pixelCripto);

				System.out.println("Pixel binario criptografado: "
						+ pixel.getPixelCriptografadoBinary());

				montaNovaImagemCriptografada(img, x, y, pixel.getListColor());

			}
		}
		
	}

	/**
	 * @param img
	 * @param x
	 * @param y
	 * @param listColor
	 */
	public void montaNovaImagemCriptografada(Image img, int x, int y,
			Color[] listColor) {

		//System.out.println("---------------------------");

		int startX = x * 3;
		int endX = startX + 2;
		int startY = y * 2;
		int endY = startY + 1;
		int posicaoColor = 0;

		/*
		 * listColor[0] = new Color(-2); listColor[1] = new Color(-3);
		 * listColor[2] = new Color(-4); listColor[3] = new Color(-5);
		 * listColor[4] = new Color(-6); listColor[5] = new Color(-7);
		 */

		// System.out.println(">> w: " + img.getNewImage().getWidth());
		// System.out.println(">> h: " + img.getNewImage().getHeight());

		for (int i = startX; i <= endX; i++) {

			for (int j = startY; j <= endY; j++) {
				/*
				 * System.out.println(j + "" + i + " color: " +
				 * Integer.toBinaryString(listColor[posicaoColor].getRGB()));
				 */
				img.getNewImage()
						.setRGB(i, j, listColor[posicaoColor].getRGB());

				posicaoColor++;

			}

		}

	}

	/**
	 * @param img
	 * @param eng
	 */
	public void descriptografarImagem(Image img, EngineRSA eng) {
		//System.out.println("HASH >>> " + eng.hashCode());
		int width = img.getNewImage().getWidth() / 3;
		int height = img.getNewImage().getHeight() / 2;

		// System.out.println("Tamnho antigo da foto");
		// System.out.println("width: " + width);
		// System.out.println("height " + height);

		BufferedImage imgDescriptografada = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < imgDescriptografada.getWidth(); x++) {

			for (int y = 0; y < imgDescriptografada.getHeight(); y++) {
				// System.out.println("Passando posicoes");
				// System.out.println("x: " + x + " y: " + y);
				String q = remontaPixel(img, x, y);
				BigInteger qq = new BigInteger(q, 2);
				
				//System.out.println("Pixel criptografado:" + qq);
				
				qq = eng.decript(qq);
				qq= qq.multiply(BigInteger.valueOf(-1));
				//System.out.println("pixel descriptado " + qq);
				
				imgDescriptografada.setRGB(x, y, qq.intValue());

			}

		}
		
		File file = new File("/Users/victor/Lenna3.png");

		try {

			ImageIO.write(imgDescriptografada, "png", file);
			Utils.imgCriada(file.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	/**
	 * @param img
	 * @param x
	 * @param y
	 * @return
	 */
	public String remontaPixel(Image img, int x, int y) {

		int startX = x * 3;
		int endX = startX + 2;
		int startY = y * 2;
		int endY = startY + 1;

		String q = "";

		for (int i = startX; i <= endX; i++) {

			for (int j = startY; j <= endY; j++) {

				// System.out.println(j + "" + i);
				Integer a = img.getNewImage().getRGB(i, j);
				String b = Integer.toBinaryString(a);
				// System.out.println(">>" + b);
				// System.out.println(">>>" + b.length());
				q += b.substring(8, 32);

			}

		}

		//System.out.println(">>>>>>>>>>>>>>" + q.length());
		//System.out.println(q);

		q = q.replaceFirst("^0+(?!$)", "");
		//System.out.println(">>>>>>>>>>>>>>" + q.length());
		//System.out.println(q);
		return q;
	}

}