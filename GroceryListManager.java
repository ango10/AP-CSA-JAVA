import java.util.ArrayList;
import java.util.Scanner;

class GroceryListManager {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    ArrayList<String> itemName = new ArrayList<String>();
    ArrayList<Integer> quantities = new ArrayList<Integer>();
    boolean keepGoing = true;
      
    while (keepGoing) {
      System.out.println("\nGrocery List Manager");
      System.out.println("--------------------");
      System.out.println("1. Add items");
      System.out.println("2. Remove items");
      System.out.println("3. View items");
      System.out.println("4. Edit item quantity");
      System.out.println("5. Exit");

      System.out.print("Select Option: ");
      int option = input.nextInt();

      if (option == 1) {
        System.out.print("Enter item name: ");
        String item = input.next();
        itemName.add(item);

        System.out.print("Enter item quantity: ");
        int quantity = input.nextInt();
        quantities.add(quantity);

        System.out.println("Item added successfully.");
      } else if (option == 2) {
        System.out.print("Enter item name to remove: ");
        String item = input.next();
        int index = itemName.indexOf(item);

        if (index == -1) {
          System.out.println("Item not found.");
        } else {
          itemName.remove(index);
          quantities.remove(index);
          System.out.println("Item removed successfully.");
        }
        } else if (option == 3) {
          System.out.println("\nGrocery List");
          System.out.println("------------");
          for (int i = 0; i < itemName.size(); i++) {
            System.out.println(itemName.get(i) + " | " + quantities.get(i));
          }
        } else if (option == 4) {
          System.out.print("Enter item name to edit: ");
          String item = input.next();
          int index = itemName.indexOf(item);

          if (index == -1) {
            System.out.println("Item not found.");
          } else {
            System.out.print("Enter new quantity: ");
            int quantity = input.nextInt();
            quantities.set(index, quantity);
            System.out.println("Item quantity updated successfully.");
          }
          } else if (option == 5) {
            System.out.println("\nFinal Grocery List");
            System.out.println("------------------");
            for (int i = 0; i < itemName.size(); i++) {
              System.out.println(itemName.get(i) + " | " + quantities.get(i));
            }
            keepGoing = false;
          } else {
            System.out.println("Invalid option. Try again.");
          }
        }
    input.close();
    }
}