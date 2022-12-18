import java.util.concurrent.Semaphore;

public class MessageQueue implements IMessageQueue{

  char[] messages = new char[] {'a', 'b', 'c', 'd', 'e'};

  int maxlength = 5;

  boolean full;


  public MessageQueue(){

  }

  @Override
  public boolean send(char msg) {
    return false;

  }

  @Override
  public char Recv() {
    return messages[messages.length -1];
  }

  public void addMsgToQueue(char msg) {
    if (messages.length + 1 == maxlength) {
      full = true;
    }
    char[]moreMsg = new char[messages.length + 1];
    //copy the contents of old array
    System.arraycopy(messages, 0, moreMsg, 0, messages.length);
    for (int i = 1; i < moreMsg.length; i ++) {
      moreMsg[moreMsg.length - i] = moreMsg[moreMsg.length - (i+1)];
    }
    moreMsg[0] = msg;
    messages = moreMsg;
  }



  public void removeMsgFromQueue() {
    char[]moreMsg = new char[messages.length - 1];
    int index = messages.length - 1;
    //copy the contents of old array
    System.arraycopy(messages, 0, moreMsg, 0, index);
    System.arraycopy(messages, index + 1, moreMsg, index, messages.length - index - 1);
    messages = moreMsg;
  }

  public void checkIfQueueIsFull (Semaphore semaphore) throws InterruptedException {
    if (full) {
      semaphore.wait();
      checkIfQueueIsFull(semaphore);
    }
  }
}
