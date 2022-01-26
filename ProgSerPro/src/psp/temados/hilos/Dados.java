package psp.temados.hilos;

import java.util.Random;

public class Dados extends Thread {
	Random rnd = new Random();
	private int resultado;

	public Dados() {
		super();
		this.resultado = resultado;
	}

	@Override
	public void run() {
		int resultado = rnd.nextInt(6);
		this.resultado = resultado;
		System.out.println("El juego ha terminado");
		System.out.println("El resultado es: " + resultado);
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

}
