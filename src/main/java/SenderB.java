import java.util.concurrent.Semaphore;

/**
 * The sender B class.
 */
public class SenderB extends SuperSender implements Runnable{

  private final Semaphore semaphore;

  private final char whoAmI = 'B';
  private final MessageQueue messageQueue;

  public SenderB(Semaphore semaphore, MessageQueue messageQueue) {
    this.semaphore = semaphore;
    this.messageQueue = messageQueue;
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      try {
        super.run(semaphore, messageQueue, whoAmI);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
