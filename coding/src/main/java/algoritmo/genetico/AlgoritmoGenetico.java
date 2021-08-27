

package algoritmo.genetico;


import algoritmo.Matriz;

import java.util.Arrays;
import java.util.Random;

/**
 * Algoritmo Genético implementado por Emanuel Victor e Haroldo Ramirez em 29/09/14.
 * Análise da influencia da mutação nos crossovers OX e PMX.
 * Algoritmo
 */
public class AlgoritmoGenetico {

    int[][] populacao;
    private int[][] MATRIZ_ADJACENTE = new int[][]{{}};
    // Tamanho da população
    private int TAM_POP;
    //    COM TAXA DE MUTAÇÃO 100% E CROSSOVER 0% O ALGORÍTMO ESTA CONFIGURADO PARA FORÇA BRUTA
    private float TAXA_MUTACAO = 1;
    private int TAXA_CROSSOVER = 100;
    private Crossover CROSSOVER;
    private int FITNESS_A_SER_ENCONTRADO;
    private int elitismo = 0;
    // Número de geraçoẽs (são atributos privados por não são necessários para o acesso externo)
    // SE FOR SETADO COMO NULL, AS GERAÇẼOS SÃO CONSIDERADAS COMO INFINITAS. OU SEJA SÓ VAI PARAR QUANDO ENCONTRAR O FITNESS
    private Integer NUM_GERACOES;
    //variável para somar o fitness
    private int geracaoMelhorFitness;
    private int[] rotaMelhorFitness;
    private int melhorTamPop;
    private int[][] populacaoInicial;
    private boolean IMPRIMIR_EVOLUCAO = false;


    public AlgoritmoGenetico(int TAM_MATRIZ, int TAM_POP) {
        this.MATRIZ_ADJACENTE = Matriz.getMatriz(TAM_MATRIZ);
        this.setFitnessASerEncontrado(Matriz.getFitness(TAM_MATRIZ));
        this.TAM_POP = TAM_POP;
        this.populacao = gerarPopulacaoAleatoria(TAM_POP, MATRIZ_ADJACENTE);
    }

    private static int[][] crossover(final int elitismo, final int roleta, final int[][] populacao, final int TAXA_CROSSOVER, final int[][] MATRIZ_ADJACENTE, final Crossover CROSSOVER) {
        if (CROSSOVER == Crossover.PMX)
            return crossoverPMX(elitismo, roleta, populacao, TAXA_CROSSOVER, MATRIZ_ADJACENTE);
        else
            return crossoverOX(elitismo, roleta, populacao, TAXA_CROSSOVER, MATRIZ_ADJACENTE);
    }

    private static int roleta(int[] fitness) {

        // INVERTENDO VETOR **GAMBIA**
        int[] fitnessAux = new int[fitness.length];

        int cont = 0;
        for (int i = fitness.length - 1; i >= 0; i--) {
            fitnessAux[i] = fitness[cont];
            cont++;
        }

        //fazendo a roleta
        int somatoria = 0;

        for (int j = 0; j < fitness.length; j++) {
            somatoria = somatoria + fitnessAux[j];//this.getMochila(j).getFitness();
        }

        somatoria = new Random().nextInt(somatoria);
        int posicaoEscolhida = -1;
        do {
            posicaoEscolhida++;
            somatoria = somatoria - fitnessAux[posicaoEscolhida];//this.getMochila(posicaoEscolhida).getFitness();
        } while (somatoria > 0);

        return posicaoEscolhida;
    }

    public static int[][] gerarPopulacaoAleatoria(int TAM_POP, int[][] MATRIZ_ADJACENTE) {


        int[][] rotas = new int[TAM_POP][MATRIZ_ADJACENTE.length];

        //Inicializando população inicial
        for (int k = 0; k < TAM_POP; k++) {

            int[] rotaAux = new int[MATRIZ_ADJACENTE.length];
            for (int i = 0; i < MATRIZ_ADJACENTE.length; i++) {
                rotaAux[i] = i;
            }

            int[] rota = new int[rotaAux.length];

            rota = embaralha(rota.length, rota, rotaAux);

            if (!estaContido(rotas, rota)) {
                rotas[k] = rota;
            } else {
                k--;
            }
        }

        return rotas;
    }

    private static int[] embaralha(int tam, int rota[], int rotaAux[]) {
        // Embaralha
        for (int i = 0; i < tam; i++) {
            int r;
            do {
                r = new Random().nextInt(tam - i);
            } while (r == i);

            rota[i] = rotaAux[r];
            rotaAux = preencheVetorSemOR(rotaAux, r);
        }
        return rota;
    }

