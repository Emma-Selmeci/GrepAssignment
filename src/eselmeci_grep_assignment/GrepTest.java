package eselmeci_grep_assignment;

public class GrepTest {
    static String s = "";
    static Exception error = null;
    static InputParams p = null;
    static int testNum = 1;

    public static void main(String[] args) {

        //I'm pretty sure there's a MUCH better way of doing this, but I don't have the technical know-how

        String testString;

        /*#########################*/
        /*####Testing setFlags()###*/
        /*#########################*/

        testString = "-a";
        System.out.printf("setFlags() test %d, input \"%s\"\n", testNum++, testString);
        p = new InputParams();
        try {
            InputParser.setFlags(testString,p);
        } catch (InvalidFlagException e) {
            error = e;
        } finally {
            if (error instanceof InvalidFlagException) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        reset();

        testString = "-wc";
        System.out.printf("setFlags() test %d, input \"%s\"\n", testNum++, testString);
        p = new InputParams();
        try {
            InputParser.setFlags(testString,p);
        } catch (InvalidFlagException e) {
            error = e;
        } finally {
            if (error == null && !p.x && !p.n && p.c && !p.i && p.w && !p.v) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        reset();

        testString = "-wcww";
        System.out.printf("setFlags() test %d, input \"%s\"\n", testNum++, testString);
        p = new InputParams();
        try {
            InputParser.setFlags(testString,p);
        } catch (InvalidFlagException e) {
            error = e;
        } finally {
            if (error == null && !p.x && !p.n && p.c && !p.i && p.w && !p.v) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        reset();

        /*#########################*/
        /*###Testing parseInput()##*/
        /*#########################*/

        testNum = 1;
        testString = "";
        System.out.printf("parseInput() test %d, input \"%s\"\n", testNum++, testString);
        try {
            s = InputParser.parseInput(testString);
            p = InputParser.getParams();
        } catch (Exception e) {
            error = e;
        } finally {
            if (error instanceof InvalidInputLayoutException && p == null) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        reset();

        testString = "-";
        System.out.printf("parseInput() test %d, input \"%s\"\n", testNum++, testString);
        try {
            s = InputParser.parseInput(testString);
            p = InputParser.getParams();
        } catch (Exception e) {
            error = e;
        } finally {
            if (error == null && p.pattern.equals("-")) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        reset();

        testString = "-3";
        System.out.printf("parseInput() test %d, input \"%s\"\n", testNum++, testString);
        try {
            s = InputParser.parseInput(testString);
            p = InputParser.getParams();
        } catch (Exception e) {
            error = e;
        } finally {
            if (error == null && p.num == 0 && p.pattern.equals("-3")) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        reset();

        testString = "-3 -c";
        System.out.printf("parseInput() test %d, input \"%s\"\n", testNum++, testString);
        try {
            s = InputParser.parseInput(testString);
            p = InputParser.getParams();
        } catch (Exception e) {
            error = e;
        } finally {
            if (error == null && p.num == 3 && p.pattern.equals("-c")) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        reset();

        testString = "-3 -c a";
        System.out.printf("parseInput() test %d, input \"%s\"\n", testNum++, testString);
        try {
            s = InputParser.parseInput(testString);
            p = InputParser.getParams();
        } catch (Exception e) {
            error = e;
        } finally {
            if (error == null && p.num == 3 && p.c && p.pattern.equals("a")) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        reset();

        testString = "-3 -c -e a";
        System.out.printf("parseInput() test %d, input \"%s\"\n", testNum++, testString);
        try {
            s = InputParser.parseInput(testString);
            p = InputParser.getParams();
        } catch (Exception e) {
            error = e;
        } finally {
            if (error == null && p.num == 3 && p.c && p.pattern.equals("a")) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        reset();

        testString = "-3 -c -e a foo.txt";
        System.out.printf("parseInput() test %d, input \"%s\"\n", testNum++, testString);
        try {
            s = InputParser.parseInput(testString);
            p = InputParser.getParams();
        } catch (Exception e) {
            error = e;
        } finally {
            if (error == null && p.num == 3 && p.c && p.pattern.equals("a") && p.fileNames.length == 1 && p.fileNames[0].equals("foo.txt")) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        reset();

        testString = "a foo.txt bar.txt";
        System.out.printf("parseInput() test %d, input \"%s\"\n", testNum++, testString);
        try {
            s = InputParser.parseInput(testString);
            p = InputParser.getParams();
        } catch (Exception e) {
            error = e;
        } finally {
            if (error == null && p.num == 0 && p.pattern.equals("a") && p.fileNames.length == 2 && p.fileNames[0].equals("foo.txt") && p.fileNames[1].equals("bar.txt")) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

    }

    static void reset() {
        s = "";
        error = null;
        p = null;
        ++testNum;
        System.out.println();
    }
}
