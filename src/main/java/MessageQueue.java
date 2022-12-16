
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
    return 0;
  }

  public char[] addMsgToQueue(char msg) {
    while (checkIfQueueIsFull()) {
      full = true;
      checkIfQueueIsFull();
    }
    full = false;
    char[]moreMsg = new char[messages.length + 1];

    //copy the contents of old array
    System.arraycopy(messages, 0, moreMsg, 0, messages.length);
    for (int i = 1; i < moreMsg.length; i ++) {
      moreMsg[moreMsg.length - i] = moreMsg[moreMsg.length - (i+1)];
    }
    moreMsg[0] = msg;
    return moreMsg;
  }

  public char[] removeMsgFromQueue() {
    char[]moreMsg = new char[messages.length - 1];

    //copy the contents of old array
    System.arraycopy(messages, 0, moreMsg, 0, messages.length);
    return moreMsg;
  }

  public boolean checkIfQueueIsFull () {
    return messages.length == maxlength;
  }
}