    private static int[] preencheVetorSemOR(final int[] vetorDeIndices, final int r) {

        int[] vetAux = new int[vetorDeIndices.length - 1];
        int cont = 0;
        for (int j = 0; j < vetorDeIndices.length; j++) {
            if (j == r) {
                continue;
            }
            vetAux[cont] = vetorDeIndices[j];
            cont++;
        }
        return vetAux;
    }

    public static int[] calcularFitness(int[][] populacao, int[][] MATRIZ_ADJACENTE) {
        int[] fitness = new int[populacao.length];
        for (int i = 0; i < populacao.length; i++) {
            fitness[i] = calcularFitness(populacao[i], MATRIZ_ADJACENTE);
        }
        return fitness;
    }

    public static int calcularFitness(int[] individuo, int[][] MATRIZ_ADJACENTE) {
        int fitness = 0;
        for (int j = 0; j < individuo.length; j++) {
            if (j + 1 != individuo.length) {
                fitness = fitness + MATRIZ_ADJACENTE[individuo[j]][individuo[j + 1]];
            } else {
                fitness = fitness + MATRIZ_ADJACENTE[individuo[j]][individuo[0]];
            }
        }
        return fitness;
    }

    // TODO MUDAR PARA O QUICK_SORT
    private static int[][] ordenar(int[][] populacao, int[] fitness) {
        // ordenando
        int i, i2;
        for (i = 0; i < populacao.length; i++) {
            for (i2 = i; i2 < populacao.length; i2++) {
                if (fitness[i] > fitness[i2]) {
                    int vTmp = fitness[i];
                    fitness[i] = fitness[i2];
                    fitness[i2] = vTmp;

                    int[] vvTmp = populacao[i];
                    populacao[i] = populacao[i2];
                    populacao[i2] = vvTmp;
                }
            }
        }
        return populacao;
    }

    private static int[][] crossoverOX(int elitismo, int roleta, int[][] populacao, int TAXA_CROSSOVER, int[][] MATRIZ_ADJACENTE) {

        int[] pai1 /*ou pai selecionado*/ = populacao[roleta];
        for (int c = 0; c < populacao.length - elitismo; c++) {
            if (c != roleta) {
                if (new Random().nextInt(99) < TAXA_CROSSOVER - 1) {

                    int[] pai2 = populacao[c];

                    int[] primeiraMetade = new int[corte(pai1.length, 2)];
                    int[] segundaMetade = new int[corte(pai1.length, 2)];

                    //preenche primeira metade do cromossomo (ou indivíduo) com os genes do pai1
                    System.arraycopy(pai1, 0, primeiraMetade, 0, corte(pai1.length, 2));


                    //preenche segunda metade do cromossomo (ou indivíduo) com os genes (na sequência) do pai2 que não estão no pai1
                    int cont = 0;
                    for (int i : pai2) {
                        if (!estaContido(primeiraMetade, pai2[i])) {
                            segundaMetade[cont] = pai2[i];
                            cont++;
                        }
                    }

                    cont = 0;
                    for (int i = corte(pai1.length, 2); i < pai1.length; i++) {
                        pai1[i] = segundaMetade[cont];
                        cont++;
                    }


                    //preenche segunda metade do cromossomo (ou indivíduo) com os genes do pai2
                    System.arraycopy(pai2, 0, segundaMetade, 0, corte(pai2.length, 2));

                    //preenche primeira metade do cromossomo (ou indivíduo) com os genes (na sequência) do pai1 que não estão no pai2
                    cont = 0;
                    for (int i = 0; i < pai2.length; i++) {
                        if (!estaContido(segundaMetade, pai1[i])) {
                            primeiraMetade[cont] = pai1[i];
                            cont++;
                        }
                    }


                    cont = 0;
                    for (int i = corte(pai2.length, 2); i < pai2.length; i++) {
                        pai2[i] = primeiraMetade[cont];
                        cont++;
                    }


                    //preenche segunda metade do cromossomo (ou indivíduo) com os genes do pai2
                    System.arraycopy(pai1, 0, primeiraMetade, 0, corte(pai1.length, 2));


                    if (!(calcularFitness(pai1, MATRIZ_ADJACENTE) > calcularFitness(populacao[elitismo + c], MATRIZ_ADJACENTE)) && !(calcularFitness(pai2, MATRIZ_ADJACENTE) > calcularFitness(populacao[elitismo + c], MATRIZ_ADJACENTE))) {
                        if (!(estaContido(populacao, pai1)) || !(estaContido(populacao, pai2))) {
                            if (calcularFitness(pai1, MATRIZ_ADJACENTE) < calcularFitness(pai2, MATRIZ_ADJACENTE)) {
                                populacao[elitismo + c] = pai1;
                            } else {
                                populacao[elitismo + c] = pai1;
                            }
                        }
                    }
                }
            }
        }
        return ordenar(populacao, calcularFitness(populacao, MATRIZ_ADJACENTE));
    }

