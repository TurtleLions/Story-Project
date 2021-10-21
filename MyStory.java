import java.util.Scanner;
public class MyStory{
  private int gems;
  private int health;
  private int weaponStrength;
  private int correctAnswers;
  private int incorrectAnswers;
  /**
  * Contructs MyStory object
  */
  public MyStory() {
    gems = 0;
    health = 100;
    weaponStrength = 1;
    correctAnswers = 0;
    incorrectAnswers = 0;
  }
  /**
  * Returns a random integer
  * @param range The range of possible random numbers
  * @return A random integer generated
  * Precondition: range>0
  */
  public int random(int range){
    return (int)(range*Math.random()+1);
  }
  /**
  * First method that should be called on a MyStory object
  */
  public void start(){
    clearConsole();
    System.out.println("You have entered a dungeon to collect 10 \uD83D\uDC8E.\nYou have heard there are dangerous monsters within.\nOf the 3 doors, which way do you go? (left, right, or straight)");
    System.out.println(" _______|d|_______ \n|\t              |\n|\t              |\n|\t              |\nd\t              d\n|\t              |\n|\t              |\n|\t              |");
    UserInput.getValidInput("left", "right", "straight");
    door();
  }
  /**
  * Method called during MyStory instance that should not be called manually
  * Implements random events
  */
  public void door(){
    clearConsole();
    int r = random(7);
    //gem find
    if(r==1 || r==2){
      gems++;
      System.out.println("You found a \uD83D\uDC8E! You have "+gems+" \uD83D\uDC8E.");
      wait(2000);
      if(gems==3){
        clearConsole();
        System.out.println("Upon finding the third \uD83D\uDC8E, you have a choice.\n1. Regain 30 healthpoints\n2. Increase weapon strength by 3");
        String input = UserInput.getValidInput("1","2");
        if(input.equals("1")){
          health+=30;
        }
        else if(input.equals("2")){
          weaponStrength+=3;
        }
      }
      else if (gems==7){
        clearConsole();
        System.out.println("Final stretch!\nYou have 7 \uD83D\uDC8E.");
        wait(2000);
      }
      else if(gems == 10){
        win();
      }
      hallway();
    }
    //get attacked
    else if(r==3){
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
    //upgrade weapon
    else if(r==4){
      weaponStrength++;
      System.out.println("You find a tool that helps you make a weapon upgrade.\nYour weapon is of " + weaponStrength + " strength.");
      wait(2000);
      hallway();
    }
    //cue MathTest method
    else if(r==5 || r==6 || r==7){
      mathTest();
    }
  }
  /**
  * Method called during MyStory instance that should not be called manually
  * Prints random problem to be solved by user
  * User must input correct answer within 5 seconds
  * Requires any input to finish running method
  * Correctly answered runs mathTestCorrect()
  * Incorrectly answered runs mathTestFail()
  * Took more than 5 seconds to answer runs mathTestFailByTime()
  */
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
    //addition
    if (operator == 1){
      long start = System.currentTimeMillis();
      z = x+y;
      z1 = String.valueOf(z);
      System.out.println("Solve: " + x + " + " + y);
      Timer timer = new Timer();
      timer.start();
      answer = input.nextLine();
      timer.interrupt();
      long stop = System.currentTimeMillis();
      wait(10);
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
    //subtraction
    if (operator == 2){
      long start = System.currentTimeMillis();
      z = x-y;
      z1 = String.valueOf(z);
      System.out.println("Solve: " + x + " - " + y);
      Timer timer = new Timer();
      timer.start();
      answer = input.nextLine();
      timer.interrupt();
      long stop = System.currentTimeMillis();
      wait(10);
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
    //multiplication
    if (operator == 3){
      x = random(12);
      y = random(12);
      long start = System.currentTimeMillis();
      z = x*y;
      z1 = String.valueOf(z);
      System.out.println("Solve: " + x + " * " + y);
      Timer timer = new Timer();
      timer.start();
      answer = input.nextLine();
      timer.interrupt();
      long stop = System.currentTimeMillis();
      wait(10);
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
    //division
    if (operator == 4){
      y = random(9)+1;
      x = random(10)*y;
      z = x/y;
      z1 = String.valueOf(z);
      long start = System.currentTimeMillis();
      System.out.println("Solve: " + x + " / " + y);
      Timer timer = new Timer();
      timer.start();
      answer = input.nextLine();
      long stop = System.currentTimeMillis();
      timer.interrupt();
      wait(10);
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
    input.close();
  }
  /**
  * Method called during MyStory instance that should not be called manually
  * Called when user failed to input correct answer in time 
  * @param correctAnswer Answer user should have inputted
  */
  public void mathTestFailByTime(int correctAnswer){
    incorrectAnswers++;
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
  /**
  * Method called during MyStory instance that should not be called manually
  * Called when user inputs the correct answer on time
  */
  public void mathTestCorrect(){
    correctAnswers++;
    clearConsole();
    System.out.println("\u001b[32mCorrect!\u001b[37m");
    wait(2000);
    hallway();
  }
  /**
  * Method called during MyStory instance that should not be called manually
  * Called when user inputs an incorrect answer on time
  * @param correctAnswer Answer user should have inputted
  */
  public void mathTestFail(int correctAnswer){
    incorrectAnswers++;
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
  /**
  * Method called during MyStory instance that should not be called manually
  * Called when user enters a new hallway
  * Creates hallway picture in console
  * Calls healthBar() to create interface
  */
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
  /**
  * Method called during MyStory instance that should not be called manually
  * Called when user enters left option
  */
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
  /**
  * Method called during MyStory instance that should not be called manually
  * Called when user enters right option
  */
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
  /**
  * Method called during MyStory instance that should not be called manually
  * Called when user enters straight option
  */
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
  /**
  * Method called during MyStory instance that should not be called manually
  * Called when user has collected required amount of gems
  */
  public void win(){
    clearConsole();
    System.out.println("You have successfully collected 10 \uD83D\uDC8E!\nNumber of correct answers: "+correctAnswers+"\nNumber of incorrect answers: "+incorrectAnswers+"\nAccuracy: "+(int)(correctAnswers/(1.0*correctAnswers+incorrectAnswers)*100)+"%");
    System.exit(0);
  }
  /**
  * Method called during MyStory instance that should not be called manually
  * Called when user loses all healthpoints
  */
  public void lose(){
    clearConsole();
    System.out.println("You died. You are now fish food.\nNumber of correct answers: "+correctAnswers+"\nNumber of incorrect answers: "+incorrectAnswers+"\nAccuracy: "+(int)(correctAnswers/(1.0*correctAnswers+incorrectAnswers)*100)+"%");
    System.exit(0);
  }
  /**
  * Method called during MyStory instance that should not be called manually
  * Called every hallway() to update user progress
  */
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
  /**
  * Called when developer wants to clear console
  */
  public static void clearConsole(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  /**
  * Called when developer wants to stop thread for a certain amount of time
  * @param milliseconds Amount of milliseconds to wait
  * Precondition: milliseconds>0
  */
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
}