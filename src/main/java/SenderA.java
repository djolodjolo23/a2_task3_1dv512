import java.util.concurrent.Semaphore;

/**
 * The sender A class.
 */
public class SenderA extends SuperSender implements Runnable{

  private final Semaphore semaphore;

  private final char whoAmI = 'A';

  private final MessageQueue messageQueue;

  public SenderA(Semaphore semaphore, MessageQueue messageQueue) {
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
