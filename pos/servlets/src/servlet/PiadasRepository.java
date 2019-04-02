package servlet;

import servlet.domain.Piada;

import java.util.ArrayList;
import java.util.List;

public class PiadasRepository {

    private final List<Piada> todasAsPiadas = new ArrayList<>();

    private PiadasRepository() {
        this.todasAsPiadas.add(new Piada("O que é feito para andar e não anda? A rua."));
        this.todasAsPiadas.add(new Piada("O que dá muitas voltas e não sai do lugar? O relógio."));
        this.todasAsPiadas.add(new Piada("O que tem cabeça e tem dente, não é bicho e nem é gente? O alho."));
        this.todasAsPiadas.add(new Piada("O que não se come, mas é bom para se comer? O talher."));
        this.todasAsPiadas.add(new Piada("O que uma impressora disse para a outra? A primeira impressão é a que fica."));
        this.todasAsPiadas.add(new Piada("O que quanto mais rugas tem mais novo é? O pneu."));
        this.todasAsPiadas.add(new Piada("O que 4 disse para o 40? Passa a bola."));
        this.todasAsPiadas.add(new Piada("O que anda com os pés na cabeça? O piolho."));
        this.todasAsPiadas.add(new Piada("O que quanto mais se tira mais se aumenta? O buraco."));
        this.todasAsPiadas.add(new Piada("O que nasce grande e morre pequeno? O lápis."));
        this.todasAsPiadas.add(new Piada("O que está sempre no meio da rua e de pernas para o ar? A letra \"u\"."));
        this.todasAsPiadas.add(new Piada("O que o cavalo foi fazer no orelhão? Passar um trote."));
        this.todasAsPiadas.add(new Piada("O que nasce a socos e morre a facadas? O pão."));
    }

    private static class PiadasDaoHolder {
        static final PiadasRepository SINGLE_INSTANCE;

        static {
            SINGLE_INSTANCE = new PiadasRepository();
        }
    }

    public static PiadasRepository getInstance() {
        return PiadasDaoHolder.SINGLE_INSTANCE;
    }

    public List<Piada> getTodasAsPiadas() {
        return todasAsPiadas;
    }
}
