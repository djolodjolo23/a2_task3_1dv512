import java.util.concurrent.Semaphore;

/**
 * The receiver class.
 */
public class Receiver implements Runnable {
  public String theMessage = "";

  private final Semaphore semaphore;

  public Receiver(Semaphore semaphore) {
    this.semaphore = semaphore;
  }


  @Override
  public void run() {
    synchronized (semaphore) {
      while (true) {
        try {
          // waits until notified that the message is sent from the message queue.
          semaphore.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        semaphore.acquireUninterruptibly();
        System.out.print(theMessage);
        semaphore.release();
        semaphore.notify();
      }
    }
  }

  public void addToMessage(char msg) {
    theMessage = "" + msg;
  }
}
