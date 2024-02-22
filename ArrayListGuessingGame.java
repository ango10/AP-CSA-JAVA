import java.util.ArrayList; //imports ArrayList
import java.util.Scanner; //imports Scanner

class ArrayListGuessingGame {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in); // instantiates scan as a scanner object
    welcome(); // runs welcome() method
    startupMenu(scan); // runs startupMenu(scan) method
    scan.close();
  }

  public static void welcome() { // Welcomes user with "Welcom to the Guessing Game!"
    System.out.println(
        " _    _      _                            _          _   _            _____                     _               _____                        _ ");
    System.out.println(
        "| |  | |    | |                          | |        | | | |          |  __ \\                   (_)             |  __ \\                      | |");
    System.out.println(
        "| |  | | ___| | ___ ___  _ __ ___   ___  | |_ ___   | |_| |__   ___  | |  \\/_   _  ___  ___ ___ _ _ __   __ _  | |  \\/ __ _ _ __ ___   ___  | |");
    System.out.println(
        "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  | __| '_ \\ / _ \\ | | __| | | |/ _ \\/ __/ __| | '_ \\ / _` | | | __ / _` | '_ ` _ \\ / _ \\ | |");
    System.out.println(
        "\\  /\\  /  __/ | (_| (_) | | | | | |  __/ | || (_) | | |_| | | |  __/ | |_\\ \\ |_| |  __/\\__ \\__ \\ | | | | (_| | | |_\\ \\ (_| | | | | | |  __/ |_|");
    System.out.println(
        " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/   \\__|_| |_|\\___|  \\____/\\__,_|\\___||___/___/_|_| |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___| (_)");
    System.out.println(
        "                                                                                                         __/ |          ");
    System.out.println(
        "                                                                                                        |___/           ");
  }

  public static void startupMenu(Scanner scan) {
    System.out.print("Press A to Start, B for Rules, or C to End: "); // asks for user input
    String input = scan.nextLine();
    if (input.equalsIgnoreCase("A")) { // if A or a is selected, game will be played
      playGame(scan); // runs playGame(scan) method
    } else if (input.equalsIgnoreCase("B")) { // if B or b is selected, rules will be presented
      rules(scan);
    } else if (input.equalsIgnoreCase("C")) { // if C or c is selected, game will end
      System.out.println("GAME HAS ENDED");
    } else {
      System.out.println("Invalid input, try again"); // if A, B, nor C are inputted, prompt user again
      startupMenu(scan);
    }
  }

  public static void rules(Scanner scan) { // presents game rules then runs startupMenu(scan)
    System.out.println("\nGame rules:");
    System.out.println("  1. Select a difficulty level (Easy, Medium, Hard).");
    System.out.println("  2. The program will generate a set of random numbers [1,10].");
    System.out.println("  3. Guess all the numbers in the list in as few guesses as possible.");
    System.out.println("  4. To make a guess, enter a number between 1 and 10.");
    System.out.println("  5. After each guess, you will be informed if you are correct or not.");
    System.out.println("  6. You will also be informed if you have already guessed that number before.");
    System.out.println("  7. The game will continue until you have correctly guessed all the numbers in the list.");
    System.out.println(
        "  8. Once you have correctly guessed all the numbers, the program will display a message congratulating you and display how many guesses it took to win the game.");
    System.out.println("  9. You can play the game again by selecting the Start Game option from the menu.\n");
    startupMenu(scan); // runs startupMenu(scan) to ask for user input
  }

  public static void playGame(Scanner scan) {
    boolean gameWon = false;
    while (!gameWon) {
      System.out.println("\nPlease select a difficulty level:"); // select difficulty (1-3)
      System.out.println("  1. Easy (3 numbers to guess correct)");
      System.out.println("  2. Medium (5 numbers to guess correct)");
      System.out.println("  3. Hard (7 numbers to guess correct)");
      System.out.print("Enter your choice: ");
      int difficulty = scan.nextInt();

      ArrayList<Integer> randomList = randomList(difficulty); // instantiates list of 3, 5, or 7 numbers that will have
                                                              // to be guessed

      int guessCount = 0; // instantiates variable that keeps track of amount of guesses
      ArrayList<Integer> guesses = new ArrayList<Integer>(); // instantiates list called guesses that keeps track of
                                                             // numbers that have already been guessed
      boolean validGuess = true;
      while (guesses.size() < randomList.size() || !validGuess) { // while size of list of guesses is less than the
                                                                  // random list that has been generated OR validGuess
                                                                  // is false...
        System.out.print("\nGuess a number between 1 and 10: "); // takes user input between 1-10
        int guess = scan.nextInt();
        scan.nextLine();

        if (guess < 1 || guess > 10) { // if user input is not 1-10 it will prompt invalid guess
          System.out.println("Invalid guess. Number must be between 1 and 10.");
          validGuess = false; // not valid because not in range
        } else if (guesses.contains(guess)) {
          System.out.println("You already guessed that number. Try again."); // if user input is already in guesses
                                                                             // list, it will say it has already been
                                                                             // guessed
          validGuess = false; // not valid because already in guesses list
        } else {
          guessCount++; // increases guessCount variable by 1 when no error occurs

          if (randomList.contains(guess)) { // if user input is in randomList generated...
            System.out.println("Correct! You guessed one of the numbers.");
            guesses.add(guess);
          } else {
            System.out.println("Incorrect. That number is not in the list.");
          }
        }
        validGuess = true; // once a valid guess is entered, validGuess is set back to true so that the
                           // loop can continue checking the next guess if it is valid
      }

      if (guesses.size() == randomList.size()) { // if amount of correct guesses is equal to the size of the randomList
                                                 // then you won
        gameWon = true;
        System.out.print("\nCongratulations! You guessed all the numbers in the list. ");
        System.out.println("It took you " + guessCount + " guesses to win the game.\n");
      }
    }
    startupMenu(scan); // runs again to prompt user on next step
  }

  public static ArrayList<Integer> randomList(int choice) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    int maxNumber = 0;
    if (choice == 1) { // easy makes list of 3 random numbers
      maxNumber = 3;
    } else if (choice == 2) { // medium makes list of 5 random numbers
      maxNumber = 5;
    } else if (choice == 3) { // hard makes list of 7 random numbers
      maxNumber = 7;
    }
    while (list.size() < maxNumber) { // while the size of the random generated list is less than the easy, medium, or
                                      // large corresponding max number...this will run
      int randomNum = (int) (Math.random() * 10) + 1; // number 1-10
      if (!list.contains(randomNum)) { // if random number generated is already in the list it will not add
        list.add(randomNum);
      }
    }
    return list; // return randomly generated list
  }

}