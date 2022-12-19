import java.util.concurrent.Semaphore;


public class Main {


  public static void main(String[] args) {

    Semaphore semaphore = new Semaphore(1, true);
    var receiver = new Receiver();
    MessageQueue messageQueue = new MessageQueue(receiver);

    var senderA = new SenderA(semaphore, messageQueue);
    var senderB = new SenderB(semaphore, messageQueue);
    var senderC = new SenderC(semaphore, messageQueue);


    var ta = new Thread(senderA);
    var tb = new Thread(senderB);
    var tc = new Thread(senderC);
    var tr = new Thread(receiver);

    ta.start();
    tb.start();
    tc.start();
    tr.start();


  }
}
