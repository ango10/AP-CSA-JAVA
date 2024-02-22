import java.util.Scanner;

class TicTacToe {
  public static void main(String[] args) {
    game();
  }

  public static void game() {
    //instantiates scanner object
    Scanner input = new Scanner(System.in);
    //instantiates 2D array
    String[][] board = {
        { " ", "1", "2", "3" },
        { "1", " ", " ", " " },
        { "2", " ", " ", " " },
        { "3", " ", " ", " " }
    };

    //creates table using | & --
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 4; col++) {
        System.out.print(board[row][col]);
        if (col != 2 || col != 3) {
          System.out.print(" | ");
        }
      }
      System.out.println();
      if (row != 2 || row != 3) {
        System.out.println("---------------");
      }
    }
    
    //establishes which player is what symbol.
    System.out.println("Player 1 will be O");
    System.out.println("Player 2 will be X");
    //establishes the objective and rules of the game prior to the start of the game.
    System.out.println("\nInstructions:");
    System.out.println("The objective of the game is to get 3 symbols in a row.\nThis can be done by filling a row, column, or diagonal.\nUsers will take turns placing their designated symbol.\nIf no one wins, the game will result in a tie.\n");

    //asks the user what row/column to place O and put symbol in that location. if there is already a symbol there it continues asking for another location.
    boolean validMove = false;
    for (int i = 0; i < 5; i++) { //loops 5 times because 9 possible spots. Since 5x2=10 then it will allow for all locations to be filled with a symbol.
      // player 1 move
      validMove = false;
      while (!validMove) { //while validMove is false
        System.out.print("Player 1, select the row you would like to place (1-3): ");
        int oneRow = input.nextInt();
        System.out.print("Player 1, select the column you would like to place (1-3): ");
        int oneCol = input.nextInt();
        //if oneRow is less than 1 or greater than 3 OR oneCol is less than 1 or greater than 3
        if (oneRow < 1 || oneRow > 3 || oneCol < 1 || oneCol > 3){
          System.out.println("\nInvalid input. Please select a row and column between 1 and 3.\n");
        }else if (board[oneRow][oneCol].equals(" ")) {
          board[oneRow][oneCol] = ColorCodes.BLUE_BACKGROUND + "O" + ColorCodes.ANSI_RESET;
          validMove = true;
        } else {
          System.out.println("\nPosition already taken. Please select another position.\n");
        }
      }
      printBoard(board);

      //if winner is equal to a string then it concatenates the string with wins!. if checkWinner returns nothing then it continues the for loop.
      String winner = checkWinner(board);
      if (winner != "") {
        System.out.println(winner + " wins!");
        return;
      }

      // player 2 move
      validMove = false;
      //asks the user what row/column to place X and put symbol in that location. If there is already a symbol there it continues asking for another location.
      while (!validMove) { //while validMove is false
        System.out.print("Player 2, select the row you would like to place (1-3): ");
        int twoRow = input.nextInt();
        System.out.print("Player 2, select the column you would like to place (1-3): ");
        int twoCol = input.nextInt();
        //if twoRow is less than 1 or greater than 3 OR twoCol is less than 1 or greater than 3
        if (twoRow < 1 || twoRow > 3 || twoCol < 1 || twoCol > 3) {
          System.out.println("\nInvalid input. Please select a row and column between 1 and 3.\n");
        } else if (board[twoRow][twoCol].equals(" ")) {
          board[twoRow][twoCol] = ColorCodes.RED_BACKGROUND + "X" + ColorCodes.ANSI_RESET;
          validMove = true;
        } else {
          System.out.println("\nPosition already taken. Please select another position.\n");
        }
      }
      printBoard(board);

      //if winner is equal to a string then it concatenates the string with wins!. if checkWinner returns nothing then it continues the for loop.
      winner = checkWinner(board);
      if (winner != "") {
        System.out.println(winner + " wins!");
        return;
      }
    }
  }
 
  //prints out the updated board after each user has placed a symbol on their turn.
  public static void printBoard(String[][] board) {
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 4; col++) {
        System.out.print(board[row][col]);
        if (col != 2 || col != 3) {
          System.out.print(" | ");
        }
      }
      System.out.println();
      if (row != 2 || row != 3) {
        System.out.println("---------------");
      }
    }
  }

  public static String checkWinner(String[][] board) {
    String winner = "";
    boolean tie = true;

    // checks column
    for (int row = 1; row <= 3; row++) {
      if (board[row][1].equals(board[row][2]) && board[row][2].equals(board[row][3])) {
        if (board[row][1].equals(ColorCodes.BLUE_BACKGROUND + "O" + ColorCodes.ANSI_RESET)) {
          winner = "Player 1";
        } else if (board[row][1].equals(ColorCodes.RED_BACKGROUND + "X" + ColorCodes.ANSI_RESET)) {
          winner = "Player 2";
        }
      }
    }

    // checks row
    for (int col = 1; col <= 3; col++) {
      if (board[1][col].equals(board[2][col]) && board[2][col].equals(board[3][col])) {
        if (board[1][col].equals(ColorCodes.BLUE_BACKGROUND + "O" + ColorCodes.ANSI_RESET)) {
          winner = "Player 1";
        } else if (board[1][col].equals(ColorCodes.RED_BACKGROUND + "X" + ColorCodes.ANSI_RESET)) {
          winner = "Player 2";
        }
      }
    }

    // top left to bottom right
    if (board[1][1].equals(board[2][2]) && board[2][2].equals(board[3][3])) {
      if (board[1][1].equals(ColorCodes.BLUE_BACKGROUND + "O" + ColorCodes.ANSI_RESET)) {
        winner = "Player 1";
      } else if (board[1][1].equals(ColorCodes.RED_BACKGROUND + "X" + ColorCodes.ANSI_RESET)) {
        winner = "Player 2";
      }
    }

    // bottom left to top right
    if (board[3][1].equals(board[2][2]) && board[2][2].equals(board[1][3])) {
      if (board[3][1].equals(ColorCodes.BLUE_BACKGROUND + "O" + ColorCodes.ANSI_RESET)) {
        winner = "Player 1";
      } else if (board[3][1].equals(ColorCodes.RED_BACKGROUND + "X" + ColorCodes.ANSI_RESET)) {
        winner = "Player 2";
      }
    }

    //check for tie. tie starts as true everytime and only turns false when empty space is present.
    for (int row = 1; row <= 3; row++) {
      for (int col = 1; col <= 3; col++) {
        if (board[row][col].equals(" ")) {
          tie = false;
        }
      }
    }
    //if winner is equal to nothing and tie is true, then game ends in a tie. if this is not the case, winner is returned.
    if (winner.equals("") && tie) {
      return "Game ends in a tie, no one";
    } else {
      return winner;
    }
  }
  
}