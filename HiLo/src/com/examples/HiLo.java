package com.examples;
import java.util.Scanner;
public class HiLo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String playAgain = "";
		do {
			int myNum = (int)(Math.random() * 201 - 100);
			//System.out.println( myNum );
			int guess = 0;
			int tries = 0;
			while (guess != myNum) {
				System.out.println("Guess a number between -100 and 100:");
				guess = scan.nextInt();
				tries = tries + 1;
				if (guess < myNum)
					System.out.println(guess + " is too low. Try again.");
				else if (guess > myNum)
					System.out.println(guess + " is too high. Try again.");
				else
					System.out.println("You entered " + guess + ". You win!\nNumber of tries: " + tries + ". Congratulations!");
			}
			System.out.println("Would you like to play again? (y/n)");
			playAgain = scan.next();
		} while (playAgain.equalsIgnoreCase("y"));
		System.out.println("Thank you for playing. Bye!");
		scan.close();
	}
}
