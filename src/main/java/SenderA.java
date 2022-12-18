import java.util.concurrent.Semaphore;

public class SenderA implements Runnable{

  private final Semaphore semaphore;

  private final MessageQueue messageQueue;

  public SenderA(Semaphore semaphore, MessageQueue messageQueue) {
    this.semaphore = semaphore;
    this.messageQueue = messageQueue;
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      while (true) {
        try {
          messageQueue.checkIfQueueIsFull(semaphore);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        messageQueue.addMsgToQueue(send());
        messageQueue.Recv();
      }
    }
  }

  public char send() {
    return 'A';
  }
}
