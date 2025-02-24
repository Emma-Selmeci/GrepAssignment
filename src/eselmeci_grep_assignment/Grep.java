package eselmeci_grep_assignment;


import java.io.*;

public class Grep {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //Creating a separate function so that InputParser can "borrow" the BufferedReader
    static String readNextLine() throws IOException {
        return br.readLine();
    }

    //Grep takes command-line arguments like the Linux version
    public static void main(String[] args) {

        String input = "";
        String output = "";

        while(true) {
            System.out.print("Enter input or 'q' to quit : grep ");
            try {
                 input = readNextLine();
            } catch(IOException e) {
                System.out.println("\n An IO Exception has occured");
                continue;
            }

            System.out.println(); //Line break for readability

            if(input.equals("q")) break;

            try {
                output = InputParser.parseInput(input);
                System.out.print(output);
            } catch(Exception e) { //We catch all types of Exceptions and print the message
                System.out.println(e.getMessage());
            }

            System.out.println(); //Line break for readability

        }

    }
}