import java.util.*;
import java.lang.*;

//*******************************************************
// Circle.java
// Clara Watson
// CSCI 145 Project 1
// This Program Simulates The Game "Hangman"
//*******************************************************

// main class
public class Hangman {
    // private static final boolean testingMode = true;
    private static final boolean testingMode = true;
    // you will take care of user interaction under main method
    public static void main(String[] args)
    {
        //sets some general variables
        RandomWord.populateWORD_ARRAY();
        String secretWord;
        int numberOfGuesses;
        int spaceAllowed;
        char guess;
        String positionStr;
        String solve;
        int[] intArr;
        boolean[] placeholderBooleanArr;

        int play = 0;
        boolean menu = true;

        //the general menu thing doing menu things
        //this is the main menu that asks the user what level of dificulty they want to play
        //this corresponds to the amount of guesses the user will have and the number of spaces they can guess wheere the letter is per try
        // each level of dificulty the numebr of guesses you have goes down as well as the spaces you can guess making it harder
        while(menu)
        {
            String difficulty = mainMenu();
            System.out.println();
            //depending on input it will give a certin difficulty
            if(difficulty.equals("e"))
            {
              numberOfGuesses = 15;
              spaceAllowed = 4;
              play++;
            }
            else if(difficulty.equals("i"))
            {
              numberOfGuesses = 12;
              spaceAllowed = 3;
              play++;
            }
            else if(difficulty.equals("h"))
            {
              numberOfGuesses = 10;
              spaceAllowed = 2;
              play++;
            }
            else if(difficulty.equals("q"))
            {
                System.out.println("\t\t  Thank You For Playing");
                break;
            }
            else
            {
                System.out.println("\t\t     Invalid Input");
                continue;
            }

            //create a new word and a array and tell if a char is showing
            //this will get the secret word from the RandomWord file and assign it to a variable
            String word = RandomWord.newWord();
            secretWord = word.toLowerCase();
            boolean[] show = new boolean [secretWord.length()];
            //sets all positions to false ie (hidden)
            for(int i = 0; i < secretWord.length(); i++){
                show[i] = false;
            }
            //this is to help in editing
            //if set to true thecode will display the secret word so that it is easier to debug
            //if set to false then it is in player mode where the secret name will not display
            if(testingMode)
            {
                System.out.println("the secret word is: " + word);
            }
            // this is the loop that will run most of the inputs and calculations for the Arrays
            while(true)
            {
                printString(show,secretWord);
                System.out.println("Guesses Remaining: "+ numberOfGuesses + "\n");
                guess = getChar();

                if(guess == '0')
                {
                    solve = solve();
                    if(solve.equals(secretWord))
                    {
                        System.out.println("You win!");
                        break;
                    }
                    else
                    {
                        System.out.println("That Is Not The Secret Word.");
                        numberOfGuesses--;
                    }
                }
                else
                {
                //while loop checks for a vilid position string

                    while(true)
                    {
                        positionStr = getIntString(spaceAllowed); //asks the user for spaces to check returns a string
                        intArr = getPosition(positionStr,spaceAllowed); //returns an array of valid
                        System.out.println();
                        //this will loop while set to true
                        //the try catch will check to see if the boolean is true and will test for errors
                        //The catch statement will be executed, if an error occurs in the try block

                        if(validPosition(positionStr,spaceAllowed) && checkPos(intArr,secretWord)) //returns a boolean
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("Invalid Input");
                        }
                    }

                    //asks for a set of integers
                    //intArr = getPosition(positionStr,spaceAllowed);
                    //will check if got a wrong guess
                    //calls the checkInside method and will retrun true or false for the if statement
                    if(checkInside(intArr,guess,secretWord))
                    {
                        numberOfGuesses--;
                        System.out.println("Your Guess Is Not In The Word\n");
                    }
                    //will respond with this if it is correct
                    else
                    {
                        System.out.println("Your guess is in the word!");
                    }
                    //update the show array
                    show = updateBoolean(secretWord,intArr,show,guess);

                    //checks if the game end condition is met if there are no more guesses left it will reveal the secret word
                    if(numberOfGuesses == 0)
                    {
                        System.out.println("you have failed to guess the word... : (");
                        break;
                    }

                    //calls the method to check if the win condition is met which returns a boolean value
                    //if they have won than it will print the sercret word and winning message
                    if(checkWin(show))
                    {
                      System.out.println();
                      System.out.println("The word is " + secretWord);
                      System.out.println("Congradulations! You Have Guessed The Word!");
                      break;
                    }
                }
            }

            //Sets the max games played to 20
            if(play == 20)
            {
                System.out.println("All Of The Words Have Been Used");
                System.out.println("Thank You For Playing");
                menu = false;
            }
            else
            {
                continue;
            }
        }
    }


    // this method validate if a string can be parsed to an integer
    // this method takes 1 parameter and returns a boolean:
    //      str - a string
    // when str can be parsed to an integer without any needs of modification
    // return true; otherwise false
    public static boolean isInt(String str)
    {
        if(str.replaceAll("\\s","").matches("[0-9]+") == false)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    // Works as intended.
    // This method checks if the given number of indexs to checks
    // equals the number of indexs the user is allowed to check.
    public static boolean validPosition(String positionStr, int spaceAllowed)
    {
        ArrayList<String> arrl = new ArrayList<String>();
        String [] split = positionStr.split(" ");

        for(int x = 0; x < split.length; x++)
        {
            if(split[x].isEmpty())
            {
                continue;
            }
            else
            {
                arrl.add(split[x]);
            }
        }
        //this returns a boolean value depending on if the if sataement is met
        if(arrl.size() == spaceAllowed)
        {
          for(int x = 0; x < arrl.size(); x++)
          {
              try
              {
                  Integer.parseInt(arrl.get(x));
              }
              catch(Exception e)
              {
                  System.out.println("Invalid Input");
                  return false;
              }
          }
          return true;
        }
        else
        {
            return false;
        }
    }


    // Works as intended.
    // this method converts the validated positions from a string to an int array
    // this method takes 2 parameters and returns an int array
    public static int[] getPosition(String positionStr, int spaceAllowed)
    {
      int [] arr = new int[spaceAllowed];
      String [] split = positionStr.split(" ");

      int y = 0;
      for(int x = 0; x < split.length; x++)
      {
          if(split[x].isEmpty())
          {
              continue;
          }
          else
          {
              arr [y] = Integer.parseInt(split[x].trim());
              y++;
          }
      }
      return arr;
    }

    //Makes sure that the user input is within the length of the string
    public static boolean checkPos(int [] intArr, String secretWord)
    {
        for(int x = 0; x < intArr.length; x++)
        {
            if(intArr[x] > secretWord.length())
            {
                return false;
            }
        }
        return true;
    }

    // This method is functional, need to make it look pretty so all the outputs line up nicely
    public static String mainMenu()
    {
        System.out.println("-------------------------------------------------");
        System.out.println("\t\t       HANGMAN");
        System.out.println("\n");
        //this is the menu that asks the user to input what level they want the game to be and the level corisponds to the amount of guesses they have for the position of each letter
        System.out.println("\t\t      Main Menu\n");
        System.out.println("\t\t       Easy (e)");
        System.out.println("\t\t   Intermediate (i)");
        System.out.println("\t\t       Hard (h)");
        System.out.println("\t\t       Quit (q)\n");
        System.out.println();
        System.out.print("\t      How Would You Like To Play: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine().substring(0,1).toLowerCase();
    }

    //prints a string not including any hidden char
    public static void printString(boolean[] arr,String str)
    {
        String placeHolder = "";
        for(int i = 0; i < str.length(); i++)
        {
            if(arr[i] == true)
            {
                placeHolder = placeHolder + str.charAt(i);
            }
            else
            {
                placeHolder = placeHolder + "-";
            }
        }
        System.out.println("The Word Is: " + placeHolder + "\n");
    }

    // asks user for a char if they input solve will return 0 which is used in
    // the main method to differ from the regular rout
    public static char getChar()
    {
        String placeHolder;
        System.out.println("Please Enter the letter you want to guess, type \"solve\" to solve");
        System.out.print("Guess: ");
        Scanner scan = new Scanner(System.in);
        placeHolder = scan.nextLine();
        if(placeHolder.toLowerCase().equals("solve"))
        {
          return '0';
        }
        else if(placeHolder.trim().charAt(0) == '!' || placeHolder.trim().charAt(0) == '?')
        {
            System.out.println("Invalid Input");
            return getChar();
        }
        else if(isInt(String.valueOf(placeHolder.trim().charAt(0))) == true)
        {
            System.out.println("Invalid Input");
            return getChar();
        }
        else
        {
            return placeHolder.toLowerCase().charAt(0);
        }
    }

    //will check if the win condidion of guessing all char is met
    //returns boolean
    public static boolean checkWin(boolean[] booleanArr)
    {
        for(int i= 0; i < booleanArr.length; i++)
        {
            if(booleanArr[i] == false)
            {
                return false;
            }
        }
        return true;
    }

    //asks user to input a string and returns said string
    // the string should be the word they are guessing to see if it matches the secret word
    public static String getString()
    {
      String placeHolder;
      System.out.print("Please Enter the word you want to guess: ");
      Scanner scan = new Scanner(System.in);
      placeHolder = scan.nextLine();
      return placeHolder;
    }

    // asks user for a string with integers that we will eventualy put into a int[]
    //returns a string if the input is invalid it will call the method again
    public static String getIntString(int spaceAllowed)
    {
        String placeHolder;
        System.out.print("Please enter " + spaceAllowed + " spaces you want to check (separated by spaces): ");

        Scanner scan = new Scanner(System.in);
        placeHolder = scan.nextLine();
        //calls the method that will check to see if the string contains only numbers and stores the return as a boolean
        boolean check = isInt(placeHolder);
        if(check == true)
        {
            return placeHolder;
        }
        else
        {
            System.out.println("Invalid Input");
            return getIntString(spaceAllowed);
        }
    }


    // This is a redundant method. Does what isInt is suppose to do.
    // this method returns true or false if the string entered contains only numbers
    // returns true or false
    public static Boolean getIntStringcheck(String placeHolder)
    {
        if(placeHolder.replaceAll("\\s","").matches("[0-9]+") == false)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //returns a boolean[] that is used with the print string method and checkWin method
    public static boolean[] updateBoolean(String str,int[] intArr, boolean[] booleanArr, char c)
    {
        for(int i=0; i < intArr.length; i++)
        {
            if(str.charAt(intArr[i]) == c)
            {
                booleanArr[intArr[i]] = true;
            }
        }
        return booleanArr;
    }

    //asks for the user to enter the string they want to solve the game with and returns the string they inputed
    public static String solve()
    {
      String placeHolder;
      System.out.print("Please solve the word: ");
      Scanner scan = new Scanner(System.in);
      placeHolder = scan.nextLine();
      return placeHolder;
    }

    //checks to see if there are any changes so it can change the number of guesses
    public static boolean checkInside(int[] arr, char c, String str)
    {
        for(int i=0; i<arr.length;i++)
        {
            if(str.charAt(arr[i]) == c)
            {
                return false;
            }
        }
        return true;
    }
}
