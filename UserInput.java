import java.util.Scanner;
/**
 * Designed by Mr. Shorr. Contains static methods.
 * No need to worry about how it works.
 */
public class UserInput
{
    /**
     * Verifies that one of the strings provided as arguments will be
     * returned.
     * 
     * Example usage:
     * String input = User.getValidInput("yes", "no", "y", "n");
     * 
     * Only yes, no, y, and n are valid inputs. If the user types anything
     * else, they will be prompted to re-enter.
     * 
     */
    public static String getValidInput(String... validInputs)
    {
        String input = "";
        boolean valid = false;
        do
        {
            input = getLine();
            for(String str : validInputs)
            {
                if(input.equals(str))
                    valid = true;
            }
            if(!valid)
                System.out.print("Invalid input. Please try again\n>");
        }
        while(!valid);
        return input;
    }
    
    public static String getLine()
    {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
