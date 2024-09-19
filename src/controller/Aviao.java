package controller;

import java.util.concurrent.Semaphore;
import java.util.Random;
public class Aviao extends Thread {
	
	private Semaphore semaforoNorte;
	private Semaphore semaforoSul;
	private Random rand = new Random();
	private int num;
	private int aviao;
	private String pista;
	
	public Aviao(Semaphore semaforoNorte, Semaphore semaforoSul, int i) {
		this.semaforoNorte = semaforoNorte;
		this.semaforoSul = semaforoSul;
		this.num = rand.nextInt(1,1001);
		this.aviao = i;
	}
	
	private String definePista() {
		if(this.num % 2 == 0) {
			return "norte";
		}else {
			return "sul";
		}
	}
	
	private void manobra() {
		int tempoManobra = rand.nextInt(300,701);
		System.out.println("O aviao " + this.aviao + " está manobrando");
		
		try {
			sleep(tempoManobra);
			System.out.println("O avião " + aviao + " manobrou em " + tempoManobra + "ms.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void taxiar() {
		int tempoTaxi = rand.nextInt(500,1001);
		System.out.println("O avião " + aviao + " está taxiando para a pista " + this.pista);
		
		try {
			sleep(tempoTaxi);
			System.out.println("O avião " + aviao + " foi taxiado em " + tempoTaxi + "ms.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void decolagem() {
		int tempoDecolagem = rand.nextInt(600,801);
		
		System.out.println("O aviao " + aviao + " está decolando pela pista " + this.pista);
		try {
			sleep(tempoDecolagem);
			System.out.println("O avião " + aviao + " decolou em " + tempoDecolagem + "ms. pela pista " + this.pista);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void afastamento() {
		int tempoAfastamento = rand.nextInt(300,801);
		
		System.out.println("O aviao " + aviao + " está se afastando da pista " + this.pista);
		try {
			sleep(tempoAfastamento);
			System.out.println("A fase de afastamento do aviao " + aviao + " foi completa em " + tempoAfastamento + "ms.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.pista = definePista();
		
		if(this.pista.equals("norte")) {
			try {
				semaforoNorte.acquire();
				manobra();
				taxiar();
				decolagem();
				afastamento();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				semaforoNorte.release();
			}
		}else{
			try {
				semaforoSul.acquire();
				manobra();
				taxiar();
				decolagem();
				afastamento();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				semaforoSul.release();
			}
		}
	}
	
}
