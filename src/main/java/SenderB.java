import java.util.concurrent.Semaphore;

public class SenderB extends SuperSender implements Runnable{

  private final Semaphore semaphore;

  private final MessageQueue messageQueue;

  public SenderB(Semaphore semaphore, MessageQueue messageQueue) {
    this.semaphore = semaphore;
    this.messageQueue = messageQueue;
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      try {
        super.run(semaphore, messageQueue, 'B');
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
