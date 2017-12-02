package produtorConsumidor;

public class Produtor implements Runnable {
	  Queue q;

	  Produtor(Queue q) {
	    this.q = q;
	    new Thread(this, "Produtor").start();
	  }

	  public void run() {
	    for (int i = 0; i < 20; i++)
	    	 q.put(i);
	  }

}
