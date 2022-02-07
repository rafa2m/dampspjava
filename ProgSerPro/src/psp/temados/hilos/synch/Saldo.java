package psp.temados.hilos.synch;

import java.util.Random;

/**
 * Clase Saldo, 
 * con un atributo que nos indique el saldo, y el constructor de la misma 
 * debe proporcionar el saldo inicial. Crea varios métodos, uno 
 * para obtener el saldo y otro para dar valor a al saldo, en estos 
 * dos métodos añade un sleep() aleatorio. Y otro método que reciba una 
 * cantidad que añadirá al saldo actual, este método debe informar de quién 
 * añade cantidad al saldo, la cantidad que añade y el estado inicial 
 * del saldo (antes de añadir), el estado final del saldo (después de añadir). 
 * Define este método como sincronizado y los parámetros necesarios.
 * @author rafa
 * @version 1.0
 */
public class Saldo {
	private long saldo;
	public Saldo(long saldo) {
		this.saldo = saldo;
	}
	public void setSaldo(long saldo) {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(500)+500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.saldo = saldo;
	}
	public long getSaldo() {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(500)+500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.saldo;
	}
	public synchronized void addSaldo(long incremento, String usuario) {
		System.out.println("Saldo actual " + this.saldo +
				"€, se añadirán " +  incremento  + " € a la cuenta de " + usuario);
		this.saldo +=incremento;
		System.out.println("Saldo actualizado " + this.saldo +
				"€, se han añadido " +  incremento  + " € a la cuenta de " + usuario);
	}
}
