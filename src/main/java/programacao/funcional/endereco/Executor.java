package programacao.funcional.endereco;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe auxiliar que gera objetos aleatórios.
 */
public class Executor {

    static String[] paises = new String[]
            {
                    "Brasil"
            };

    static String[] estados = new String[]
            {
                    "Acre - AC / Brasil",
                    "Amazonas - AM / Brasil",
                    "Paraná - PR / Brasil",
                    "Piauí - PI / Brasil",
                    "Rio Grande do Sul - RS / Brasil",
                    "Santa Catarina - SC / Brasil",
                    "São Paulo - SP / Brasil"
            };

    static String[] cidades = new String[]
            {
                    "Foz do Iguacu - PR",
                    "Curitiba - PR",
                    "Três Barras do Paraná - PR",
                    "Barracão - PR",
                    "Santa Terezinha - PR",
                    "Cascavel - PR",
                    "Umuarama - PR",

                    "São Paulo - SP",
                    "Santo André - SP",
                    "Jundiaí - SP",
                    "Santos - SP",
                    "São Bernardo do Campo - SP",
                    "Sorocaba - SP",
                    "Piracicaba - SP",


                    "Florianópolis - SC",
                    "Blumenau - SC",
                    "Itajaí - SC",
                    "Chapecó - SC",
                    "São José - SC",
                    "Bom Jesus - SC",
                    "Navegantes - SC",

                    "Porto Alagre - RS",
                    "Alecrim - RS",
                    "Alpestre - RS",
                    "Araricá - RS",
                    "Barracão - RS",
                    "Bom Jesus - RS",
                    "Pelotas - RS",

                    "Acauã - PI",
                    "Agricolândia - PI",
                    "Altos - PI",
                    "Amarante - PI",
                    "Aroazes - PI",
                    "Bom Jesus - PI",
                    "Arraial - PI",

                    "Rio Branco - AC",
                    "Xapuri - AC",
                    "Feijó - AC",
                    "Tarauacá - AC",
                    "Acrelândia - AC",
                    "Jordão - AC",
                    "Capixaba - AC",

                    "Maués - AM",
                    "Careiro - AM",
                    "Careiro da Várzea - AM",
                    "Borba - AM",
                    "Manaus - AM",
                    "Silves - AM",
                    "Itapiranga - AM"

            };

    public static void main(String[] args) {
//        Executor.testandoForEach();
//        Executor.testandoArraysStream();
//        Executor.testandoMap();
//        Executor.testandoFilter();
//        Executor.testandoFilter2();
//        Executor.testandoFilter3();

        Executor.popularListaDeCidadesNosEstados();
    }

    static void testandoReferenceMethods() {
        Arrays.asList(Executor.cidades)
                .forEach(System.out::println);
    }

    static void testandoForEach() {
        Arrays.asList(Executor.cidades)
                .forEach(System.out::println);
    }

    /**
     * Função adicionada no java 8, para navegação no fluxo de eventos do stream.
     * Ou seja, não é necessário "Array.asList(array).stream, basta utilizar o "Arrays.stream(array)" que a função devolve um fluxo (ou stream)
     */
    static void testandoArraysStream() {
        final Stream stream = Arrays.stream(Executor.cidades)
                .map(cidadeString -> {
                    final Cidade cidade = new Cidade();
                    cidade.setNome(cidadeString.substring(0, cidadeString.indexOf("-")));
                    return cidade;
                });

        final Optional<Cidade> cidade = stream.findAny();

        System.out.println(cidade.get().getNome());
    }

    static void testandoMap() {
        Arrays.stream(Executor.cidades)
                .map(cidadeString -> {
                    final Cidade cidade = new Cidade();
                    cidade.setNome(cidadeString.substring(0, cidadeString.indexOf("-")));
                    return cidade;
                })
                .forEach(cidade -> {
                    System.out.println(cidade.getNome());
                });
    }

    static void testandoMapsEncadeados() {
        Arrays.stream(Executor.cidades)
                .map(cidadeString -> new Cidade(cidadeString.substring(0, cidadeString.indexOf("-")), null))
                .map(Cidade::getNome)
                .forEach(System.out::println);
    }

    static void testandoFilter() {
        Arrays.stream(Executor.cidades)
                .filter(filtro -> filtro.contains("Bom Jesus"))
                .map(cidadeString -> new Cidade(cidadeString, null))
                .map(Cidade::getNome)
                .forEach(System.out::println);
    }

