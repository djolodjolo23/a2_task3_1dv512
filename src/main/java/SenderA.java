import java.util.concurrent.Semaphore;

/**
 * The sender A class.
 */
public class SenderA extends SuperSender implements Runnable{

  private final Semaphore semaphore;

  private final Semaphore semaphore2;

  private final char whoAmI = 'A';

  private final MessageQueue messageQueue;

  public SenderA(Semaphore semaphore, Semaphore semaphore2, MessageQueue messageQueue) {
    this.semaphore = semaphore;
    this.semaphore2 = semaphore2;
    this.messageQueue = messageQueue;
  }

  @Override
  public void run() {

      synchronized (semaphore) {
        try {
          super.run(semaphore, semaphore2, messageQueue, whoAmI);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }

  }
}
