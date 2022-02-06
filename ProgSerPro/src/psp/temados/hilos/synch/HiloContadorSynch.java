package psp.temados.hilos.synch;
/**
 * Hilo con objeto sincronizado
 * @author rafa
 *
 */
class HiloContadorSynch extends Thread {
	private Contador contador;
	private int inicio, fin;
	public HiloContadorSynch(String nombre, Contador contador) {
		setName(nombre);
		this.contador = contador;
	}
	public void run() {
		synchronized(contador){
			this.inicio = contador.getValor();
			this.fin = (contador.getValor()
					+ contador.getMaximo()/5);
			while(contador.getValor()< this.fin)
				contador.incrementaValor();
			
			System.out.println(getName() + " ha contado desde " + inicio +
					" hasta " + fin);
		}
	}
}

