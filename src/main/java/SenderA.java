
public class SenderA implements Runnable{

  private final IMessageQueue queue;

  public SenderA(IMessageQueue queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        queue.send('A');
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
