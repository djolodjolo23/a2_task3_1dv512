import java.util.concurrent.Semaphore;

public class SenderB implements Runnable{

  private final Semaphore semaphore;

  public SenderB(Semaphore semaphore) {
    this.semaphore = semaphore;
  }

  @Override
  public void run() {

  }
}
