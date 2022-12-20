import java.util.concurrent.Semaphore;


public class Main {


  public static void main(String[] args) throws InterruptedException {

    Semaphore senderSemaphore = new Semaphore(1, true);
    Semaphore queueAndReceiverSemaphore = new Semaphore(1);

    var receiver = new Receiver(queueAndReceiverSemaphore);
    var messageQueue = new MessageQueue(receiver, queueAndReceiverSemaphore);

    var senderA = new SenderA(senderSemaphore, messageQueue);
    var senderB = new SenderB(senderSemaphore, messageQueue);
    var senderC = new SenderC(senderSemaphore, messageQueue);

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

    ta.join();
    tb.join();
    tc.join();
    tr.join();
    tq.join();


  }
}
