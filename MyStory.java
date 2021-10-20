import java.util.Scanner;
import java.util.concurrent.atomic.*;
public class MyStory{
  private int gems;
  private int health;
  private int weaponStrength;
  static AtomicReference<String> setAnswer = new AtomicReference<String>();
  public MyStory() {
    gems = 0;
    health = 100;
    weaponStrength = 1;
  }
  public int random(int range){
    return (int)(range*Math.random()+1);
  }
  public void start(){
    clearConsole();
    System.out.println("You have entered a dungeon to collect 10 \uD83D\uDC8E.\nYou have heard there are dangerous monsters within.\nOf the 3 doors, which way do you go? (left, right, or straight)");
    System.out.println(" _______|d|_______ \n|\t              |\n|\t              |\n|\t              |\nd\t              d\n|\t              |\n|\t              |\n|\t              |");
    String input = UserInput.getValidInput("left", "right", "straight");
    door();
  }
  public void door(){
    clearConsole();
    int r = 4;
    if(r==1){
      gems++;
      if (gems == 1){
        System.out.println("You found a \uD83D\uDC8E! You have "+gems+" \uD83D\uDC8E.");
      }
      else{
        System.out.println("You found a \uD83D\uDC8E! You have "+gems+" \uD83D\uDC8E.");
      }
      if(gems == 10){
        win();
      }
      wait(2000);
      hallway();
    }
    else if(r==2){
      int estrength = random(10)+5;
      int lostHp = Math.round(estrength/(int)(Math.sqrt(weaponStrength)));
      health -= lostHp;
      System.out.println("You are attacked by a small fish.");
      if(health>0){
        System.out.println("You lost " + lostHp + " healthpoints.\nYou have " + health + " healthpoints left.");
      }
      else{
        System.out.println("You lost " + lostHp + " healthpoints.");
      }
      wait(2000);
      hallway();
    }
    else if(r==3){
      weaponStrength++;
      System.out.println("You find a tool that helps you make a weapon upgrade.\nYour weapon is of " + weaponStrength + " strength.");
      wait(2000);
      hallway();
    }
    else if(r==4){
      mathTest();
    }
  }
  public void mathTest(){
      System.out.println("Keep in mind you have 5 seconds to answer without penalty.");
      wait(1000);
      int x = random(25);
      int y = random(25);
      int z;
      String z1;
      int operator = random(4);
      Scanner input = new Scanner(System.in);
      String answer = "";
      setAnswer.set(answer);
      if (operator == 1){
        long start = System.currentTimeMillis();
        long stop = System.currentTimeMillis();
        z = x+y;
        z1 = String.valueOf(z);
        System.out.println("Solve: " + x + " + " + y);
        Timer timer = new Timer();
        GetAnswer getAnswer = new GetAnswer();
        timer.start();
        getAnswer.start();
        while(answer.equals("") && stop-start<5000){
          answer = setAnswer.get();
          stop = System.currentTimeMillis();
        }
        timer.interrupt();
        getAnswer.interrupt();
        wait(1);
        if(stop-start>5000){
          mathTestFailByTime(z);
        }
        else if (answer.equals(z1)){
          mathTestCorrect();
        }
        else{
          mathTestFail(z);
        }
      }
      if (operator == 2){
        long start = System.currentTimeMillis();
        long stop = System.currentTimeMillis();
        z = x-y;
        z1 = String.valueOf(z);
        System.out.println("Solve: " + x + " - " + y);
        Timer timer = new Timer();
        GetAnswer getAnswer = new GetAnswer();
        timer.start();
        getAnswer.start();
        while(answer.equals("") && stop-start<5000){
          answer = setAnswer.get();
          stop = System.currentTimeMillis();
        }
        timer.interrupt();
        getAnswer.interrupt();
        wait(1);
        if(stop-start>5000){
          mathTestFailByTime(z);
        }
        else if (answer.equals(z1)){
          mathTestCorrect();
        }
        else{
          mathTestFail(z);
        }
      }
      if (operator == 3){
        x = random(12);
        y = random(12);
        long start = System.currentTimeMillis();
        long stop = System.currentTimeMillis();
        z = x*y;
        z1 = String.valueOf(z);
        System.out.println("Solve: " + x + " * " + y);
        Timer timer = new Timer();
        GetAnswer getAnswer = new GetAnswer();
        timer.start();
        getAnswer.start();
        while(answer.equals("") && stop-start<5000){
          answer = setAnswer.get();
          stop = System.currentTimeMillis();
        }
        timer.interrupt();
        getAnswer.interrupt();
        wait(1);
        if(stop-start>5000){
          mathTestFailByTime(z);
        }
        else if (answer.equals(z1)){
          mathTestCorrect();
        }
        else{
          mathTestFail(z);
        }
      }
      if (operator == 4){
        long start = System.currentTimeMillis();
        long stop = System.currentTimeMillis();
        y = random(9)+1;
        x = random(10)*y;
        z = Math.round(x/y);
        z1 = String.valueOf(z);
        System.out.println("Solve: " + x + " / " + y);
        Timer timer = new Timer();
        GetAnswer getAnswer = new GetAnswer();
        timer.start();
        getAnswer.start();
        while(answer.equals("") && stop-start<5000){
          answer = setAnswer.get();
          stop = System.currentTimeMillis();
        }
        timer.interrupt();
        getAnswer.interrupt();
        wait(1);
        if(stop-start>5000){
          mathTestFailByTime(z);
        }
        else if (answer.equals(z1)){
          mathTestCorrect();
        }
        else{
          mathTestFail(z);
        }
      }
  }
  public void mathTestFailByTime(int correctAnswer){
    clearConsole();
    health-=20;
    if(health>0){
      System.out.println("\u001b[33mToo long!\u001b[37m\nThe correct answer was "+correctAnswer+".\nYou lost 20 healthpoints.\nYou have "+health+" healthpoints left.");
    }
    else{
    System.out.println("\u001b[33mToo long!\u001b[37m\nThe correct answer was "+correctAnswer+".\nYou lost 20 healthpoints.");
    }
    wait(2000);
    hallway();
  }
  public void mathTestCorrect(){
    clearConsole();
    System.out.println("\u001b[32mCorrect!\u001b[37m");
    wait(2000);
    hallway();
  }
  public void mathTestFail(int correctAnswer){
    clearConsole();
    health-=20;
    if(health>0){
      System.out.println("\u001b[31mIncorrect!\u001b[37m\nThe correct answer was "+correctAnswer+".\nYou lost 20 healthpoints.\nYou have "+health+" healthpoints left.");
    }
    else{
    System.out.println("\u001b[31mIncorrect!\u001b[37m\nThe correct answer was "+correctAnswer+".\nYou lost 20 healthpoints.");
    }
    wait(2000);
    hallway();
  }
  public void mathTestF(){
    health-=20;
  }
  public void hallway(){
    clearConsole();
    if(health<=0){
      lose();
    }
    else if (health>100){
      health = 100;
    }
    healthBar();
    int leftr = random(2);
    int straightr = random(2);
    int rightr = random(2);
    String sides = "";
    String leftSide = "";
    String rightSide = "";
    String topSide = "";
    System.out.println("You have entered a hallway.");
    if (straightr == 1){
      topSide = " _______|d|_______ ";
    }
    else {
      topSide = " _______|h|_______ ";
    }
    if (leftr == 1){
      leftSide = "d";
    }
    else {
      leftSide = "h";
    }
    if (rightr == 1){
      rightSide = "d";
    }
    else {
      rightSide = "h";
    }
    sides = topSide + "\n|\t              |\n|\t              |\n|\t              |\n"+leftSide+"\t              "+rightSide+"\n|\t              |\n|\t              |\n|\t              |";
    System.out.println(sides);
    String input = UserInput.getValidInput("left", "right", "straight");
    if(input.equals("left")){
      left(leftr);
    }
    else if(input.equals("right")) {  
      right(rightr);
    }
    else if(input.equals("straight")) {
      straight(straightr);
    }
  }
  public void left(int leftr){
    
    if(leftr==1){
      door();
    }
    else if(leftr==2){
      clearConsole();
      if(health<=30){
        int regen = (int)(random(10)*(100-health)*(0.01));
        health += regen;
        System.out.println("You have regenerated " +regen+ " healthpoints.");
        wait(2000);
      }
      hallway();
    }
  }
  
