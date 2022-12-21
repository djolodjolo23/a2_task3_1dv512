import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The message queue class.
 * Responsible for receiving messages from sender Threads to the queue.
 * Responsible for sending the messages from the queue to the receiver.
 */
public class MessageQueue implements IMessageQueue, Runnable{

  char[] messages = new char[] {};

  AtomicInteger arrayCounter = new AtomicInteger(0);

  private final Receiver receiver;

  private final Semaphore semaphore;


  public MessageQueue(Receiver receiver, Semaphore semaphore){
    this.receiver = receiver;
    this.semaphore = semaphore;
  }

  public AtomicInteger getArrayCounter() {
    return arrayCounter;
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      while (true) {
        semaphore.acquireUninterruptibly();
        // in case the array is empty, the message queue waits for the first message to arrive.
        if (arrayCounter.get() != 0) {
          send(messages[messages.length - 1]);
          semaphore.release();
          // the receiver gets notified and prints ONLY if the message is sent.
          semaphore.notify();
          removeMsgFromQueue();
        } else {
          semaphore.release();
        }
        try {
          semaphore.wait(200);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }

  }

  @Override
  public void send(char msg) {
    msg = Recv();
    receiver.addToMessage(msg);
  }

  @Override
  public char Recv() {
    return messages[messages.length -1];
  }

  /**
   * Adds a new message to the queue.
   * If queue is full, tells the sender to wait with semaphore.wait();
   *
   * @param msg the message to be sent.
   */
  public void addMsgToQueue(char msg) throws InterruptedException {
    char[]moreMsg = new char[messages.length + 1];
    System.arraycopy(messages, 0, moreMsg, 0, messages.length);
    for (int i = 1; i < moreMsg.length; i ++) {
      moreMsg[moreMsg.length - i] = moreMsg[moreMsg.length - (i+1)];
    }
    moreMsg[0] = msg;
    arrayCounter.incrementAndGet();
    messages = moreMsg;
  }



  /**
   * removes a first message arrived from the queue.
   * Also calls send() so that message is sent before being removed.
   */
  private void removeMsgFromQueue() {
    char[] lessMsg = new char[messages.length - 1];
    int index = messages.length - 1;
    System.arraycopy(messages, 0, lessMsg, 0, index);
    System.arraycopy(messages, index + 1, lessMsg, index, messages.length - index - 1);
    messages = lessMsg;
    arrayCounter.decrementAndGet();
  }
}
