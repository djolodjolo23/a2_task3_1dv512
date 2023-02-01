public interface IMessageQueue {

  boolean send(char msg) throws InterruptedException;

  char Recv() throws InterruptedException;

}
