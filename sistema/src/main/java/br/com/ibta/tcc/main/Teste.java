package br.com.ibta.tcc.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import javax.imageio.ImageIO;

import br.com.ibta.RSA.EngineRSA;
import br.com.ibta.RSA.KeyRSA;
import br.com.ibta.RSA.RSA;

public class Teste {

	private static int KEY_LENGTH = 64;

	public static void main(String[] args) {

		
		/* transformando o pixel criptografado em 6 pixels novos  */
		
		BigInteger aa = new BigInteger("79502169818938959280363064769819069673");
		String bb = aa.toString(2);

		System.out.println(bb.length());
		System.out.println(bb);

		String zeros = "";

		for (int i = 0; i < (144 - bb.length()); i++) {
			zeros += "0";
		}

		String cc = zeros + bb;

		int qtd = cc.length() / 24;

		Color[] listaColor = new Color[6];

		for (int i = 0; i < qtd; i++) {

			int j = i * 24;
			int l = j + 23;

			System.out.println("---------------------------");
			// System.out.println(">>>> inicio: " + j);
			// System.out.println(">>>> final : " + l);

			String ff = cc.substring(j, l);

			System.out.println("---------------------------");

			// Color vv = new Color(Integer.parseInt(ff, 2));
			// System.out.println("Nossa nova cor é: " + vv.getRGB());

			listaColor[i] = new Color(Integer.parseInt(ff, 2));

		}

		System.out.println(">>> valor final: " + cc.length());
		
		
		/* FIM */

		
		/* Reconstruindo a imagem */
		BufferedImage imgTeste = new BufferedImage(3, 2,
				BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < imgTeste.getWidth(); x++) {

			for (int y = 0; y < imgTeste.getHeight(); y++) {

				imgTeste.setRGB(x, y, listaColor[x].getRGB());

			}
		}
		
		File file = new File("/Users/victor/img_gerada.png");
		
		try {
			ImageIO.write(imgTeste, "PNG", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* FIM */
		

		KeyRSA keys[];

		keys = RSA.createKeys(KEY_LENGTH);

		System.out.println("-----------------------------");
		System.out.println("Encript Key = " + keys[0]);
		System.out.println("Decript Key = " + keys[1]);
		System.out.println("-----------------------------");

		Random rd = new Random(System.currentTimeMillis());
		EngineRSA eng = EngineRSA.create(keys[0], keys[1]);

		BigInteger t1, t2, t3;
		t1 = new BigInteger(5, rd);
		t2 = eng.encript(t1);
		t3 = eng.decript(t2);

		
		  System.out.println("plain = " + t1.toString(10));
		  System.out.println("cipher = " + t2.toString(10));
		  System.out.println("plain = " + t3.toString(10));
		 

		Image img = new Image("/Users/victor/Lenna.png",
				"/Users/victor/Lenna2.png", "/Users/victor/Lenna3.png");
		img.carregarImagem();;

		long startTime = System.nanoTime();
		//img.transformImage(eng);
		long endTime = System.nanoTime();

		//img.criarNovaImagem();;

		System.out.println(Utils.timer(endTime, startTime));

	}
}
