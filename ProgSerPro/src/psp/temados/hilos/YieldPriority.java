package psp.temados.hilos;

import java.time.LocalTime;
/**
 * Hilo que cederá su tiempo de ejecución en CPU para que otro hilo
 * entre. Los hilos con mayor duración cederán más veces la CPU para
 * que el resto de hilos pueda terminar su trabajo
 * @author rafa
 *
 */
public class YieldPriority implements Runnable{
	private long simulatedRuntime;
	private Thread thread;
	public YieldPriority(long simulatedRuntime,String name, int priority) {
		this.simulatedRuntime = simulatedRuntime;
		this.thread = new Thread(this);
		if(priority>0)
			this.thread.setPriority(priority);
		this.thread.setName(name);
		System.out.println(this.thread
				.getName() + ", preparado para ejecutar durante " + 
				(float)this.simulatedRuntime/1000
				+ " segundos");
	}
	@Override
	public void run() {
		long counter = System.currentTimeMillis();
		long instant=counter;
		while (System.currentTimeMillis()-counter < this.simulatedRuntime) {
			if(instant!=System.currentTimeMillis()) {
				System.out.println(this.thread
						.getName() + ";" 
						+ this.thread.getPriority()
						+ ";" + LocalTime.now());
				System.out.println(this.thread
						.getName() + ";" 
						+ this.thread.getPriority()
						+ ";0");
			}
			instant = System.currentTimeMillis();
			this.thread.yield();
		}
		System.out.println(this.thread
				.getName() + ", ha finalizado ");
	}
	public void startThread() {
		this.thread.start();
	}
}
