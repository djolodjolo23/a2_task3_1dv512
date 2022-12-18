import java.util.concurrent.Semaphore;

/**
 * The super sender class holding a run method.
 * Inherited by all senders.
 */
public abstract class SuperSender {

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

  /**
   * Sends a message depending on the sender.
   *
   * @param sender is the sender object from the Sender class.
   *
   * @return is the char value depending on the object.
   */
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
