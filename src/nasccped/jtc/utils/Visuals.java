package nasccped.jtc.utils;
import java.util.HashMap;

public class Visuals {

    private final String clearEscape = "\u001b[2J\u001b[H";
    private final HashMap<String, String> bodyColorEscapes = new HashMap<>();

    // fill the hashMap on object creation
    public Visuals() {
        bodyColorEscapes.put("reset"  , "\u001b[0m"   );
        bodyColorEscapes.put("red"    , "\u001b[1;31m");
        bodyColorEscapes.put("green"  , "\u001b[1;32m");
        bodyColorEscapes.put("yellow" , "\u001b[1;33m");
        bodyColorEscapes.put("blue"   , "\u001b[1;34m");
        bodyColorEscapes.put("magenta", "\u001b[1;35m");
        bodyColorEscapes.put("cyan"   , "\u001b[1;36m");
        bodyColorEscapes.put("white"  , "\u001b[1;37m");
    }

    public String getBodyColorByName(String name) {
        String result = bodyColorEscapes.get(name.strip().toLowerCase());
        return result == null ? "\u001b[1;33;41m" : result;
    }

    public void clearTerminal() {
        System.out.print(clearEscape);
    }
}
