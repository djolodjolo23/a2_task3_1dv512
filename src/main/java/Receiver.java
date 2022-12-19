import java.util.concurrent.Semaphore;

/**
 * The receiver class.
 */
public class Receiver implements Runnable {
  public String theMessage = "";

  public Semaphore semaphore;

  public Receiver(Semaphore semaphore) {
    this.semaphore = semaphore;
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      while (true) {
        try {
          semaphore.acquire();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.print(theMessage + " ");
        semaphore.release();
        try {
          semaphore.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }



  public void addToMessage(char msg) {
    theMessage = "" + msg;
  }
}

