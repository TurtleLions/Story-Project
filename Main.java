//Remember when we all had to memorize multiplication and division tables!
public class Main {
  /**
  * Main method
  */
  public static void main(String[] args){
    MyStory.clearConsole();
    System.out.println("Welcome to a randomly generated math dungeon.");
    System.out.println("Type enter if you are ready.");
    UserInput.getValidInput("enter");
    MyStory.clearConsole();
    System.out.println("Quick notes:\n\u2022d means door.\n\u2022h means hallway.\n\u2022You have 5 seconds to answer math problems.\n\nType continue to move on.");
    UserInput.getValidInput("continue");
    MyStory fish = new MyStory();
    fish.start();
  }
}
