package psp.temados.proco;

import java.io.IOException;

/**
 * Clase para probar el modelo de sincronización 
 * productor-consumidor.
 * @author rafa
 * @version 1.0
 */
public class TestProductorConsumidor {
	private Cola cola = new Cola();
	public static void main(String[] args) {
		
		TestProductorConsumidor tpc = new TestProductorConsumidor();
		tpc.testProductorLento3();		
	}
	public void testProductorLento1() {
		/*
		 * 1. El productor es más lento que el consumidor.
		 * - Más consumidores que productores.
		 */		
		Productor productor = 
				new Productor(this.cola, 1, 2000);	
		Consumidor consumidor1 = 
				new Consumidor(this.cola, 1, 500);
		Consumidor consumidor2 = 
				new Consumidor(this.cola, 2, 500);
		
		productor.start();
		
		consumidor1.start();
		consumidor2.start();
		try {
			productor.join();
			consumidor1.join();
			consumidor2.join();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		System.out.println("Fin del caso de prueba: productor más lento "
				+ "y más consumidores que productores");
		System.out.println("---------------------------------------------------------------");		
	}
	private void testProductorLento2() {
		/*
		 * - Más productores que consumidores
		 */
		Productor productor1 =	new Productor(this.cola, 1, 2000);
		Productor productor2 = 
				new Productor(this.cola, 2, 2000);
		Consumidor consumidor1 = 
				new Consumidor(this.cola, 1, 500);
				
		productor1.start();		
		productor2.start();
		consumidor1.start();
		
		try {
			productor1.join();notify();
			productor2.join();
			consumidor1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Fin del caso de prueba: productor más lento "
				+ "y más productores que consumidores");		
		System.out.println("---------------------------------------------------------------");		
	}
	private void testProductorLento3() {
		/*
		 * - Mismo número de productores que consumidores
		 */		
		Productor productor1 = 
				new Productor(this.cola, 1, 2000);
		Productor productor2 = 
				new Productor(this.cola, 2, 2000);
		Consumidor consumidor1 = 
				new Consumidor(this.cola, 1, 500);
		
		Consumidor consumidor2 = 
				new Consumidor(this.cola, 2, 500);
		
		productor1.start();		
		productor2.start();
		consumidor1.start();
		consumidor2.start();
		
		try {
			productor1.join();
			productor2.join();
			consumidor1.join();
			consumidor2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		System.out.println("Fin del caso de prueba: productor más lento "
				+ "y mismos productores y consumidores");
	}
}