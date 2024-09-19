package view;
import controller.Aviao;
import java.util.concurrent.Semaphore;
public class Main {

	public static void main(String[] args) {
		
		Semaphore semaforoNorte = new Semaphore(1);
		Semaphore semaforoSul = new Semaphore(1);
		
		for(int i = 0; i < 12; i++) {
			Thread aviao = new Aviao(semaforoNorte, semaforoSul, (i+1));
			aviao.start();
		}
	}

}
