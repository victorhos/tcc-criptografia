package br.com.ibta.tcc.main;

import java.awt.Color;
import java.math.BigInteger;

public class Pixel {

	private static final int BLOCO_BIT = 144;
	private static final int QTD_PIXEL = BLOCO_BIT / 24;

	private Integer pixel;
	private Color color;
	private BigInteger pixelCriptografado;
	private String pixelCriptografadoBinary;

	public Color[] getListColor() {

		Color[] listaColor = new Color[QTD_PIXEL];

		for (int i = 0; i < QTD_PIXEL; i++) {

			int j = i * 24;
			int l = j + 24;

			String ff = getPixelCriptografadoBinary().substring(j, l);

			listaColor[i] = new Color(Integer.parseInt(ff, 2));

		}

		return listaColor;

	}

	/* Getters E Setters */

	public String getPixelCriptografadoBinary() {
		return pixelCriptografadoBinary;
	}

	public void setPixelCriptografadoBinary(String pixelCriptografadoBinary) {

		String zeros = "";

		for (int i = 0; i < (BLOCO_BIT - pixelCriptografadoBinary.length()); i++) {
			zeros += "0";
		}

		pixelCriptografadoBinary = zeros + pixelCriptografadoBinary;

		this.pixelCriptografadoBinary = pixelCriptografadoBinary;

	}

	public BigInteger getPixelCriptografado() {
		return pixelCriptografado;
	}

	public void setPixelCriptografado(BigInteger pixelCriptografado) {

		setPixelCriptografadoBinary(pixelCriptografado.toString(2));

		this.pixelCriptografado = pixelCriptografado;

	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Integer getPixel() {
		return pixel;
	}

	public void setPixel(Integer pixel) {
		this.pixel = pixel;
	}

}
