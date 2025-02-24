package eselmeci_grep_assignment;

import java.io.FileNotFoundException;

/**
 * The InputParser class parses the String input (from Grep class or GrepTest) and hands parameters to AnswerGenerator
 */

public class InputParser {
    /**
     * @param input - String to be processed. Expected format is according to assignment, excluding the word grep
     *              The String is split by whitespaces, and as a result, the search pattern or file names cannot include whitespaces
     * @return the output for the program, a possibly multi-line String or an empty String
     * @throws InvalidInputLayoutException
     * @throws NumberFormatException
     * @throws InvalidFlagException
     */
    static InputParams params;

    //For debugging purposes
    static InputParams getParams() {
        return params;
    }


    static String parseInput(String input) throws
            InvalidInputLayoutException,
            NumberFormatException,
            InvalidFlagException,
            FileNotFoundException {
        params = null;
        input = input.trim();
        if(input.isEmpty()) throw new InvalidInputLayoutException("Empty String provided");
        String[] splitInput = input.split("\\s+");

        /*
        If we've reached this point, our splitInput has at least one element.
        If it's one element only, we immediately assume that this is a pattern and the user intends to use grep on the console.
        This means that even if the user only provides a string like "-c" or "foo.txt", we assume that this is the pattern.
         */

        params = new InputParams();
        int counter = 0;

        //Checking for num input
        if(counter < splitInput.length-1 && splitInput[counter].length() > 1 && splitInput[counter].charAt(0) == '-' && splitInput[counter].charAt(1) >= '0' && splitInput[counter].charAt(1) <= '9') {
            params.num = Integer.parseInt(splitInput[counter].substring(1)); //Possibly throwing a NumberFormatException
            ++counter;
        }

        //Checking for flags
        if(counter < splitInput.length-1 && splitInput[counter].length() > 1 && splitInput[counter].charAt(0) == '-') {
            if(splitInput[counter].length() == 2 && splitInput[counter].charAt(1) != 'e') {
                setFlags(splitInput[counter],params); //Extracting the flags (possibly getting an InvalidFlagException)
                ++counter;
                if(splitInput[counter].length() == 2 && splitInput[counter].charAt(0) == '-' && splitInput[counter].charAt(1) == 'e') ++counter; //If the next substring in input it "-e", we assume that the pattern is coming
            } else { //If the next substring in input it "-e", we assume that the pattern is coming
                ++counter;
            }
        }

        //The next substring should be the pattern
        params.pattern = splitInput[counter++];

        //The remaining substrings are the files provided
        params.fileNames = new String[splitInput.length - counter];
        for(int i = 0; counter < splitInput.length; ++i, ++counter) {
            params.fileNames[i] = splitInput[counter];
        }

        return AnswerGenerator.getResult(params);
    }

    /**
     * setFlags() sets the flags of params based on String s
     * @param s - a String starting with '-' followed by
     * @param params
     * @throws InvalidFlagException
     */
    static void setFlags(String s, InputParams params) throws InvalidFlagException {
        for(int i = 1; i < s.length(); ++i) { //Checking only from index 1 to exclude '-'
            switch(s.charAt(i)) {
                case 'v' : {
                    params.v = true;
                } break;
                case 'w' : {
                    params.w = true;
                } break;
                case 'x' : {
                    params.x = true;
                } break;
                case 'n' : {
                    params.n = true;
                } break;
                case 'i' : {
                    params.i = true;
                } break;
                case 'c' : {
                    params.c = true;
                } break;
                default : {
                    throw new InvalidFlagException("Invalid flag provided : " + s.charAt(i));
                }
            }
        }
    }
}

/**
 * InputParams stores the processed data required to evaluate the grep statement
 */

class InputParams {
    int num = 0;
    String pattern;
    String[] fileNames;
    boolean v = false;
    boolean w = false;
    boolean x = false;
    boolean n = false;
    boolean i = false;
    boolean c = false;

    @Override
    public String toString() {
            if(pattern == null) pattern = "[NULL]";
            if(fileNames == null) fileNames = new String[] {"[NULL]"};
            return "num : " + num + "\n" +
                    "pattern : " + pattern + "\n" +
                    "fileName 1 : " + fileNames[0] + "\n" +
                    "v : " + v + " w : " + w + " x : " + x + " n : " + n + " i : " + i + " c : " + c + "\n";

    }
}

class InvalidInputLayoutException extends Exception {
    InvalidInputLayoutException(String errorMessage) {
        super(errorMessage);
    }
}

class InvalidFlagException extends Exception {
    InvalidFlagException(String errorMessage) {super(errorMessage);}
}