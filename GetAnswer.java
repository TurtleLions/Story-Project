import java.util.Scanner;
public class GetAnswer extends Thread{
  private String recievedInput;
  public void run(){
    Scanner input = new Scanner(System.in);
    recievedInput = input.nextLine();
  }
  public String returnInput(){
    return recievedInput;
  }
}