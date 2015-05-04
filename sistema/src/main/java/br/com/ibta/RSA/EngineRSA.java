package br.com.ibta.RSA;

import java.math.BigInteger;

public class EngineRSA {

	private KeyRSA e;
	private KeyRSA d;

	public static EngineRSA create(KeyRSA e, KeyRSA d) {
		if (e == null || d == null)
			return null;
		return (new EngineRSA(e, d));
	}

	private EngineRSA(KeyRSA e, KeyRSA d) {
		this.e = e;
		this.d = d;
	}

	public BigInteger encript(BigInteger plain) {
		return plain.modPow(e.getKey(), e.getModulo());
	}

	public BigInteger decript(BigInteger cipher) {
		return cipher.modPow(d.getKey(), d.getModulo());
	}

}