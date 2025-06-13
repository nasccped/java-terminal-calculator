package io;

import java.util.Scanner;

/**
 * Class for fast printing / inputing with extra feature (left-pad, prompt)
 */
public class IO {

    private final int identPadding;
    /**
     * Next padding value (if it's different from the current one)
     */
    private int nextPadding;
    /**
     * Disable left-gap printing if print was used instead of println
     */
    private boolean continueLine;
    /**
     * Set default prompt String when using promptedInput methods
     */
    private String defaultPrompt;
    private final Scanner sc = new Scanner(System.in);

    /**
     * Static function that returns the IO object builder
     */
    public static IOBuilder builder() {
        return new IOBuilder();
    }

    /**
     * Contructor function
     *
     * @param ioBuilder the builder object
     * @throws NonSetIdentPadding if identPadding value wasn't set
     * @throws IllegalArgumentException if identPadding less than 0 (negative
     *         padding values aren't allowed)
     */
    protected IO(IOBuilder ioBuilder) throws NonSetIdentPaddingException {
        if (ioBuilder.identPadding == null)
            throw new NonSetIdentPaddingException(
                        "You forgot to set identPadding value (builder)");
        int identPadding = ioBuilder.identPadding;
        if (!isIdentPaddingValid(identPadding))
            throw new IllegalArgumentException(
                        "identPadding should be greater or equals to 0");
        String defaultPrompt = ioBuilder.defaultPrompt;
        if (defaultPrompt == null) defaultPrompt = "";
        this.identPadding = identPadding;
        this.nextPadding = -1;
        this.continueLine = false;
        this.defaultPrompt = defaultPrompt;
    }

    public void println() {
        System.out.println();
        setContinueLine(false);
    }

    public void println(Object obj) {
        System.out.println(generateContent(obj));
        setContinueLine(false);
        resetNextPadding();
    }

    public void print(Object obj) {
        System.out.print(generateContent(obj));
        setContinueLine(true);
    }

    /**
     * Works like Scanner(System.in).nextLine() function
     *
     * @return String readed by the nextLine function
     */
    public String input() {
        System.out.print(getPadString());
        setContinueLine(false);
        return nextLine();
    }

    /**
     * Works the same as above but with the default prompt
     *
     * @return String readed by the nextLine function
     */
    public String promptedInput() {
        System.out.print(getPadString() + defaultPrompt);
        setContinueLine(false);
        return nextLine();
    }

    /**
     * Works the same as above but with a custom prompt
     *
     * @return String readed by the nextLine function
     */
    public String promptedInput(String prompt) {
        System.out.print(getPadString() + prompt);
        setContinueLine(false);
        return nextLine();
    }

    public void clearTerm() { System.out.print("\u001b[2J\u001b[H"); }

    /**
     * Set an specific value to indent padding
     *
     * <p>A different value from identPadding must be set. It will set to
     * nextPadding var (since identPadding is a final value). There's no
     * problem to set a negative value (the value will be ignore, and then,
     * reseted)</p>
     */
    public void setNextPadding(int padding) {
        nextPadding = padding;
    }

    /**
     * Get the correct padding value (identPadding | nextPadding)
     *
     * <p>This function is private. It will only be used within the object. It
     * was created to avoid code repetition</p>
     *
     * @return nextPadding value (if it's set) or the idenPadding const
     */
    private int getPaddingValue() {
        return nextPadding > -1 ? nextPadding : identPadding;
    }

    private void resetNextPadding() {
        nextPadding = -1;
    }

    /**
     * Test if the given integer is a valid identPadding value
     */
    private static boolean isIdentPaddingValid(int identPadding) {
        return identPadding > 0;
    }

    /**
     * Generate the left gap whitespace (if in a new line)
     */
    private String getPadString() {
        return continueLine ? "" : " ".repeat(getPaddingValue());
    }

    /**
     * Unwrap object to be printed into a String
     *
     * @throws IllegalArgumentException if the object doesn't have toString
     *         function implemented
     */
    private String unwrapString(Object obj) {
        try {
            return obj.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException(
                        "Couldn't unwrap String from the value");
        }
    }

    /**
     * <p>This function will only be used by the IO object. it will auto-detect
     * if the line should be continued (print used instead of println)</p>
     */
    private void setContinueLine(boolean value) {
        continueLine = value;
    }

    /**
     * Generate the String content to by printed based on the target object
     *
     * <p>By default, the IO object will never print the literal object. It can
     * only print Strings, so we need to convert the target object to a String
     * by using the toString method. This function will create a StringBuffer
     * wit the initial left padding, append the converted String and then
     * return the buffer converted to a String</p>
     */
    private String generateContent(Object obj) {
        StringBuffer strbuf = new StringBuffer(getPadString());
        strbuf.append(unwrapString(obj));
        return strbuf.toString();
    }

    private String nextLine() {
        return sc.nextLine();
    }
}
