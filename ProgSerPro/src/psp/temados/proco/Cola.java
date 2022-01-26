package psp.temados.proco;

public class Cola {
	private int numero;
	private boolean disponible = false;

	public synchronized int get(int i, int hilo){
		while(!disponible){
			try{
				wait();
			}catch(InterruptedException e){}
		}
		//este es el mensaje que estaba en el consumidor
		//, ahora esta línea esta sincronizada y se ejecuta
		//justo antes de consumir el valor de la cola
		System.out.println(i + "=>Consumidor: " + 
				hilo + ", consume: " + this.numero);
		notify();
		this.disponible = false;
		return this.numero;
	}
	public synchronized void put(int valor, int hilo){
		while(disponible){
			try{
				wait();
			}catch(InterruptedException e){}
		}
		//este es el mensaje que estaba en el consumidor
		//, ahora esta línea esta sincronizada y se ejecuta
		//justo antes de consumir el valor de la cola
		System.out.println(valor + "=>Productor : " 
				+ hilo	+ ", produce: " + this.numero);
		this.numero = valor;
		notify();
		this.disponible = true;
	}
}