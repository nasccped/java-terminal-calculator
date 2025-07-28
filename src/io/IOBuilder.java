package io;

/**
 * Builder class for IO object ('Builder' design pattern)
 *
 * <p>All function here are intended to work like a setter before writing the
 * value to the final object (IO). You can find the attributes docs at
 * IO.java</p>
 */
public class IOBuilder {

    protected Integer identPadding = null;
    protected String defaultPrompt = null;

    public IOBuilder identPadding(int identPadding) {
        this.identPadding = identPadding;
        return this;
    }

    public IOBuilder defaultPrompt(String defaultPrompt) {
        this.defaultPrompt = defaultPrompt;
        return this;
    }

    public IO build() throws NonSetIdentPaddingException {
        return new IO(this);
    }
}
