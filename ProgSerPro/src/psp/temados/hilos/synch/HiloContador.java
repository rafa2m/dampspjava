package psp.temados.hilos.synch;

class HiloContador extends Thread {
	private Contador contador;
	private int inicio, fin;
	public HiloContador(String nombre, Contador contador) {
		setName(nombre);
		this.contador = contador;
	}
	public void run() {
			this.inicio = contador.getValor();
			this.fin = (contador.getValor()
					+ contador.getMaximo()/5);
			while(contador.getValor()< this.fin)
				contador.incrementaValor();
			
			System.out.println(getName() + " ha contado desde " + inicio +
					" hasta " + fin);
	}
}


