import java.util.concurrent.Semaphore;

/**
 * The sender C class.
 */
public class SenderC extends SuperSender implements Runnable{

  private final Semaphore semaphore;

  private final char whoAmI = 'C';

  private final MessageQueue messageQueue;

  public SenderC(Semaphore semaphore, MessageQueue messageQueue) {
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
