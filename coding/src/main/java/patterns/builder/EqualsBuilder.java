package patterns.builder;

public class EqualsBuilder extends MessageBuilder {

    private static final String EQUALS = "=";

    public EqualsBuilder(MessageBuilder andBuilder) {
        this.stringBuilder = andBuilder.stringBuilder;
    }

    public ToBuilder equals() {
        stringBuilder.append(EQUALS);
        return new ToBuilder(this);
    }
}
