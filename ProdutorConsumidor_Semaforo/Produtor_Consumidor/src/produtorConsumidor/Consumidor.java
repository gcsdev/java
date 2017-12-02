package produtorConsumidor;

public class Consumidor implements Runnable {
	Queue q;

	  Consumidor(Queue q) {
	    this.q = q;
	    new Thread(this, "Consumidor").start();
	  }

	  public void run() {
	    for (int i = 0; i < 20; i++)
	      q.get();
	  }
	
	
	
}
