package nasccped.jtc.utils;

public class IO {

    private int leftMargin;

    public IO() {
        this.leftMargin = 0;
    }

    public IO(int lMargin) {
        this.leftMargin = lMargin > 0 ? lMargin : 0;
    }

    public void println() {
        System.out.println();
    }

    public void println(Object value) {
        printMargin();
        System.out.println(value);
    }

    public void print(Object value) {
        printMargin();
        System.out.print(value);
    }

    private void printMargin() {
        System.out.print(" ".repeat(leftMargin));
    }
}
