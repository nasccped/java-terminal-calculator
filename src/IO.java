/**
 * Class for fast printing / inputing with extra feature (left-pad, prompt)
 */
public class IO {

    // our private attributes
    private final int identPadding; // self explanatory

    private int nextPadding; // next padding value (if it's different
                             // from the current one)

    private boolean continueLine; // continueLine (disable gap printing if
                                  // print was used instead of println)

    /**
     * Contructor function
     *
     * @param identPadding must be greater than -1
     * @throws IllegalArgumentException if identPadding less than 0 (negative
     *         padding values aren't allowed)
     */
    public IO(int identPadding) {
        if (!isIdentPaddingValid(identPadding))
            throw new IllegalArgumentException(
                        "identPadding should be greater or equals to 0");
        this.identPadding = identPadding;
        this.nextPadding = -1;
        this.continueLine = false;
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

    private static boolean isIdentPaddingValid(int identPadding) {
        return identPadding > 0;
    }

    /**
     * Generate the left gap whitespace (if in a new line)
     */
    private String getPadString() {
        if (continueLine) return "";
        int padding = getPaddingValue();
        return " ".repeat(padding); // don't use ternary here! This will turn
                                    // the int padding useless (wasted mem)
    }

    /**
     * Unwrap object to be printed into a String
     *
     * @throws IllegalArgumentException if the object doesn't have toString
     *         function implemented
     */
    private String unwrapString(Object obj) {
        String valueResult;
        try {
            valueResult = obj.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException(
                        "Couldn't unwrap String from the value");
        }
        return valueResult;
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
}
