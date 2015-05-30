package br.com.ibta.tcc.main;

import java.io.File;

public class Utils {

	public static String LINHA = "-----------------------------------";
	public static String IMG_SUCESSO = "Imagem carregada com sucesso";
	public static String IMG_FALHA = "Erro ao carregar imagem";
	public static String IMG_CRIADA = "Imagem criada em:";

	public static void imgSucesso() {

		System.out.println(LINHA);
		System.out.println(IMG_SUCESSO);
		System.out.println(LINHA);

	}

	public static void imgFalha() {

		System.out.println(LINHA);
		System.out.println(IMG_FALHA);
		System.out.println(LINHA);

	}

	public static void imgCriada(String path) {

		System.out.println(LINHA);
		System.out.println(IMG_CRIADA);
		System.out.println(path);
		System.out.println(LINHA);

	}

	public static String timer(long endTime, long startTime) {

		long nanoSecs = (endTime - startTime);

		int minutes = (int) (nanoSecs / 60000000000.0);
		int seconds = (int) (nanoSecs / 1000000000.0) - (minutes * 60);
		int millisecs = (int) (((nanoSecs / 1000000000.0) - (seconds + minutes * 60)) * 1000);

		if (minutes == 0 && seconds == 0)
			return millisecs + "ms";
		else if (minutes == 0 && millisecs == 0)
			return seconds + "s";
		else if (seconds == 0 && millisecs == 0)
			return minutes + "min";
		else if (minutes == 0)
			return seconds + "s " + millisecs + "ms";
		else if (seconds == 0)
			return minutes + "min " + millisecs + "ms";
		else if (millisecs == 0)
			return minutes + "min " + seconds + "s";

		return minutes + "min " + seconds + "s " + millisecs + "ms";

	}

	public static void tamandoDoArquivo(File file) {

		if (file.exists()) {

			double bytes = file.length();
			double kilobytes = (bytes / 1024);
			double megabytes = (kilobytes / 1024);

			System.out.println(LINHA);
			
			System.out.println("bytes : " + bytes);
			System.out.println("kilobytes : " + kilobytes);
			System.out.println("megabytes : " + megabytes);
			
			System.out.println(LINHA);

		} else {
		
			System.out.println("File does not exists!");
		
		}

	}

}
