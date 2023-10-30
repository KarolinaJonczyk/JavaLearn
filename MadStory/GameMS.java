import java.util.Scanner;

public class GameMS {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String playAgain = "";

        do {
            System.out.print("Enter one color: ");
            String color = scan.next();

            System.out.print("Enter one past tense verb: ");
            String pastTenseVerb = scan.next();

            System.out.print("Enter one adjective: ");
            String adjective = scan.next();

            String story = color + " cat " + pastTenseVerb + " the most " + adjective + " dog";
            System.out.println("Generated story: " + story);

            System.out.println("Would you like to play again? (y/n)");
            playAgain = scan.next();
        } while (playAgain.equalsIgnoreCase("y"));

        System.out.println("Thank you for playing. Bye!");
        scan.close();
    }
}
