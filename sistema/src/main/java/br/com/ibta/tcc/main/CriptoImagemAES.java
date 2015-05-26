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

				color = concatZeros(color);

				cipher = aes.encrypt(color);
				AAA = cipher;

				for (int i = 0; i < cipher.length; i++) {

					System.out.println("b >> " + cipher[i]);

					Integer number = new Integer(cipher[i]);

					if (number > 0) {
						number = number * (-1000);
					}

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

				// System.out.println("minha cor é: " +
				// listColor[posicaoColor].getRGB());

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

				//byte[] q = (byte[]) remontaPixel(img, x, y);

				//System.out.println("aqui! >>  " + aes.decrypt(q));

				// System.out.println("PIXEL >>>>>> " + q);

				// BigInteger qq = new BigInteger(q, 2);

				// qq = eng.decript(qq);
				// qq = qq.multiply(BigInteger.valueOf(-1));

				// img.getDescriptImagem().setRGB(x, y, qq.intValue());

			}

		}

	}

	public static void main(String[] args) throws Exception {

		// byte[] cipher = null;
		// byte[] descipher = new byte[QTD_PIXEL];
		// Color[] listaColor = new Color[QTD_PIXEL];

		AES aes = new AES("AAAAAAAAAAAAAAAA", "minhasenha123456");

		/* Carregando imagem */
		Image img = new Image("/Users/victor/image2px.jpg",
				"/Users/victor/image2pxx.jpg", "/Users/victor/image2pxxx.jpg");
		img.carregarImagem();
		img.criarNovaImagem(8, 2);
		img.criarDescriptImagem(8, 2);

		String color = Integer.toHexString(Integer.valueOf("16777000"));
		color = "0000000000" + color;

		System.out.println("color: " + color);

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

		/*
		 * try { cipher = aes.encrypt(color); } catch (Exception e) {
		 * e.getMessage(); e.printStackTrace(); }
		 * 
		 * for (int i = 0; i < cipher.length; i++) {
		 * 
		 * Integer number = new Integer(cipher[i]);
		 * 
		 * System.out.println(number);
		 * 
		 * if (number > 0) { number = number * (-1000); }
		 * 
		 * listaColor[i] = new Color(number);
		 * 
		 * }
		 */

		System.out.println("-----------------------------");
		System.out.println("      Descriptografia");
		System.out.println("-----------------------------");
		
		byte[] hh = new byte[QTD_PIXEL];

		for (int x = 0; x < img.getDescriptImagem().getWidth(); x++) {

			for (int y = 0; y < img.getDescriptImagem().getHeight(); y++) {

				byte[] q = (byte[]) remontaPixel(img, x, y, hh);
				
				//BBB = q;

				System.out.println("aqui! >>  " + aes.decrypt(q));

			}

		}

		// long startTime2 = System.nanoTime();
		// descriptografarImagem(img, aes);
		// long endTime2 = System.nanoTime();

		// img.salvarDescriptImagem();

		// Tempo de execução para descriptografar a imagem
		// System.out.println(Utils.timer(endTime2, startTime2));

		/*
		 * for (int i = 0; i < listaColor.length; i++) {
		 * 
		 * Integer number = listaColor[i].getRGB();
		 * 
		 * if (number < -1000) { number = (number / 1000) * (-1); }
		 * 
		 * descipher[i] = number.byteValue();
		 * System.out.println(number.byteValue());
		 * 
		 * }
		 * 
		 * try { System.out.println(aes.decrypt(descipher)); } catch (Exception
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		
		System.out.println("##############################");
		System.out.println("##############################");
		System.out.println("##############################");
		
		for (int i = 0; i < AAA.length;i++){
		
			System.out.println(AAA[i] + " / " + BBB[i]);
			//System.out.println();
			
			//System.out.println("igual: " + (AAA[i] == BBB[i]));
			
		}
		

	}

	public static byte[] remontaPixel(Image img, int x, int y, byte[] hh) throws Exception {

		int startX = x * 8;
		int endX = startX + 7;
		int startY = y * 2;
		int endY = startY + 1;

		

		for (int i = startX; i <= endX; i++) {

			for (int j = startY; j <= endY; j++) {

				Integer a = img.getNovaImagem().getRGB(i, j);

				if (a < -1000) {
					a = (a / 1000) * (-1);
				}

				System.out.println("a >> " + a.byteValue());
				hh[i] = a.byteValue();
				
				System.out.println("Setou? " + hh[i]);

			}

		}

		for (int i = 0; i < hh.length; i++){
			
			System.out.println("@@ " + hh[i]);
			
		}
		
		// String jj = aes.decrypt(descipher);
		// System.out.println("aqui >>> " + jj);

		// jj = jj.replaceFirst("^0+(?!$)", "");
		
		BBB = hh;

		return hh;

	}

}
