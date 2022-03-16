package patterns.builder;

public class MessageBuilder {

    private static final String DATETIME_KEY = "dateTame";
    private static final String LOG_ID_KEY = "LogID";

    protected StringBuilder stringBuilder = new StringBuilder();

    public MessageBuilder() {
    }

    public MessageBuilder(final StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public EqualsBuilder logIdKey() {
        stringBuilder.append(LOG_ID_KEY);
        return new EqualsBuilder(this);
    }

    public EqualsBuilder dateTime() {
        stringBuilder.append(DATETIME_KEY);
        return new EqualsBuilder(this);
    }

    public EqualsBuilder uaid() {
        stringBuilder.append(DATETIME_KEY);
        return new EqualsBuilder(this);
    }

    public EqualsBuilder user() {
        stringBuilder.append(DATETIME_KEY);
        return new EqualsBuilder(this);
    }

    public String build() {
        return stringBuilder.toString();
    }


}
