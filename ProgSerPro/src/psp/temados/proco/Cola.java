package psp.temados.proco;
/**
 * Objeto compartido entre productores y consumidores
 * @author rafa
 * @version 1.0
 */
public class Cola {
	private int numero;
	private boolean disponible = false;

	public synchronized int get(int i, int hilo){
		while(!disponible){
			try{
				wait();
			}catch(InterruptedException e){}
		}

		notify();
		this.disponible = false;
		//este es el mensaje que estaba en el consumidor
		//, ahora esta línea esta sincronizada y se ejecuta
		//justo antes de consumir el valor de la cola
		System.out.println(i + "=>Consumidor: " + 
				hilo + ", consume: " + this.numero);
		return this.numero;
	}
	public synchronized void put(int valor, int hilo){
		while(disponible){
			try{
				wait();
			}catch(InterruptedException e){}
		}		
		this.numero = valor;
		notify();
		this.disponible = true;
		//este es el mensaje que estaba en el consumidor
		//, ahora esta línea esta sincronizada y se ejecuta
		//justo antes de consumir el valor de la cola
		System.out.println(valor + "=>Productor : " 
				+ hilo	+ ", produce: " + this.numero);
	}
}