  public void right(int rightr){
    
    if(rightr==1){
      door();
    }
    else if(rightr==2){
      clearConsole();
      if(health<=30){
        int regen = (int)(random(10)*(100-health)*(0.01));
        health += regen;
        System.out.println("You have regenerated " +regen+ " healthpoints.");
        wait(2000);
      }
      hallway();
    }
    
  }
  public void straight(int straightr){
    
    if(straightr==1){
      door();
    }
    else if(straightr==2){
      clearConsole();
      if(health<=30){
        int regen = (int)(random(10)*(100-health)*(0.01));
        health += regen;
        System.out.println("You have regenerated " +regen+ " healthpoints.");
        wait(2000);
      }
      hallway();
    }
    
  }

  
  
  public void win(){
    clearConsole();
    System.out.println("You have successfully collected 10 \uD83D\uDC8E!");
    System.exit(0);
  }
  public void lose(){
    clearConsole();
    System.out.println("You died. You are now fish food.");
    System.exit(0);
  }
  public void healthBar(){
    int bigHeart = health/10;
    int bar = 0;
    while (bar!=bigHeart){
      System.out.print("\u2764\uFE0F ");
      bar++;
    } 
    if(health%10>=8){
      System.out.print("\u2764\uFE0F");
    }
    else if(health%10>=3&&health%10<=7){
      System.out.print("\uD83D\uDC94");
    }
    System.out.print("\n");
    int gbar = 0;
    while (gbar!=gems){
      System.out.print("\uD83D\uDC8E");
      gbar++;
    }
    System.out.print("\n");
  }
  public static void clearConsole(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  private void wait(int milliseconds)
  {
      try {
          Thread.sleep(milliseconds);
      }
      catch(Exception e)
      {
          e.printStackTrace();
      } 
  }
  static class GetAnswer extends Thread{
    private String recievedInput = "";
    public void run(){
      Scanner input = new Scanner(System.in);
      recievedInput = input.nextLine();
      setAnswer.set(recievedInput);
    }
  }
}