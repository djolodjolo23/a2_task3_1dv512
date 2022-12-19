import java.util.concurrent.Semaphore;


public class Main {


  public static void main(String[] args) throws InterruptedException {

    Semaphore semaphore = new Semaphore(1, true);
    Semaphore semaphore2 = new Semaphore(1);
    var receiver = new Receiver(semaphore2);
    MessageQueue messageQueue = new MessageQueue(receiver, semaphore, semaphore2);

    var senderA = new SenderA(semaphore, semaphore2, messageQueue);
    var senderB = new SenderB(semaphore, semaphore2, messageQueue);
    var senderC = new SenderC(semaphore, semaphore2, messageQueue);

    var tq = new Thread(messageQueue);
    var ta = new Thread(senderA);
    var tb = new Thread(senderB);
    var tc = new Thread(senderC);
    var tr = new Thread(receiver);

    ta.start();
    tb.start();
    tc.start();
    tr.start();
    tq.start();




  }
}
