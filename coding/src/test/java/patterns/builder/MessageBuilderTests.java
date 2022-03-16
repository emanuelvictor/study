package patterns.builder;

import org.junit.Test;

public class MessageBuilderTests {

    @Test
    public void teste() {
        final MessageBuilder messageBuilder = new MessageBuilder()
                .logIdKey().equals().to("asdfa").and()
                .dateTime().equals().to("asdfa").and()
                .uaid().equals().to("asdfa");
        System.out.println(messageBuilder.build());
    }
}
