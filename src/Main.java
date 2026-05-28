import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String path = "src\\Words.txt";
        ArrayList<String> words = new ArrayList<>();

        System.out.println("""
                ***************
                 HANGMAN GAME
                ***************
                """);
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while((line = reader.readLine()) != null){
                words.add(line.trim());
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("Something went wrong!");
        }
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int wrongGuesses = 0;
        char guessLetter;
        ArrayList <Character> wordState = new ArrayList<>();

        String word = words.get(random.nextInt(words.size()));

        for (int i = 0; i< word.length(); i++){
            wordState.add('_');
        }

        while(wrongGuesses < 6){
            System.out.println(handManImage(wrongGuesses));
            for(char c : wordState){
                System.out.print(c + " ");
            }
            System.out.println();
            System.out.print("Enter a character: ");
            guessLetter = scanner.next().toLowerCase().charAt(0);
            if (word.indexOf(guessLetter)>= 0){
                System.out.println("Correct!");
                for (int i = 0; i< word.length(); i++){
                    if(word.charAt(i)==guessLetter) {
                        wordState.set(i, guessLetter);
                    }
                }
                if(!wordState.contains('_')){
                    System.out.println(handManImage(wrongGuesses));
                    System.out.println("You Win!");
                    System.out.println("The word was: " + word);
                    break;
                };

            }
            else {
                wrongGuesses ++;
                System.out.println("Incorrect!");
            }

        }
        if(wrongGuesses == 6){
            System.out.print(handManImage(wrongGuesses));
            System.out.println("Game Over!");
            System.out.println("The word was: " + word);
        }
        scanner.close();
    }

    static String handManImage(int wrongGuess) {
        return switch (wrongGuess) {
            case 0 -> """    
                        
                    """;
            case 1 -> """
                       o   
                          

                    """;
            case 2 -> """
                       o
                       |   
                          

                    """;
            case 3 -> """
                       o
                       |\\  
                          

                    """;
            case 4 -> """
                       o
                      /|\\  
                          
                          
                    """;
            case 5 -> """
                       o
                      /|\\ 
                        \\ 
                          
                    """;
            case 6 -> """
                       o
                      /|\\
                      / \\  
                                               
                    """;
            default -> "";
        };
    }
}