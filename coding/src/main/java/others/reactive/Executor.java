package others.reactive;

import others.functional.address.Estado;
import others.functional.address.Pais;
import others.functional.address.Repository;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Stream;

public class Executor {

    public static void main(String[] args) {
        final Stream<Estado> estadosStream = Arrays.stream(Repository.estados)
                // Mapeando jo estado
                .map(estadoString -> new Estado(

                                // Seta o nome do estado
                                estadoString.substring(0, estadoString.indexOf("-")).trim(),

                                // Seta a UF do estado
                                estadoString.substring(estadoString.indexOf("-") + 1, estadoString.indexOf("/")).trim(),

                                // Seta o Pais do estado
                                new Pais(estadoString.substring(estadoString.indexOf("/") + 1).trim())

                        )
                );

        final Flux<Estado> estadosFlux = Flux.fromStream(estadosStream);

    }
}
