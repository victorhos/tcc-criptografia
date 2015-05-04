package br.com.ibta.tcc.main;

import java.math.BigInteger;
import java.util.Random;

import br.com.ibta.RSA.EngineRSA;
import br.com.ibta.RSA.KeyRSA;
import br.com.ibta.RSA.RSA;

public class Teste {

	private static int KEY_LENGTH = 64;

	public static void main(String[] args) {

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

		/*
		System.out.println("plain = " + t1.toString(10));
		System.out.println("cipher = " + t2.toString(10));
		System.out.println("plain = " + t3.toString(10));
		*/
		
		Image img = new Image("/Users/victor/Lenna.png",
				"/Users/victor/Lenna2.png");
		img.loadImage();

		long startTime = System.nanoTime();
		img.transformImage(eng);
		long endTime = System.nanoTime();

		img.createNewImage();

		System.out.println(Utils.timer(endTime, startTime));

	}

}