    static void testandoFilter2() {
        Arrays.stream(Executor.cidades)
                .map(cidadeString -> {

                    final Cidade cidade = new Cidade(cidadeString.substring(0, cidadeString.indexOf("-")).trim(),
                            Arrays.stream(Executor.estados)
                                    // Filtrando os estados
                                    .filter(estadoString ->


                                            cidadeString.substring(cidadeString.indexOf("-") + 1, cidadeString.length()).trim()
                                                    .equals(estadoString.substring(estadoString.indexOf("-") + 1, estadoString.indexOf("/")).trim())


                                    )
                                    // Mapeando o estado
                                    .map(estadoString -> new Estado(

                                                    // Seta o nome do estado
                                                    estadoString.substring(0, estadoString.indexOf("-")).trim(),

                                                    // Seta a UF do estado
                                                    estadoString.substring(estadoString.indexOf("-") + 1, estadoString.indexOf("/")).trim(),

                                                    // Seta o Pais do estado
                                                    new Pais(estadoString.substring(estadoString.indexOf("/") + 1, estadoString.length()).trim())

                                            )
                                    )

                                    // Pega o primeiro, só tem um mesmo
                                    .findFirst().get()
                    );

                    return cidade;
                })
                .forEach(cidade -> {
                    System.out.print(cidade.getNome() + " - ");
                    System.out.print(cidade.getEstado().getNome() + " - ");
                    System.out.print(cidade.getEstado().getUf() + " - ");
                    System.out.println(cidade.getEstado().getPais().getNome());
                });
    }

    public static List<Cidade> getCidades(final Integer quantidade) {


        return Executor.getCidades().subList(0, quantidade);
    }

    /**
     * TODO aqui
     *
     * @return
     */
    public static List<Pais> getPaises() {

        return Arrays.stream(Executor.paises)
                // Mapeando jo estado
                .map(paisString -> new Pais(paisString))
                .collect(Collectors.toList());
    }


    public static List<Estado> getEstados() {
        return Arrays.stream(Executor.estados)
                // Mapeando jo estado
                .map(estadoString -> new Estado(

                                // Seta o nome do estado
                                estadoString.substring(0, estadoString.indexOf("-")).trim(),

                                // Seta a UF do estado
                                estadoString.substring(estadoString.indexOf("-") + 1, estadoString.indexOf("/")).trim(),

                                // Seta o Pais do estado
                                new Pais(estadoString.substring(estadoString.indexOf("/") + 1, estadoString.length()).trim())

                        )
                ).collect(Collectors.toList());
    }



    public static List<Cidade> getCidades() {

        Executor.getEstados()
                .stream().map(estado -> {
                    estado.setCidades(Arrays.stream(Executor.cidades)
                            .map(cidadeString -> new Cidade(cidadeString.substring(0, cidadeString.indexOf("-")).trim(), estado))
                            .collect(Collectors.toList()));
                    return estado;
                }
        ).map(estado -> estado.getPais().getEstados())

                .forEach(System.out::println);

        final List<Cidade> cidades = Arrays.stream(Executor.cidades)

                .map(cidadeString ->

                        new Cidade(cidadeString.substring(0, cidadeString.indexOf("-")).trim(),

                                Arrays.stream(Executor.estados)

                                        // Filtrando os estados
                                        .filter(estadoString ->

                                                cidadeString.substring(cidadeString.indexOf("-") + 1, cidadeString.length()).trim()
                                                        .equals(estadoString.substring(estadoString.indexOf("-") + 1, estadoString.indexOf("/")).trim())

                                        )

                                        // Mapeando jo estado
                                        .map(estadoString -> new Estado(

                                                        // Seta o nome do estado
                                                        estadoString.substring(0, estadoString.indexOf("-")).trim(),

                                                        // Seta a UF do estado
                                                        estadoString.substring(estadoString.indexOf("-") + 1, estadoString.indexOf("/")).trim(),

                                                        // Seta o Pais do estado
                                                        new Pais(estadoString.substring(estadoString.indexOf("/") + 1, estadoString.length()).trim())

                                                )
                                        )

                                        // Pega o primeiro, só tem um mesmo
                                        .findFirst().get()
                        )

                )

                .collect(Collectors.toList());

        Collections.shuffle(cidades);

        return cidades;
    }


    static void popularListaDeCidadesNosEstados() {
        final List<Cidade> cidades = Executor.getCidades();

//        cidades.stream().map( cidade -> cidade.getEstado().getCidades().add(cidade));


//        cidades.stream().map(cidade -> cidade.getNome() + " - " + cidade.getEstado().getNome()).forEach(System.out::println);

//        cidades.stream().map(cidade -> cidade.getEstado().getNome() + " " + cidade.getEstado()).forEach(System.out::println);

//        Executor.getEstados().stream().map(estado -> estado.getNome()).forEach(System.out::println);
    }

}
