import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue implements IMessageQueue{

  private static final int bufferSizeLimit = 5;

  private int send = 0;

  private int receive = 0;
  private final char[] internalBuffer = new char[bufferSizeLimit];
  private final Semaphore full = new Semaphore(bufferSizeLimit);

  // managing the internal buffer (has messages and is full).
  private final Semaphore messages = new Semaphore(0);

  /**
   * mutualExclusion so one thread at a time can access to the shared resource.
   */
  private final Lock mutualExclusion = new ReentrantLock();


  /**
   * Takes the message and returns boolean true or false, depending on if the buffer is full or not.
   *
   * @return is true or false, depending on the buffer.
   */
  @Override
  public boolean send(char msg) {
    if (!full.tryAcquire()) {
      return false;
    }
    mutualExclusion.lock();
    try {
      internalBuffer[send] = msg;
      send = (send + 1) % bufferSizeLimit;
    } finally {
      mutualExclusion.unlock();
    }
    messages.release();
    return true;
  }


  /**
   * Receive a message.
   *
   * @return is the message.
   */
  public char Recv() {
    messages.acquireUninterruptibly();
    mutualExclusion.lock();
    try {
      char msg = internalBuffer[receive];
      receive = (receive + 1) % bufferSizeLimit;
      return msg;
    } finally {
      mutualExclusion.unlock();
      full.release();
    }
  }
}
