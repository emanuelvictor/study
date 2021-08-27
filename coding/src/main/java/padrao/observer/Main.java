package padrao.observer;

import reactor.core.publisher.Flux;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        final Avisavel general = new AvisavelImpl();

        final AvisadorAvisavelImpl coronel = new AvisadorAvisavelImpl();
        coronel.deveAvisar(general);

        final Avisador sentinela = new AvisadorImpl();
        sentinela.deveAvisar(coronel);

        Flux.range(0, 10)
                .toStream()
                .forEach(index -> sentinela.enviarAviso("aviso " + index));
    }

}