package psp.hilos.espera;

public class Vaso extends Thread {
	private int capacidadMaxima, capcidadActual=0;
	private long milis;
	private boolean vasoLleno=false;
	public Vaso(int capacidadMaxima) {
		this.capacidadMaxima=capacidadMaxima;
	}
	public boolean llenarVaso() {
		this.capcidadActual++;
		try {
			this.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.capcidadActual==this.capacidadMaxima) {	
			this.vasoLleno=true;
			return true;
		}
		else {
			System.out.print("+");
			return false;
		}
	}

	public static void main(String[] args) {		
		Vaso vaso = new Vaso(10);
		vaso.start();
		vaso.milis = System.currentTimeMillis();
		while(!vaso.llenarVaso());	
		synchronized(vaso) {			
			vaso.notify();			
		}
		try {
			vaso.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vaso=null;
	}
	@Override
	public void run() {		
		if(!this.vasoLleno) {
			System.out.println("Esperando a que se llene el vaso");	
			System.out.print("[");
			synchronized(this) {
				try {					
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.print("]");
			System.out.println("\nEl vaso se ha llenado en " + (System.currentTimeMillis()-this.milis)/1000
					+ " segundos");
		}		
	}
}
