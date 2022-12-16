import java.util.concurrent.Semaphore;

public class SenderA implements Runnable{

  private final Semaphore semaphore;

  public SenderA(Semaphore semaphore) {
    this.semaphore = semaphore;
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      while (true) {

      }
    }
  }

  private void send(MessageQueue messageQueue) {

  }
}
