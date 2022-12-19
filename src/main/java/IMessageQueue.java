public interface IMessageQueue {

  void send(char msg) throws InterruptedException;

  char Recv() throws InterruptedException;



}
