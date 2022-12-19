import java.util.concurrent.Semaphore;


public class Main {


  public static void main(String[] args) throws InterruptedException {

    Semaphore semaphore = new Semaphore(1, true);

    Semaphore semaphore2 = new Semaphore(1);
    var receiver = new Receiver(semaphore2);
    MessageQueue messageQueue = new MessageQueue(receiver, semaphore2);

    var senderA = new SenderA(semaphore, messageQueue);
    var senderB = new SenderB(semaphore, messageQueue);
    var senderC = new SenderC(semaphore, messageQueue);

    var tq = new Thread(messageQueue);
    tq.start();

    var ta = new Thread(senderA);
    var tb = new Thread(senderB);
    var tc = new Thread(senderC);
    var tr = new Thread(receiver);


    ta.start();
    tb.start();
    tc.start();
    tr.start();

    ta.join();
    tb.join();
    tc.join();
    tr.join();
    tq.join();


  }
}
