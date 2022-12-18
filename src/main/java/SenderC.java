import java.util.concurrent.Semaphore;

public class SenderC extends SuperSender implements Runnable{

  private final Semaphore semaphore;

  private final MessageQueue messageQueue;

  public SenderC(Semaphore semaphore, MessageQueue messageQueue) {
    this.semaphore = semaphore;
    this.messageQueue = messageQueue;
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      try {
        super.run(semaphore, messageQueue, 'C');
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
