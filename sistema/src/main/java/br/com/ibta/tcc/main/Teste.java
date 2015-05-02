package br.com.ibta.tcc.main;

public class Teste {

	public static void main(String[] args) {
		
		Image img = new Image(
				"/Users/victor/img500.png",
				"/Users/victor/img500x.png"
		);
		img.loadImage();

		long startTime = System.nanoTime();
		img.transformImage();
		long endTime = System.nanoTime();
		
		img.createNewImage();
		
		System.out.println(Utils.timer(endTime, startTime));

	}

}
