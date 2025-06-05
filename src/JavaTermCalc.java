/**
 * This file was generated using kojamp CLI-app
 * Take a look at the official repository (https://github.com/nasccped/kojamp)
 */

public class JavaTermCalc {

    private static String turnGreen(String text) {
        return "\u001b[92m" + text + "\u001b[0m";
    }

    private static String turnRed(String text) {
        return "\u001b[91m" + text + "\u001b[0m";
    }

    private static void println(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) {
        String projectName = "JavaTermCalc";
        println("Welcome to " + turnGreen(projectName));
        println("");
        println("Let's do some " + turnRed("maths") + " >:^D");
    }

}
