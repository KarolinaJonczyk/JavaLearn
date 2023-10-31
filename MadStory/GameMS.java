import java.util.Random;
import java.util.Scanner;

public class GameMS {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        String playAgain = "";

        String[] storyTemplates = {
            "(color) cat (pastTenseVerb) the most (adjective) dog.",
            "(adjective) Prince (pastTenseVerb) two (color) horses.",
            "The (adjective) capybara (pastTenseVerb) over the (color) moon.",
            "In the land of (color), a (pastTenseVerb) (adjective) raven flew.",
            "Once upon a time, a (color) knight (pastTenseVerb) a (adjective) dragon",
            "Under the (color) sky, the (adjective) deer (pastTenseVerb) through the forest",
            "In a world of (adjective) wonders, a (color) ship (pastTenseVerb) the (adjective) waves",
            "In the middle of the dark night, the (adjective) owl (pastTenseVerb) under the (color) moonlight"
        };

        do {
            int storyId = rand.nextInt(storyTemplates.length);

            System.out.print("Enter one color: ");
            String color = scan.next();

            System.out.print("Enter one past tense verb: ");
            String pastTenseVerb = scan.next();

            System.out.print("Enter one adjective: ");
            String adjective = scan.next();

            String storyTemplate = storyTemplates[storyId];

            String story = storyTemplate
                .replace("(color)", color)
                .replace("(pastTenseVerb)", pastTenseVerb)
                .replace("(adjective)", adjective);

            System.out.println("Generated story: " + story);

            System.out.print("Would you like to play again? (y/n): ");
            playAgain = scan.next();
        } while (playAgain.equalsIgnoreCase("y"));

        System.out.println("Thank you for playing. Bye!");
        scan.close();
    }
}
