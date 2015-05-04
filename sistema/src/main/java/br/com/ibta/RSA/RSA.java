package br.com.ibta.RSA;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

	private static int KEY_LENGTH = 64;

	public static boolean isPrime(BigInteger n) {

		for (int i = 2; 2 * i < n.intValue(); i++) {

			if (n.intValue() % i == 0) {
				return false;
			}

		}

		return true;
	}

	public static void main(String args[]) {
		
		KeyRSA keys[];

		try {

			keys = RSA.createKeys(KEY_LENGTH);

			System.out.println("Encript Key = " + keys[0]);
			System.out.println("Decript Key = " + keys[1]);

			Random rd = new Random(System.currentTimeMillis());
			EngineRSA eng = EngineRSA.create(keys[0], keys[1]);

			BigInteger t1, t2, t3;
			t1 = new BigInteger(5, rd);
			t2 = eng.encript(t1);
			t3 = eng.decript(t2);

			System.out.println("plain = " + t1.toString(10));
			System.out.println("cipher = " + t2.toString(10));
			System.out.println("plain = " + t3.toString(10));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int CERTAINTY = 100;

	/*
	 * Metodo responsavel pela criacao das chaves
	 */

	public static KeyRSA[] createKeys(int bitLength) throws ArithmeticException {

		BigInteger p, q;
		BigInteger e, d, n;

		if (bitLength < 2)
			throw (new ArithmeticException("Err: bitLength inv�lido"));

		Random rd;
		rd = new Random(System.currentTimeMillis());

		BigInteger t1, t2, t3;
		t1 = new BigInteger(bitLength, CERTAINTY, rd);
		t2 = new BigInteger(bitLength, CERTAINTY, rd);
		t3 = new BigInteger(bitLength, CERTAINTY, rd);

		while (true) {
			int c12, c13, c23;
			c12 = t1.compareTo(t2);
			c13 = t1.compareTo(t3);
			c23 = t2.compareTo(t3);

			if (c12 != 0 && c13 != 0 && c23 != 0) {
				if (c12 == 1 && c13 == 1) {
					d = t1;
					p = t2;
					q = t3;
				} else if (c23 == 1) {
					d = t2;
					p = t1;
					q = t3;
				} else {
					d = t3;
					p = t1;
					q = t2;
				}
				break;
			} else if (c12 == 0 || c13 == 0) {
				t1 = new BigInteger(bitLength, CERTAINTY, rd);
			} else {
				t2 = new BigInteger(bitLength, CERTAINTY, rd);
			}
		}

		BigInteger Phi;
		BigInteger unid = new BigInteger("1");
		Phi = (p.subtract(unid).multiply(q.subtract(unid)));

		e = d.modInverse(Phi);
		n = p.multiply(q);

		// cria duas chaves, a de criptografia e a de decriptografia
		KeyRSA keys[] = new KeyRSA[2];

		keys[0] = KeyRSA.create(e, n);
		keys[1] = KeyRSA.create(d, n);

		return keys;
	}

}
