package br.com.emanuelvictor.domain.model.random;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MyRandomNumber {

    static final String ERROR_BEGIN_LESS_THAN_ZERO = "begin eh menor que zero";
    static final String ERROR_END_LESS_THAN_ZERO = "end eh menor que zero";
    static final String ERROR_BEGIN_LESS_THAN_END = "begin é menor que end";

    private final Set<Integer> history;

    public MyRandomNumber() {
        history = new HashSet<>();
    }

    /**
     * @param begin inicio do intervalo
     * @param end   fim do intervalo
     * @return retornar um numero aleatorio entre [begin, end]
     * o numero aleatorio retornado nao pode ser igual ao anterior
     * @throws IntervaloInvalidoException essa excecao eh lancada quando begin >= end ou (begin<0 || end<0)
     */
    public int nextRandomNumber(int begin, int end) throws IntervaloInvalidoException {
        validateInputs(begin, end);
        return generateRandomNumber(begin, end + 1);
    }

    static void validateInputs(int begin, int end) throws IntervaloInvalidoException {
        if (begin < 0)
            throw new IntervaloInvalidoException(ERROR_BEGIN_LESS_THAN_ZERO);

        if (end < 0)
            throw new IntervaloInvalidoException(ERROR_END_LESS_THAN_ZERO);

        if (begin >= end)
            throw new IntervaloInvalidoException(ERROR_BEGIN_LESS_THAN_END);
    }

    int generateRandomNumber(final int begin, final int end) {
        final int randomNumber = generate(begin, end);
        if (randomNumberGeneratedIsInHistory(randomNumber))
            return generateRandomNumber(begin, end);
        history.add(randomNumber);
        return randomNumber;
    }

    int generate(final int begin, final int end) {
        return new Random().nextInt(end - begin) + begin;
    }

    boolean randomNumberGeneratedIsInHistory(final int randomNumber) {
        return getHistory().stream().anyMatch(numberInHistory -> numberInHistory.equals(randomNumber));
    }

    Set<Integer> getHistory() {
        return history;
    }
}
