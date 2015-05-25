package br.com.ibta.AES;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;

/*
 * http://www.caelum.com.br/apostila-java-testes-xml-design-patterns/graficos-com-jfreechart/#7-5-exercicios-jfreechart
 * http://stackoverflow.com/questions/15554296/simple-java-aes-encrypt-decrypt-example
 * http://www.java2s.com/Code/Java/Security/EncryptionanddecryptionwithAESECBPKCS7Padding.htm
 * https://gist.githubusercontent.com/bricef/2436364/raw/2070f666ad990c57f3e08d49b3749da877d0b9e0/AES.java
 * http://www.code2learn.com/2011/06/encryption-and-decryption-of-data-using.html
 * http://karanbalkar.com/2014/02/tutorial-76-implement-aes-256-encryptiondecryption-using-java/
 */

public class AES {

	private String IV;
	private String encryptionKey;

	// private String IV = "AAAAAAAAAAAAAAAA";
	// private String encryptionKey = "minhasenha123456";
	// private String plaintext = "00000000000000000000000000000000";

	public AES(String IV, String encryptionKey) {

		this.encryptionKey = encryptionKey;
		this.IV = IV;

	}

	/*
	 * 
	 * String aa = Integer.toHexString(Integer.valueOf("16777000"));
	 * 
	 * public static void main(String[] args) { try {
	 * 
	 * System.out.println("plain:   " + plaintext);
	 * 
	 * byte[] cipher = encrypt(plaintext, encryptionKey);
	 * 
	 * System.out.println("tamanho: " + cipher.length);
	 * System.out.println("cipher:  ");
	 * 
	 * for (int i = 0; i < cipher.length; i++) { System.out.println(new
	 * Integer(cipher[i]) + ""); }
	 * 
	 * String decrypted = decrypt(cipher, encryptionKey);
	 * 
	 * System.out.println("decrypt: " + decrypted);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	public byte[] encrypt(String plainText) throws Exception {

		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");

		SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes(
				"UTF-8"), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, key,
				new IvParameterSpec(IV.getBytes("UTF-8")));

		return cipher.doFinal(plainText.getBytes("UTF-8"));

	}

	public String decrypt(byte[] cipherText) throws Exception {

		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes(
				"UTF-8"), "AES");

		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(getIV()
				.getBytes("UTF-8")));

		return new String(cipher.doFinal(cipherText), "UTF-8");

	}

	public String getIV() {
		return IV;
	}

	public void setIV(String iV) {
		IV = iV;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}

	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

}