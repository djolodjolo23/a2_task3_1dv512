import java.util.concurrent.Semaphore;


public class Main {


  public static void main(String[] args) {

    IMessageQueue queue = new MessageQueue();

    SenderA senderA = new SenderA(queue);
    SenderB senderB = new SenderB(queue);
    SenderC senderC = new SenderC(queue);
    Receiver receiver = new Receiver(queue);

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
