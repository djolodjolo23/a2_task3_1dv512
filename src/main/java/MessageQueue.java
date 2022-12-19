import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The message queue class.
 * Responsible for receiving messages from sender Threads to the queue.
 * Responsible for sending the messages from the queue to the receiver.
 */
public class MessageQueue implements IMessageQueue, Runnable{

  char[] messages = new char[] {};

  int maxlength = 5;

  AtomicInteger arrayCounter = new AtomicInteger(0);



  boolean full;

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
        if (arrayCounter.get() != 0) {
          send(messages[messages.length - 1]);
          semaphore.release();
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
    if (Recv() == 0) {
    } else {
      msg = Recv();
      receiver.addToMessage(msg);
    }
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
   * @param semaphore is the shared semaphore.
   */
  public void addMsgToQueue(char msg, Semaphore semaphore) throws InterruptedException {
    //checkIfQueueIsFull(semaphore);
    char[]moreMsg = new char[messages.length + 1];
    //copy the contents of old array
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
    //if (messages.length == maxlength) {
      char[] lessMsg = new char[messages.length - 1];
      int index = messages.length - 1;
      System.arraycopy(messages, 0, lessMsg, 0, index);
      System.arraycopy(messages, index + 1, lessMsg, index, messages.length - index - 1);
      messages = lessMsg;
      full = false;
      arrayCounter.decrementAndGet();
    //}
  }

  /**
   * Recursive method to put a thread in a waiting state if the queue is full.
   * TODO: here is the problem
   * @param semaphore is the shared semaphore.
   */
  private void checkIfQueueIsFull (Semaphore semaphore) throws InterruptedException {
    while (arrayCounter.get() == 5) {
      checkIfQueueIsFull(semaphore);
    }
  }

}
