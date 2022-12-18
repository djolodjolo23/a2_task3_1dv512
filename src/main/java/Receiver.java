public class Receiver implements Runnable {



  public String theMessage= "";

  public Receiver() {

  }

  @Override
  public void run() {
    while (true) {
      System.out.print(theMessage + " ");
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void addToMessage(char msg) {
    theMessage = "" + msg;
  }
}
