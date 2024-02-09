import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int minRange = 1;
        int maxRange = 100;
        int attempts = 5; 
        
        boolean playAgain = true;
        int totalAttempts = 0;
        int roundsWon = 0;
        
        while (playAgain) {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("Guess a number between " + minRange + " and " + maxRange + ".");
            
            int attemptCount = 0;
            boolean guessedCorrectly = false;
            
            while (attemptCount < attempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                
                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    roundsWon++;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
                
                attemptCount++;
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + targetNumber);
            }
            
            totalAttempts += attemptCount;
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next();
            if (!playAgainResponse.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }
        
        System.out.println("Game Over!");
        System.out.println("Total rounds played: " + (playAgain ? roundsWon : roundsWon + 1));
        System.out.println("Total attempts made: " + totalAttempts);
        scanner.close();
    }
}
