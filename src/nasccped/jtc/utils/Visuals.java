package nasccped.jtc.utils;

public class Visuals {

    private final String clearEscape = "\u001b[2J\u001b[H";

    public final String resetEscape = "\u001b[0m";
    public final String boldMagenta = "\u001b[1;35m";
    public final String boldWhite = "\u001b[1;37m";

    public void clearTerminal() {
        System.out.print(clearEscape);
    }
}
