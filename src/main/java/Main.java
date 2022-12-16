import java.util.concurrent.Semaphore;

public class Main {


  public static void main(String[] args) throws InterruptedException {

    Semaphore semaphore = new Semaphore(0);

    var senderA = new SenderA(semaphore);
    var senderB = new SenderB(semaphore);
    var senderC = new SenderC(semaphore);
    var receiver = new Receiver();
    MessageQueue messageQueue = new MessageQueue();

    var ta = new Thread(senderA);
    var tb = new Thread(senderB);
    var tc = new Thread(senderC);
    var tr = new Thread(receiver);


    System.out.println(messageQueue.messages.length);
    System.out.println(messageQueue.messages);
    System.out.println(messageQueue.removeMsgFromQueue());


  }
}
