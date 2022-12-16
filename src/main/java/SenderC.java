import java.util.concurrent.Semaphore;

public class SenderC implements Runnable{

  private final Semaphore semaphore;

  public SenderC(Semaphore semaphore) {
    this.semaphore = semaphore;
  }

  @Override
  public void run() {

  }
}
