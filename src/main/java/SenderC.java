import java.util.concurrent.Semaphore;

public class SenderC implements Runnable{

  private final Semaphore semaphore;

  private final MessageQueue messageQueue;

  public SenderC(Semaphore semaphore, MessageQueue messageQueue) {
    this.semaphore = semaphore;
    this.messageQueue = messageQueue;
  }

  @Override
  public void run() {

  }

  public char send() {
    return 'C';
  }
}
