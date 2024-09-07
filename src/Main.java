import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        Scanner scanner = new Scanner(System.in);
        String word, meaning;
        int choice;

        do {
            System.out.println("\nSimple Dictionary");
            System.out.println("1. Add a word");
            System.out.println("2. Lookup a word");
            System.out.println("3. Update a word");
            System.out.println("4. Display all words");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter word: ");
                    word = scanner.nextLine();
                    System.out.print("Enter meaning: ");
                    meaning = scanner.nextLine();
                    dictionary.addWord(word, meaning);
                    System.out.println("Word added successfully!");
                    break;
                case 2:
                    System.out.print("Enter word to lookup: ");
                    word = scanner.nextLine();
                    System.out.println("Meaning: " + dictionary.lookupWord(word));
                    break;
                case 3:
                    System.out.print("Enter word to update: ");
                    word = scanner.nextLine();
                    System.out.print("Enter new meaning: ");
                    meaning = scanner.nextLine();
                    dictionary.updateWord(word, meaning);
                    break;
                case 4:
                    dictionary.displayAllWords();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
