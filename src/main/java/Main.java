import java.util.concurrent.Semaphore;

public class Main {


  public static void main(String[] args) throws InterruptedException {

    Semaphore semaphore = new Semaphore(0);
    MessageQueue messageQueue = new MessageQueue();

    var senderA = new SenderA(semaphore, messageQueue);
    var senderB = new SenderB(semaphore, messageQueue);
    var senderC = new SenderC(semaphore, messageQueue);
    var receiver = new Receiver();

    var ta = new Thread(senderA);
    var tb = new Thread(senderB);
    var tc = new Thread(senderC);
    var tr = new Thread(receiver);


    System.out.println(messageQueue.messages);
    messageQueue.removeMsgFromQueue();
    System.out.println(messageQueue.messages);
    messageQueue.addMsgToQueue('A');
    System.out.println(messageQueue.messages);


  }
}
