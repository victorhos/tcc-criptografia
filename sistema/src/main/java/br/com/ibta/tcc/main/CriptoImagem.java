package br.com.ibta.tcc.main;

import java.awt.Color;
import java.math.BigInteger;

import br.com.ibta.RSA.EngineRSA;

public class CriptoImagem {

	public void criptografarImagem(Image img, EngineRSA eng) {

		for (int x = 0; x < img.getImage().getWidth(); x++) {
			for (int y = 0; y < img.getImage().getHeight(); y++) {

				BigInteger p, pixelCripto, t3;

				p = BigInteger.valueOf(img.getImage().getRGB(x, y) * (-1));
				pixelCripto = eng.encript(p);

				Pixel pixel = new Pixel();
				pixel.setPixelCriptografado(pixelCripto);

				montaNovaImagem(img, x, y, pixel.getListColor());

			}
		}

	}

	public void montaNovaImagem(Image img, int x, int y, Color[] listColor) {

		int startX = x * 3;
		int fimX = startX + 2;
		int startY = y * 2;
		int fimY = startY + 1;
		int posicaoColor = 0;

		for (int i = startX; i <= fimX; i++) {

			for (int j = startY; j <= fimY; j++) {
				System.out.println(i + " : " + j + " c: " + posicaoColor);
				img.getNewImage()
						.setRGB(i, j, listColor[posicaoColor].getRGB());

				posicaoColor++;

			}

		}

	}

}
