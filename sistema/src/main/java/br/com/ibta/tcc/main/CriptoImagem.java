package br.com.ibta.tcc.main;

import java.awt.Color;
import java.math.BigInteger;

import br.com.ibta.RSA.EngineRSA;

public class CriptoImagem {

	public void criptografarImagem(Image img, EngineRSA eng) {

		for (int x = 0; x < img.getImagem().getWidth(); x++) {
			for (int y = 0; y < img.getImagem().getHeight(); y++) {

				BigInteger p, pixelCripto;

				p = BigInteger.valueOf(img.getImagem().getRGB(x, y) * (-1));

				pixelCripto = eng.encript(p);

				Pixel pixel = new Pixel();
				pixel.setPixelCriptografado(pixelCripto);

				montaNovaImagemCriptografada(img, x, y, pixel.getListColor());

			}
		}

	}

	public void montaNovaImagemCriptografada(Image img, int x, int y,
			Color[] listColor) {

		int startX = x * 3;
		int endX = startX + 2;
		int startY = y * 2;
		int endY = startY + 1;
		int posicaoColor = 0;

		for (int i = startX; i <= endX; i++) {

			for (int j = startY; j <= endY; j++) {

				img.getNovaImagem().setRGB(i, j,
						listColor[posicaoColor].getRGB());

				posicaoColor++;

			}

		}

	}

	public void descriptografarImagem(Image img, EngineRSA eng) {

		for (int x = 0; x < img.getDescriptImagem().getWidth(); x++) {

			for (int y = 0; y < img.getDescriptImagem().getHeight(); y++) {

				String q = remontaPixel(img, x, y);
				BigInteger qq = new BigInteger(q, 2);

				qq = eng.decript(qq);
				qq = qq.multiply(BigInteger.valueOf(-1));

				img.getDescriptImagem().setRGB(x, y, qq.intValue());

			}

		}

	}

	public String remontaPixel(Image img, int x, int y) {

		int startX = x * 3;
		int endX = startX + 2;
		int startY = y * 2;
		int endY = startY + 1;

		String q = "";

		for (int i = startX; i <= endX; i++) {

			for (int j = startY; j <= endY; j++) {

				Integer a = img.getNovaImagem().getRGB(i, j);
				String b = Integer.toBinaryString(a);
				q += b.substring(8, 32);

			}

		}

		q = q.replaceFirst("^0+(?!$)", "");

		return q;

	}

}