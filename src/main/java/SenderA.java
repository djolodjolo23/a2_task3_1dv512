import java.util.concurrent.Semaphore;

public class SenderA extends SuperSender implements Runnable{

  private final Semaphore semaphore;

  private final MessageQueue messageQueue;

  public SenderA(Semaphore semaphore, MessageQueue messageQueue) {
    this.semaphore = semaphore;
    this.messageQueue = messageQueue;
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      try {
        super.run(semaphore, messageQueue, 'A');
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
