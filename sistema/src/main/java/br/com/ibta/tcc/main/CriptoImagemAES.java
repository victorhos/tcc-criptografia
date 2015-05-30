package br.com.ibta.tcc.main;

import java.awt.Color;
import br.com.ibta.AES.AES;

public class CriptoImagemAES {

	static int QTD_PIXEL = 16;

	public static void criptografarImagem(Image img, AES aes) throws Exception {

		for (int x = 0; x < img.getImagem().getWidth(); x++) {
			for (int y = 0; y < img.getImagem().getHeight(); y++) {

				byte[] cipher = null;
				Color[] listaColor = new Color[QTD_PIXEL];

				String color = Integer.toHexString(Integer.valueOf(img
						.getImagem().getRGB(x, y)));

				color = concatZeros(color);

				cipher = aes.encrypt(color);

				for (int i = 0; i < cipher.length; i++) {

					Integer number = new Integer(cipher[i]);

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

		for (int x = 0; x < img.getDescriptImagem().getWidth(); x++) {

			for (int y = 0; y < img.getDescriptImagem().getHeight(); y++) {

				byte[] q = remontaPixel(img, x, y);

				String jj = aes.decrypt(q);

				jj = jj.replaceFirst("^0+(?!$)", "");

				int vv = (int) Long.parseLong(jj, 16);
				img.getDescriptImagem().setRGB(x, y, vv);

			}

		}

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

				hh[contador] = a.byteValue();
				contador++;

			}

		}

		return hh;

	}

	public static void main(String[] args) throws Exception {

		AES aes = new AES("AAAAAAAAAAAAAAAA", "minhasenha123456");

		/* Carregando imagem */
		Image img = new Image(
				"/Users/victor/Google Drive/Tcc/Imagens Testes/AES/rss.png",
				"/Users/victor/Google Drive/Tcc/Imagens Testes/AES/rss_cript_aes.png",
				"/Users/victor/Google Drive/Tcc/Imagens Testes/AES/rss_descript_aes.png");
		img.carregarImagem();
		img.criarNovaImagem(8, 2);
		img.criarDescriptImagem(8, 2);

		System.out.println("Criptografando...");

		long startTime = System.nanoTime();
		criptografarImagem(img, aes);
		long endTime = System.nanoTime();

		// Tempo de execução para criptografar a imagem
		System.out.println(Utils.timer(endTime, startTime));

		/* Salvando nova imagem */
		img.salvarNovaImagem();

		System.out.println("Descriptografando...");

		long startTime2 = System.nanoTime();
		descriptografarImagem(img, aes);
		long endTime2 = System.nanoTime();

		img.salvarDescriptImagem();

		// Tempo de execução para descriptografar a imagem
		System.out.println(Utils.timer(endTime2, startTime2));

	}

}
