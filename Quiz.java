import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
    public String getQuestionText() {
        return questionText;
    }
    public List<String> getOptions() {
        return options;
    }
    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}
public class Quiz {
    private List<Question> questions;
    private int score;
    private int currentQuestionIndex;
    private Scanner scanner;
    private Timer timer;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        currentQuestionIndex = 0;
        scanner = new Scanner(System.in);
        timer = new Timer();
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                currentQuestionIndex++;
            }
        }, 20 * 1000); 
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");

        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ":");
            System.out.println(currentQuestion.getQuestionText());

            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ") " + options.get(i));
            }

            System.out.println("Enter your answer (1-" + options.size() + "):");

            startTimer();

            int selectedOption = scanner.nextInt();
            checkAnswer(selectedOption - 1, currentQuestion);
        }

        System.out.println("Quiz ended!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }

    private void checkAnswer(int selectedOptionIndex, Question question) {
        if (selectedOptionIndex == question.getCorrectOptionIndex()) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect! Correct answer was option " + (question.getCorrectOptionIndex() + 1));
        }

        timer.cancel();
        timer.purge();
    }



    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        Question question1 = new Question("What is the capital of France?",
                List.of("London", "Paris", "Berlin", "Rome"), 1);
        Question question2 = new Question("What is the largest planet in our solar system?",
                List.of("Mars", "Jupiter", "Saturn", "Earth"), 1);
        Question question3 = new Question("Who wrote 'To Kill a Mockingbird'?",
                List.of("Harper Lee", "Stephen King", "J.K. Rowling", "Mark Twain"), 0);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        quiz.addQuestion(question3);

        quiz.startQuiz();
    }
}
