import java.util.concurrent.Semaphore;

/**
 * The super sender class holding a run method.
 * Inherited by all senders.
 */
public abstract class SuperSender {


  public void run(Semaphore semaphore1, Semaphore semaphore2, MessageQueue messageQueue, char sender) throws InterruptedException {
    while (true) {
      // This method is not super mandatory, similar effect can be accomplished with
      // semaphore.wait(Timeout millisecond).
      checkIfIAlreadySentAMessage(sender, semaphore1, messageQueue);
      try {
        semaphore1.acquire();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      try {
        messageQueue.addMsgToQueue(send(sender), semaphore1);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      semaphore1.release();
      semaphore1.notifyAll();
      try {
        semaphore1.wait();
        //semaphore.wait(500);
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

  /**
   * A method added for checking if a sender already sent a message in a previous turn.
   * Added to create a better semaphore sharing between the threads.
   *
   * @param msg is the message sent.
   * @param semaphore is the shared semaphore.
   */
  public void checkIfIAlreadySentAMessage(char msg, Semaphore semaphore, MessageQueue messageQueue) throws InterruptedException {
    if (messageQueue.messages.length != 0 && messageQueue.messages[messageQueue.messages.length - 1] == msg) {
      semaphore.wait();
    }
  }
}
