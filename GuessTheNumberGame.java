package task1java;


import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    private static final int MAX_ATTEMPTS = 5; // Limiting the number of attempts
    private static final int MAX_RANGE = 100; // Maximum value for the random number
    private static final int POINTS_PER_ROUND = 10; // Points awarded per round

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int round = 1;
        int totalScore = 0;

        System.out.println("Welcome to the Guess the Number game!");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts per round.");
        System.out.println("Guess a number between 1 and " + MAX_RANGE + ".");

        while (true) {
            System.out.println("\nRound " + round);
            int targetNumber = random.nextInt(MAX_RANGE) + 1;
            int attemptsLeft = MAX_ATTEMPTS;
            int roundScore = 0;

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess (attempt " + (MAX_ATTEMPTS - attemptsLeft + 1) + "): ");
                int guess = scanner.nextInt();

                if (guess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number.");
                    roundScore = POINTS_PER_ROUND * attemptsLeft;
                    totalScore += roundScore;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Try a higher number.");
                } else {
                    System.out.println("Try a lower number.");
                }

                attemptsLeft--;
            }

            if (attemptsLeft == 0) {
                System.out.println("Sorry, you ran out of attempts. The number was " + targetNumber);
            }

            System.out.println("Round Score: " + roundScore);
            System.out.println("Total Score: " + totalScore);

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                System.out.println("Thank you for playing! Final Score: " + totalScore);
                break;
            }

            round++;
        }

        scanner.close();
    }
}

