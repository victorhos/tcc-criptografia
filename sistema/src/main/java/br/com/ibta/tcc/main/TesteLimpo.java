package br.com.ibta.tcc.main;

import br.com.ibta.RSA.EngineRSA;
import br.com.ibta.RSA.KeyRSA;
import br.com.ibta.RSA.RSA;

public class TesteLimpo {

	private static int KEY_LENGTH = 64;

	public static void main(String[] args) {

		KeyRSA keys[];

		/* Criando chaves */
		keys = RSA.createKeys(KEY_LENGTH);
		EngineRSA eng = EngineRSA.create(keys[0], keys[1]);

		/* Carregando imagem */
		Image img = new Image(
				"/Users/victor/image2px.jpg",
				"/Users/victor/image2pxx.jpg",
				"/Users/victor/image2pxxx.jpg");
		img.carregarImagem();
		img.criarNovaImagem();
		img.criarDescriptImagem();

		/* Critopgrafia da imagem */
		CriptoImagem ci = new CriptoImagem();

		long startTime = System.nanoTime();
		ci.criptografarImagem(img, eng);
		long endTime = System.nanoTime();

		// Tempo de execução para criptografar a imagem
		System.out.println(Utils.timer(endTime, startTime));

		/* Salvando nova imagem */
		img.salvarNovaImagem();

		/* Descritopgrafia da imagem */
		System.out.println("Descritopgrafia");
		long startTime2 = System.nanoTime();
		ci.descriptografarImagem(img, eng);
		long endTime2 = System.nanoTime();

		img.salvarDescriptImagem();

		// Tempo de execução para descriptografar a imagem
		System.out.println(Utils.timer(endTime2, startTime2));

	}

}
