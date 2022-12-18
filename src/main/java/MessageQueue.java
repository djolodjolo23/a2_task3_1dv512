import java.util.concurrent.Semaphore;

public class MessageQueue implements IMessageQueue{

  char[] messages = new char[] {};

  int maxlength = 5;

  boolean full;

  private Receiver receiver;


  public MessageQueue(Receiver receiver){
    this.receiver = receiver;
  }

  @Override
  public boolean send(char msg) {
    if (Recv() == 0) {
      return false;
    } else {
      msg = Recv();
      receiver.addToMessage(msg);
      return true;
    }
  }

  @Override
  public char Recv() {
    if (messages.length == maxlength) {
      return messages[messages.length -1];
    }
    return 0;
  }

  public void addMsgToQueue(char msg, Semaphore semaphore) throws InterruptedException {
    checkIfQueueIsFull(semaphore);
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
    removeMsgFromQueue();
  }



  public void removeMsgFromQueue() {
    if (messages.length == maxlength) {
      send(messages[messages.length - 1]);
      char[] lessMsg = new char[messages.length - 1];
      int index = messages.length - 1;
      System.arraycopy(messages, 0, lessMsg, 0, index);
      System.arraycopy(messages, index + 1, lessMsg, index, messages.length - index - 1);
      messages = lessMsg;
      full = false;
    }
  }

  public void checkIfQueueIsFull (Semaphore semaphore) throws InterruptedException {
    if (full) {
      semaphore.wait();
      checkIfQueueIsFull(semaphore);
    }
  }

  public void checkIfIAlreadySentAMessage(char msg, Semaphore semaphore) throws InterruptedException {
    while (messages.length != 0 && messages[messages.length - 1] == msg) {
      semaphore.wait();
    }
  }
}
