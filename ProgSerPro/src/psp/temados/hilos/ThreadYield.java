package psp.temados.hilos;

import java.io.FileNotFoundException;
import java.io.PrintStream;
/**
 * Análisis de prioridades y yield en 4 hilos
 * @author rafa
 * @version 1.0
 */
public class ThreadYield {
    public static void main(String[] args) {
    	try {
			System.setOut(new PrintStream("yieldout.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	//este hilo es el que necesita mayor tiempo de ejecución, haciendo
    	//yield no se apoderará por completo de la CPU y cederá tiempo de ejecución
    	//a los otros hilos
        YieldPriority yieldPriority1 = new YieldPriority(1000,"Hilo 1",
        		Thread.MIN_PRIORITY);
        
        YieldPriority yieldPriority2 = new YieldPriority(200,"Hilo 2",
        		Thread.NORM_PRIORITY);
        
        YieldPriority yieldPriority3 = new YieldPriority(500,"Hilo 3",0);
        
        YieldPriority yieldPriority4 = new YieldPriority(400,"Hilo 4",Thread.MAX_PRIORITY);
        
        yieldPriority1.startThread();
        yieldPriority2.startThread();
        yieldPriority3.startThread();
        yieldPriority4.startThread();
    }
}
