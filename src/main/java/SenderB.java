public class SenderB implements Runnable{

  private final IMessageQueue queue;


  public SenderB(IMessageQueue queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        queue.send('B');
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
