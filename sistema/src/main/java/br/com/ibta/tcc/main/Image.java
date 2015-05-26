package br.com.ibta.tcc.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Victor Hugo
 * @author Lilian Oliveira
 * @author Bruna Biontino
 * @version 0.1
 */

public class Image {

	private BufferedImage imagem;
	private BufferedImage novaImagem;
	private BufferedImage descriptImagem;
	private String extensao;

	private String pathImagem;
	private String novoPathImagem;
	private String descriptPathImagem;

	public Image(String path, String novoPath, String descriptPathImagem) {

		this.pathImagem = path;
		this.novoPathImagem = novoPath;
		this.descriptPathImagem = descriptPathImagem;
		this.extensao = extension(path);

	}

	/**
	 * Carrega a imagem para ser cripttografada
	 */
	public void carregarImagem() {

		try {

			File file = new File(getPathImagem());

			setImagem(ImageIO.read(file));

			Utils.imgSucesso();

		} catch (IOException e) {

			Utils.imgFalha();
			System.out.println(e.getLocalizedMessage());

		}

	}

	/**
	 * Criar o buffer da imagem com as dimensões de largura =
	 * (largura_img_original * 3) e altura = (altura_img_original * 2) para
	 * receber a imagem original criptografada
	 */
	public void criarNovaImagem(int width, int height) {

		setNovaImagem(width, height);

	}

	/**
	 * Criar o buffer da imagem com as dimensões de largura =
	 * (largura_img_criptografada / 3) e altura = (altura_img_criptografada / 2)
	 * para receber a imagem descriptografada
	 */
	public void criarDescriptImagem(int width, int height) {

		setDescriptImagem(width, height);

	}

	/**
	 * Salva a imagem criptografada
	 */
	public void salvarNovaImagem() {

		File file = new File(getNovoPathImagem());

		try {

			ImageIO.write(getNovaImagem(), getExtensao(), file);
			Utils.imgCriada(file.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Salva a imagem descriptografada
	 */
	public void salvarDescriptImagem() {

		File file = new File(getDescriptPathImagem());

		try {

			ImageIO.write(getDescriptImagem(), getExtensao(), file);
			Utils.imgCriada(file.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param exts
	 *            é o path da imagem
	 * @return a extensão da imagem Ex. PNG, JPG, JPEG
	 */
	public String extension(String exts) {

		int dot = exts.lastIndexOf(".");

		return exts.substring(dot + 1);

	}

	/* Getters E Setters */

	public BufferedImage getImagem() {
		return imagem;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

	public BufferedImage getNovaImagem() {
		return novaImagem;
	}

	public void setNovaImagem(int width, int height) {

		int largura = getImagem().getWidth() * width;
		int altura = getImagem().getHeight() * height;

		this.novaImagem = new BufferedImage(largura, altura,
				BufferedImage.TYPE_INT_RGB);

	}

	public BufferedImage getDescriptImagem() {
		return descriptImagem;
	}

	public void setDescriptImagem(int width, int height) {

		int largura = getNovaImagem().getWidth() / width;
		int altura = getNovaImagem().getHeight() / height;

		this.descriptImagem = new BufferedImage(largura, altura,
				BufferedImage.TYPE_INT_RGB);

	}

	public String getDescriptPathImagem() {
		return descriptPathImagem;
	}

	public void setDescriptPathImagem(String descriptPathImagem) {
		this.descriptPathImagem = descriptPathImagem;
	}

	public String getPathImagem() {
		return pathImagem;
	}

	public void setPathImagem(String pathImage) {
		this.pathImagem = pathImage;
	}

	public String getNovoPathImagem() {
		return novoPathImagem;
	}

	public void setNovoPathImagem(String newPathImage) {
		this.novoPathImagem = newPathImage;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

}
