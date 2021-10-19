import java.util.Scanner;
import java.util.concurrent.atomic.*;
public class GetAnswer extends Thread{
  private String recievedInput = "";
  public void run(){
    Scanner input = new Scanner(System.in);
    recievedInput = input.nextLine();
    AtomicReference<String> setAnswer = new AtomicReference<String>();
    setAnswer.set(recievedInput);
  }
  
}