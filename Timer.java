public class Timer extends Thread {
  public void run(){
      System.out.print("\n \0337 \033[A \r ");
      for(int i=5;i>0;){
        if(i<=3){
          System.out.println("\u001b[31m"+i+"\u001b[37m seconds left!");
        }
        else{
          System.out.println(i+" seconds left!");
        }
        System.out.print("\0338");
        i--;
        try{
          Thread.sleep(1000);
          System.out.print("\0337 ");
          System.out.print("\033[A \r \b ");
        }catch(InterruptedException e){
          MyStory.clearConsole();
          Thread.currentThread().interrupt();
        }
      } 
    System.out.print("\033[2K Time's up! Do your best to answer the question!\0338");
    interrupt();
    }
}