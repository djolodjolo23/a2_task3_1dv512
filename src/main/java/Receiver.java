public class Receiver implements Runnable {

  private final IMessageQueue queue;

  public Receiver(IMessageQueue queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        System.out.print(queue.Recv());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