    public static int[][] crossoverPMX(int elitismo, int roleta, int[][] populacao, int TAXA_CROSSOVER, int[][] MATRIZ_ADJACENTE) {


        int[] filho1 /*ou pai selecionado*/ = populacao[roleta];
        for (int c = 0; c < populacao.length - elitismo; c++) {
            if (c != roleta) {
                if (new Random().nextInt(99) < TAXA_CROSSOVER - 1) {

                    int[] filho2 = populacao[c];


                    int[] primeiraParteFilho1 = new int[corte(filho1.length, 3)];
                    int[] primeiraParteFilho2 = new int[corte(filho2.length, 3)];

                    // preenche as primeiras partes
                    for (int i = 0; i < corte(filho1.length, 3); i++) {
                        primeiraParteFilho1[i] = filho1[i];
                        primeiraParteFilho2[i] = filho2[i];
                    }

                    int[] restoFilho1 = new int[filho1.length - (corte(filho1.length, 3))];
                    int[] restoFilho2 = new int[filho2.length - (corte(filho2.length, 3))];

                    int cont = 0;
                    for (int i : filho1) {
                        if (!estaContido(primeiraParteFilho2, filho1[i])) {
                            restoFilho2[cont] = filho1[i];
                            cont++;
                        }
                    }

                    cont = 0;
                    for (int i : filho2) {
                        if (!estaContido(primeiraParteFilho1, filho2[i])) {
                            restoFilho1[cont] = filho2[i];
                            cont++;
                        }
                    }


                    cont = 0;
                    for (int i = primeiraParteFilho1.length; i < filho1.length; i++) {
                        filho1[i] = restoFilho1[cont];
                        cont++;
                    }

                    cont = 0;
                    for (int i = primeiraParteFilho2.length; i < filho2.length; i++) {
                        filho2[i] = restoFilho2[cont];
                        cont++;
                    }

                    if (!(calcularFitness(filho1, MATRIZ_ADJACENTE) > calcularFitness(populacao[elitismo + c], MATRIZ_ADJACENTE)) && !(calcularFitness(filho2, MATRIZ_ADJACENTE) > calcularFitness(populacao[elitismo + c], MATRIZ_ADJACENTE))) {
                        if (!(estaContido(populacao, filho1)) || !(estaContido(populacao, filho2))) {
                            if (calcularFitness(filho1, MATRIZ_ADJACENTE) < calcularFitness(filho2, MATRIZ_ADJACENTE)) {
                                populacao[elitismo + c] = filho1;
                            } else {
                                populacao[elitismo + c] = filho2;
                            }
                        }
                    }
                }
            }
        }
        return ordenar(populacao, calcularFitness(populacao, MATRIZ_ADJACENTE));
    }

    // FAZ O PONTO DE CORTE
    private static int corte(int i, int divisor) {
        if ((i % 2) == 1) {
            return (i / divisor) + 1;
        } else {
            return i / divisor;
        }
    }

    public static int[][] mutacao(int[][] populacao, float TAXA_MUTACAO, int[][] MATRIZ_ADJACENTE) {

        for (int k = 1; k < populacao.length; k++) {
            float r = new Random().nextFloat();
            r = r * 100;
            if (r <= TAXA_MUTACAO) {
                int[] individuoAux;
                do {
                    individuoAux = mutacao(populacao.length, MATRIZ_ADJACENTE);
                } while (estaContido(populacao, individuoAux));
                populacao[populacao.length - 1] = individuoAux;
            }
        }
        return populacao;
    }

    private static int[] mutacao(int TAM_POP, int[][] MATRIZ_ADJACENTE) {
        int[][] populacao = gerarPopulacaoAleatoria(TAM_POP, MATRIZ_ADJACENTE);
        populacao = ordenar(populacao, calcularFitness(populacao, MATRIZ_ADJACENTE));
        int roleta = roleta(calcularFitness(populacao, MATRIZ_ADJACENTE));
        return populacao[/*TAM_POP - (*/roleta /*+ 1)*/];
    }

    private static boolean estaContido(int[] vet, int i) {
        for (int j = 0; j < vet.length; j++) {
            if (vet[j] == i)
                return true;
        }
        return false;
    }

    private static boolean estaContido(int[][] populacao, int individuo[]) {
        for (int k = 0; k < populacao.length; k++) {
            if (Arrays.equals(populacao[k], individuo)) {
                return true;
            }
        }
        return false;
    }

    private static void imprimir(int[][] populacao, int[] fitness, int[][] MATRIZ_ADJACENTE) {
        for (int j = 0; j < populacao.length; j++) {
            System.out.print("Rota " + j + " = ");
            for (int i = 0; i < MATRIZ_ADJACENTE.length; i++) {
                System.out.print(" " + populacao[j][i] + " ");
            }
            System.out.println(" fitness = " + fitness[j]);
        }
    }

