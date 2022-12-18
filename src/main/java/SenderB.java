import java.util.concurrent.Semaphore;

public class SenderB implements Runnable{

  private final Semaphore semaphore;

  private final MessageQueue messageQueue;

  public SenderB(Semaphore semaphore, MessageQueue messageQueue) {
    this.semaphore = semaphore;
    this.messageQueue = messageQueue;
  }

  @Override
  public void run() {

  }

  public char send(){
    return 'B';
  }
}
