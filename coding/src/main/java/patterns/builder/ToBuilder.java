package patterns.builder;

public class ToBuilder extends MessageBuilder {

    public ToBuilder(MessageBuilder andBuilder) {
        this.stringBuilder = andBuilder.stringBuilder;
    }

    public AndBuilder to(final String value) {
        this.stringBuilder.append(value);
        return new AndBuilder(this);
    }

}
