package eselmeci_grep_assignment;

public class GrepTest {
    static String s = "";
    static Exception error = null;

    public static void main(String[] args) {

        //I'm pretty sure there's a MUCH better way of doing this but I don't have the technical know-how
        int testNum = 1;
        String testString = "";
        System.out.printf("Test %d, input \"%s\"\n", testNum++, testString);
        try {
            s = InputParser.parseInput("");
        } catch (Exception e) {
            error = e;
        } finally {
            if (error instanceof InvalidInputLayoutException) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        reset();

    }

    static void reset() {
        s = "";
        error =null;
        System.out.println();
    }
}
