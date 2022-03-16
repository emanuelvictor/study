package patterns.builder;

public class AndBuilder extends MessageBuilder {

    public AndBuilder(MessageBuilder andBuilder) {
        this.stringBuilder = andBuilder.stringBuilder;
    }

    public MessageBuilder and() {
        this.stringBuilder.append(" | ");
        return this;
    }

}
