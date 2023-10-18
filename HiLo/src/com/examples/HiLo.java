package com.examples;
import java.util.Scanner;
public class HiLo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String playAgain = "";
		do {
			int myNum = (int)(Math.random() * 100 + 1);
			//System.out.println( myNum );
			int guess = 0;
			while (guess != myNum) {
				System.out.println("Guess a number between 1 and 100:");
				guess = scan.nextInt();
				if (guess < myNum)
					System.out.println(guess + " is too low. Try again.");
				else if (guess > myNum)
					System.out.println(guess + " is too high. Try again.");
				else
					System.out.println("You entered " + guess + ". You win!");
			}
			System.out.println("Would you like to play again? (y/n)");
			playAgain = scan.next();
		} while (playAgain.equalsIgnoreCase("y"));
		System.out.println("Thank you for playing. Bye!");
		scan.close();
	}
}