    public static void imprimir(int[] rota) {
        System.out.print("A melhor rota encontrada =");
        for (int j : rota) {
            System.out.print(" " + rota[j]);
        }
    }

    public static void imprimir(int[] rotaMelhorFitness, int geracaoMelhorFitness, int melhorFitnes, int melhorTamPop, Crossover melhorCrossover) {
        System.out.println("     A melhor rota seria = 0 1 6 5 2 4 3 15 17 16 14 13 12 11 10 9 8 7; com fitness = 200");
        imprimir(rotaMelhorFitness);
        System.out.println("; com fitness = " + melhorFitnes);
        System.out.println("Geração " + geracaoMelhorFitness);
        System.out.println("Crossover " + melhorCrossover);
        System.out.println("Teste com tamanho de população " + melhorTamPop);

    }

    public void imprimirEvolucao(boolean IMPRIMIR_EVOLUCAO) {
        this.IMPRIMIR_EVOLUCAO = IMPRIMIR_EVOLUCAO;
    }

    public void execute() {
        this.execute(this.populacao);
    }

    //TODO EXECUTE
    public void execute(int[][] populacao) {

        int melhorFitnes = 99999;

        //Vetor que guarda os fitness de fitness (são atributos privados por não são necessários para o acesso externo)
        int[] fitness = calcularFitness(populacao, MATRIZ_ADJACENTE);

        // Faz a verificação de se é null repete infinitamente, se não for null, só faz o número de geraçẽos
        boolean b = false;
        if (NUM_GERACOES == null) {
            b = true;
            NUM_GERACOES = 1;
        }

        for (int j = 0; j < NUM_GERACOES; j++) {

            //Reordena a populaçao pelo fitness
            populacao = ordenar(populacao, fitness);

            if (fitness[0] <= FITNESS_A_SER_ENCONTRADO) {
                System.out.println("Encontrou " + fitness[0] + " na geração " + j);
                break;
            } else if (fitness[0] < melhorFitnes) {
                if (IMPRIMIR_EVOLUCAO) {
                    populacao = ordenar(populacao, fitness);
                    imprimir(populacao[0]);
//                    imprimir(ordenar(populacao, fitness), fitness, MATRIZ_ADJACENTE);
                    System.out.println("Encontrou " + fitness[0] + " na geração " + j);
                }
                geracaoMelhorFitness = j;
                rotaMelhorFitness = ordenar(populacao, fitness)[0];
                melhorFitnes = fitness[0];
                melhorTamPop = populacao.length; //TODO ? deletar
            }


            // Seleciona o melhor indivíduo baseado no fitnesse com a roleta
            int roleta = roleta(fitness);

            // Aplica o crossover do indivíduo selecionado pela roleta
            populacao = crossover(elitismo, roleta, populacao, TAXA_CROSSOVER, MATRIZ_ADJACENTE, Crossover.PMX);

            // Aplica mutaçao
            populacao = mutacao(populacao, TAXA_MUTACAO, MATRIZ_ADJACENTE);

            // Calcula o fitness
            fitness = calcularFitness(populacao, MATRIZ_ADJACENTE);

            /*populacao = */
            populacao = ordenar(populacao, fitness);

            if (b) // Se for true repete infinitamente
                NUM_GERACOES++;

            System.out.println(calcularFitness(populacao, MATRIZ_ADJACENTE)[0]);
        }

    }

    public void setMatrizAdjacente(int[][] MATRIZ_ADJACENTE) {
        this.MATRIZ_ADJACENTE = MATRIZ_ADJACENTE;
    }

    public void setTamanhoDaPopulacao(int TAM_POP) {
        this.TAM_POP = TAM_POP;
    }

    public void setTaxaDeMutacao(int TAXA_MUTACAO) {
        this.TAXA_MUTACAO = /*(TAXA_MUTACAO*10)/100*/TAXA_MUTACAO;
    }

    public Crossover getCrossover() {
        return CROSSOVER;
    }

    public void setCrossover(Crossover CROSSOVER) {
        this.CROSSOVER = CROSSOVER;
    }

    public void setTaxaDeCrossover(int TAXA_CROSSOVER) {
        this.TAXA_CROSSOVER = TAXA_CROSSOVER;
    }

    public void setFitnessASerEncontrado(int FITNESS_A_SER_ENCONTRADO) {
        this.FITNESS_A_SER_ENCONTRADO = FITNESS_A_SER_ENCONTRADO;
    }

    public void setNumeroDeGeracoes(Integer NUM_GERACOES) {
        this.NUM_GERACOES = NUM_GERACOES;
    }

    public void setElitismo(int elitismo) {
        if (elitismo == 0) {
            this.elitismo = elitismo;
        } else {
            this.elitismo = this.TAM_POP / elitismo;
        }
    }

}