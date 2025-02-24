package eselmeci_grep_assignment;

import java.io.*;
import java.util.regex.Pattern;

/**
 * AnswerGenerator is handled an InputParams class, and is responsible for generating a possibly multi-line String or throwing an error
 *
 */

//https://stackoverflow.com/questions/3436118/is-java-regex-case-insensitive

public class AnswerGenerator {

    static InputParams params;
    static BufferedReader[] br;
    static boolean isInConsoleMode;
    static Pattern pattern;

    static String getResult(InputParams params) throws FileNotFoundException {
        AnswerGenerator.params = params;
        openResources();
        makePattern();

        String nextLine;

        try {
            while(true) {
                nextLine = getNextLine();
                processLine(nextLine);
            }
        } catch(NoMoreLineException ignored) {}

        closeResources();
        return makeAnswer();
    }

    /**
     * openResources sets isInConsoleMode to t/f depending on if file names were provided
     * If file names are provided, it attempts to open the files
     * @throws FileNotFoundException if a file from params.fileNames cannot be opened
     */
    private static void openResources() throws FileNotFoundException {
        int numFiles = params.fileNames.length;

        if(numFiles == 0) { //If no file was provided, we're going to use console mode
            isInConsoleMode = true;
        } else {
            isInConsoleMode = false;
            br = new BufferedReader[numFiles];
            for(int i = 0; i < numFiles; ++i) {
                try {
                    br[i] = new BufferedReader(new FileReader(params.fileNames[i]));
                } catch (FileNotFoundException e) {
                    throw new FileNotFoundException("Could not open file : " + params.fileNames[i]);
                } finally {
                    closeResources();
                }
            }
        }
    }

    /**
     * makePattern handles the -x, -w and -i flags by modifying the regex pattern
     */
    private static void makePattern() {
        String regex = params.pattern;
        if(params.x) {
            //Java regex matches for whole lines by deault
        } else {
            if(params.w) {
                regex = "((^)|(.*\\s))" + regex + "(($)|(\\s.*))"; //this took me an embarassingly long time to figure out
            } else {
                regex = ".*" + regex + ".*"; //by default, we match the whole line even if only a substring of it matches
            }
        }

        //There is an optional argument for case insensitive matching
        if(params.i) {
            pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            pattern.compile(regex);
        }

    }

    private static String getNextLine() throws NoMoreLineException {
        return "";
    }

    private static void processLine(String line) {

    }

    private static String makeAnswer() {
        return "";
    }

    private static void closeResources() {
        for(int i = 0; i < br.length; ++i) {
            try {
                if(br[i] != null) br[i].close();
            } catch (IOException e) {
                //We can't do much here, and files not closing properly is not a huge problem
            }
        }
    }

    private class NoMoreLineException extends Exception {}

}
