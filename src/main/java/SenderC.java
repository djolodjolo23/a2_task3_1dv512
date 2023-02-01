public class SenderC implements Runnable{

  private final IMessageQueue queue;

  public SenderC(IMessageQueue queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        queue.send('C');
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
