import java.util.Scanner;
import java.util.concurrent.*;
public class GetAnswer extends Thread{
  private String recievedInput;
  public void run(){
    Scanner input = new Scanner(System.in);
    recievedInput = input.nextLine();
  }
  public void returns() throws InterruptedException, ExecutionException{
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Callable<String> callable = new Callable<String>(){
      @Override
      public String call(){
        return recievedInput;
      }
    };
    Future<String> future = executor.submit(callable);
    executor.shutdown();
  }
}