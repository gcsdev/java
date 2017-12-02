package produtorConsumidor;


public class ProdutorConsumidor_Semaforo {
  public static void main(String args[]) {
    Queue q = new Queue();
    new Consumidor(q);
    new Produtor(q);
  }
}
