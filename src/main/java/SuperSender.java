import java.util.concurrent.Semaphore;

public class SuperSender {

  public void run(Semaphore semaphore, MessageQueue messageQueue, char sender) throws InterruptedException {
    while (true) {
      messageQueue.checkIfIAlreadySentAMessage(sender, semaphore);
      try {
        semaphore.acquire();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      try {
        messageQueue.addMsgToQueue(send(sender), semaphore);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      semaphore.release();
      semaphore.notifyAll();
      try {
        semaphore.wait();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public char send(char sender) {
    switch (sender) {
      case 'A' -> {
        return 'A';
      }
      case 'B' -> {
        return 'B';
      }
      case 'C' -> {
        return 'C';
      }
    }
    return 0;
  }
}
