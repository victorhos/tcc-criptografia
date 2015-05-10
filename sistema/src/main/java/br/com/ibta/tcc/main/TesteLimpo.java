package br.com.ibta.tcc.main;

//import java.util.Random;

import br.com.ibta.RSA.EngineRSA;
import br.com.ibta.RSA.KeyRSA;
import br.com.ibta.RSA.RSA;

public class TesteLimpo {

	private static int KEY_LENGTH = 64;

	public static void main(String[] args) {

		KeyRSA keys[];

		/* Criando chaves */
		keys = RSA.createKeys(KEY_LENGTH);
		//Random rd = new Random(System.currentTimeMillis());
		EngineRSA eng = EngineRSA.create(keys[0], keys[1]);
		
		/* Carregando imagem */
		Image img = new Image("/Users/victor/Lenna.png", "/Users/victor/Lenna2.png");
		img.loadImage();
		img.setNewImage();
		
		/* Critopgrafia da imagem*/
		CriptoImagem ci = new CriptoImagem();
		
		long startTime = System.nanoTime();
		ci.criptografarImagem(img, eng);
		long endTime = System.nanoTime();
		System.out.println(Utils.timer(endTime, startTime));
		/* Salvando nova imagem */
		img.saveNewImage();
		
		//System.out.println("---------------------------------------------");
		
		/* Descritopgrafia da imagem*/
		System.out.println("Descritopgrafia");
		long startTime2 = System.nanoTime();
		ci.descriptografarImagem(img, eng);
		long endTime2 = System.nanoTime();
		System.out.println("-------------Aguarde o timer  :)-------------------");
		System.out.println(Utils.timer(endTime2, startTime2));
		
		
		
	}

}
