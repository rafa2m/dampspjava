package psp.temados.hilos;
/**
 * Clase para controlar los estados de un hilo
 * @author rafa
 * @version 1.0
 */
public class HiloEstado extends Thread {
	private String nombre;
	private long time;
	public HiloEstado(String nombre, long time) {
		super.setName(nombre);
		this.nombre = nombre;
		this.time = time;
	}
	@Override
	public void run() {
		super.run();
		try {
			System.out.println("El hilo " + this.getName() 
			+ " ha tardado " + (System.currentTimeMillis()-time) +
			 " milisegundos hasta entrar en ejecución");
			sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("El hilo " + this.getName() 
			+ " ha sido interrumpido");
		}
		System.out.println("El hilo " + this.nombre + " con id=" +
		Thread.currentThread().getId() + " está en ejecución");
	}
}
