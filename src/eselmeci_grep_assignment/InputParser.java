package eselmeci_grep_assignment;



public class InputParser {
    static String parseInput(String input) throws
            InvalidInputLayoutException,
            NumberFormatException,
            InvalidFlagException {

        input = input.trim();
        if(input.isEmpty()) throw new InvalidInputLayoutException("No pattern provided");
        String[] splitInput = input.split("\\s+");

        /*
        If we've reached this point, our splitInput has at least one element.
        If it's one element only, we immediately assume that this is a pattern and the user intends to use grep on the console.
        This means that even if the user only provides a string like "-c", we assume that this is the pattern.
         */

        InputParams params = new InputParams();
        int counter = 0;

        //Checking for num input
        if(splitInput[counter].charAt(0) == '-' && splitInput[counter].charAt(1) >= '0' && splitInput[counter].charAt(1) <= '9') {
            params.num = Integer.parseInt(splitInput[counter].substring(1));
            ++counter;
        }

        if(splitInput[counter].charAt(0) == '-') {
            if(splitInput[counter].charAt(1) != 'e') {
                setFlags(splitInput[counter],params);
                ++counter;
                if(splitInput[counter].charAt(0) == '-' && splitInput[counter].charAt(1) == 'e') ++counter;
            } else {
                ++counter;
            }
        }

        params.pattern = splitInput[counter++];

        params.fileNames = new String[splitInput.length - counter];
        for(int i = 0; counter < splitInput.length; ++i, ++counter) {
            params.fileNames[i] = splitInput[counter];
        }

        return AnswerGenerator.getResult(params);
    }

    static void setFlags(String s, InputParams params) throws InvalidFlagException {

    }
}

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
}

class InvalidInputLayoutException extends Exception {
    InvalidInputLayoutException(String errorMessage) {
        super(errorMessage);
    }
}

class InvalidFlagException extends Exception {
    InvalidFlagException(String errorMessage) {super(errorMessage);}
}