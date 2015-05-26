package br.com.ibta.tcc.main;

import java.awt.Color;
import br.com.ibta.AES.AES;

public class CriptoImagemAES {

	static int QTD_PIXEL = 16;
	static byte[] AAA = new byte[QTD_PIXEL];
	static byte[] BBB = new byte[QTD_PIXEL];

	public static void criptografarImagem(Image img, AES aes) throws Exception {

		for (int x = 0; x < img.getImagem().getWidth(); x++) {
			for (int y = 0; y < img.getImagem().getHeight(); y++) {

				byte[] cipher = null;
				Color[] listaColor = new Color[QTD_PIXEL];

				String color = Integer.toHexString(Integer.valueOf(img
						.getImagem().getRGB(x, y)));

				// System.out.println("color: "
				// + Integer.valueOf(img.getImagem().getRGB(x, y)));

				color = concatZeros(color);

				cipher = aes.encrypt(color);

				if (x == 0 && y == 53) {
					AAA = cipher;
				}

				for (int i = 0; i < cipher.length; i++) {

					Integer number = new Integer(cipher[i]);

					// System.out.println("aa >> " + cipher[i]);

					/*
					if (number > 0) {
						number = number * (-1000);
					}

					if (number == 0) {
						number = -999;
					}
					*/

					// System.out.println("a > " + number);

					listaColor[i] = new Color(number);

				}

				montaNovaImagemCriptografada(img, x, y, listaColor);

			}
		}

	}

	public static String concatZeros(String color) {

		String zeros = "";

		for (int i = 0; i < (16 - color.length()); i++) {
			zeros += "0";
		}

		color = zeros + color;

		return color;

	}

	public static void montaNovaImagemCriptografada(Image img, int x, int y,
			Color[] listColor) {

		int startX = x * 8;
		int endX = startX + 7;
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

	public static void descriptografarImagem(Image img, AES aes)
			throws Exception {

		System.out.println("width : " + img.getDescriptImagem().getWidth());
		System.out.println("height: " + img.getDescriptImagem().getHeight());

		for (int x = 0; x < img.getDescriptImagem().getWidth(); x++) {

			for (int y = 0; y < img.getDescriptImagem().getHeight(); y++) {

				System.out.println(x + " / " + y);

				byte[] q = remontaPixel(img, x, y);

				String jj = aes.decrypt(q);
				// System.out.println("aqui  >>> " + jj);

				jj = jj.replaceFirst("^0+(?!$)", "");
				// System.out.println("aqui2 >>> " + jj);

				try{
					int vv = (int) Long.parseLong(jj, 16);
					img.getDescriptImagem().setRGB(x, y, vv);
				}
				catch(Exception e){
					
					for (int i = 0; i < AAA.length; i++) {

						if (AAA[i] != BBB[i]) {
							System.out.println(AAA[i] + " / " + BBB[i]);
						}

					}
					
					System.out.println("--------");
					
				}
				
				

			}

		}

	}

	public static void main(String[] args) throws Exception {

		AES aes = new AES("AAAAAAAAAAAAAAAA", "minhasenha123456");

		/* Carregando imagem */
		Image img = new Image("/Users/victor/1920x1080.jpg",
				"/Users/victor/1920x1080_2.jpg", "/Users/victor/1920x1080_3.jpg");
		img.carregarImagem();
		img.criarNovaImagem(8, 2);
		img.criarDescriptImagem(8, 2);

		System.out.println("-----------------------------");
		System.out.println("       Criptografia");
		System.out.println("-----------------------------");

		long startTime = System.nanoTime();
		criptografarImagem(img, aes);
		long endTime = System.nanoTime();

		// Tempo de execução para criptografar a imagem
		System.out.println(Utils.timer(endTime, startTime));

		/* Salvando nova imagem */
		img.salvarNovaImagem();

		System.out.println("-----------------------------");
		System.out.println("      Descriptografia");
		System.out.println("-----------------------------");

		long startTime2 = System.nanoTime();
		descriptografarImagem(img, aes);
		long endTime2 = System.nanoTime();

		/*
		for (int i = 0; i < AAA.length; i++) {

			if (AAA[i] != BBB[i]) {
				System.out.println(AAA[i] + " / " + BBB[i]);
			}

		}
*/
		img.salvarDescriptImagem();

		// Tempo de execução para descriptografar a imagem
		System.out.println(Utils.timer(endTime2, startTime2));

	}

	public static byte[] remontaPixel(Image img, int x, int y) throws Exception {

		int startX = x * 8;
		int endX = startX + 7;
		int startY = y * 2;
		int endY = startY + 1;
		int contador = 0;

		byte[] hh = new byte[16];

		for (int i = startX; i <= endX; i++) {

			for (int j = startY; j <= endY; j++) {

				Integer a = img.getNovaImagem().getRGB(i, j);

				/*
				if (a < -1000) {
					a = (a / 1000) * (-1);
				}

				if (a == -999) {
					a = 0;
				}
				*/

				// System.out.println("b > " + a.byteValue());
				hh[contador] = a.byteValue();
				contador++;

			}

		}

		BBB = hh;

		return hh;

	}

}
