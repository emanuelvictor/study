package com.emanuelvictor.java1.exercicios.exercicios5;

import com.emanuelvictor.java1.exercicios.exercicios5.application.exceptions.VeicExistException;
import com.emanuelvictor.java1.exercicios.exercicios5.application.exceptions.VelocException;
import com.emanuelvictor.java1.exercicios.exercicios5.domain.entity.Carga;
import com.emanuelvictor.java1.exercicios.exercicios5.domain.entity.Passeio;
import com.emanuelvictor.java1.exercicios.exercicios5.domain.service.VeiculoService;
import com.emanuelvictor.java1.exercicios.exercicios5.infrastructure.Leitura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Emanuel Victor De Oliveira Fonseca
 */
public class Teste {

    private final VeiculoService veiculoService = new VeiculoService();

    private final List<String> opcoes = new ArrayList<>(
            Arrays.asList(
                    "| 1) Cadastrar Veículo de Passeio",
                    "| 2) Cadastrar Veículo de Carga",
                    "| 3) Imprimir Todos os Veículos de Passeio",
                    "| 4) Imprimir Todos os Veículos de Carga",
                    "| 5) Imprimir Veículo de Passeio pela Placa",
                    "| 6) Imprimir Veículo de Carga pela Placa",
                    "| 7) Alterar dados do Veículo de Passeio pela Placa",
                    "| 8) Alterar dados do Veículo de Carga pela Placa",
                    "| 9) Sair do Sistema")
    );

    public static void main(final String args[]) throws IllegalAccessException, NoSuchFieldException {
        System.out.println("| ------------------------------------------------------------------ |");
        System.out.println("|            Sistema de Gestão de Veículos - Menu Inicial            |");
        System.out.println("| ------------------------------------------------------------------ |");
        final Teste teste = new Teste();
        teste.exibirMenu();

    }

    private static void showClock() {
        System.out.println("| ------------------------------------------------------------------ |");
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("|                                                " + LocalDateTime.now().format(formatter) + " |");
    }

    private int exibirMenu() {
        this.opcoes.forEach(System.out::println);

        final String entrada = Leitura.inDados("| Informe a opção desejada: ");

        final int opcao = Leitura.trataInDadosNumericos(entrada);

        if (opcao > 0 && opcao < 10) {
            this.escolherOpcao(opcao);
            return opcao;
        } else {
            System.out.println("| **** Opção não encontrada");
            return exibirMenu();
        }
    }

    private void escolherOpcao(int option) {

        this.opcoes.stream()
                .filter(opcao -> Integer.valueOf(opcao.substring(2, 3)) == option)
                .collect(Collectors.toList()).forEach(a -> {

            System.out.println(a);

            switch (option) {
                case 1:
                    cadastrarVeiculoPasseio();
                    break;
                case 2:
                    cadastrarVeiculoCarga();
                    break;
                case 3:
                    imprimirTodosOsVeiculoDePasseio();
                    break;
                case 4:
                    imprimirTodosOsVeiculoDeCarga();
                    break;
                case 5:
                    imprimirVeiculoDePasseioPelaPlaca();
                    break;
                case 6:
                    imprimirVeiculoDeCargaPelaPlaca();
                    break;
                case 7:
                    alterarVeiculoDePasseioPelaPlaca();
                    break;
                case 8:
                    alterarVeiculoDeCargaPelaPlaca();
                    break;
                default:
                    System.out.println("| Até mais tarde");
                    System.exit(0);
            }
        });
    }

    private void cadastrarVeiculoPasseio() {

        final Passeio passeio = new Passeio();

        cadastrarDadosPadraoParaVeiculosDePasseio(passeio);
        passeio.setQtdePassageiros(Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a quantidade de passageiros:")));

        try {

            this.veiculoService.save(passeio);
            System.out.println("| ------------------------------------------------------------------ |");
            System.out.println("| Veículo salvo com sucesso!");
            System.out.println("| ------------------------------------------------------------------ |");

        } catch (VeicExistException e) {
            System.out.println("| ------------------------------------------------------------------ |");
            System.out.println("| " + e.getMessage());
            System.out.println("| ------------------------------------------------------------------ |");

            final Carga cargaEncontrada = this.veiculoService.findCargaByPlaca(passeio.getPlaca());

            // Se encontrou um veículo de carga ignora
            if (cargaEncontrada != null)
                cargaEncontrada.print();
            else {
                // Se encontrou um veículo de passeio, sugere a opção de altera-lo
                this.veiculoService.findPasseioByPlaca(passeio.getPlaca()).print();

                final String yesOrNo = Leitura.inDados("| Deseja altera-lo? (Sim/Não)");

                if ((yesOrNo != null && !Objects.equals(yesOrNo, "")) && (yesOrNo.trim().toLowerCase().equals("yes") || yesOrNo.trim().toLowerCase().equals("sim") || (yesOrNo.trim().toLowerCase().equals("y") || yesOrNo.trim().toLowerCase().equals("s")))) {
                    this.veiculoService.update(passeio.getPlaca(), passeio);
                    System.out.println("| ------------------------------------------------------------------ |");
                    System.out.println("| Veículo salvo com sucesso!");
                    System.out.println("| ------------------------------------------------------------------ |");
                }
            }

        }

        final String yesOrNo = Leitura.inDados("| Deseja cadastrar um novo veículo de passeio? (Sim/Não)");

        if ((yesOrNo != null && !Objects.equals(yesOrNo, "")) && (yesOrNo.trim().toLowerCase().equals("yes") || yesOrNo.trim().toLowerCase().equals("sim") || (yesOrNo.trim().toLowerCase().equals("y") || yesOrNo.trim().toLowerCase().equals("s"))))
            this.cadastrarVeiculoPasseio();
        else
            exibirMenu();
    }

    private void cadastrarVeiculoCarga() {

        final Carga carga = new Carga();

        cadastrarDadosPadraoParaVeiculosDeCarga(carga);
        carga.setCargaMax(Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a carga máxima:")));
        carga.setTaxa(Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a taxa:")));

        try {
            this.veiculoService.save(carga);
            System.out.println("| ------------------------------------------------------------------ |");
            System.out.println("| Veículo salvo com sucesso!");
            System.out.println("| ------------------------------------------------------------------ |");
        } catch (VeicExistException e) {

            System.out.println("| ------------------------------------------------------------------ |");
            System.out.println("| " + e.getMessage());
            System.out.println("| ------------------------------------------------------------------ |");

            final Passeio passeioEncontrado = this.veiculoService.findPasseioByPlaca(carga.getPlaca());

            // Se encontrou um veículo de carga ignora
            if (passeioEncontrado != null)
                passeioEncontrado.print();
            else {
                // Se encontrou um veículo de passeio, sugere a opção de altera-lo

                this.veiculoService.findPasseioByPlaca(carga.getPlaca()).print();

                final String yesOrNo = Leitura.inDados("| Deseja altera-lo? (Sim/Não)");

                if ((yesOrNo != null && !Objects.equals(yesOrNo, "")) && (yesOrNo.trim().toLowerCase().equals("yes") || yesOrNo.trim().toLowerCase().equals("sim") || (yesOrNo.trim().toLowerCase().equals("y") || yesOrNo.trim().toLowerCase().equals("s")))) {
                    this.veiculoService.update(carga.getPlaca(), carga);
                    System.out.println("| ------------------------------------------------------------------ |");
                    System.out.println("| Veículo salvo com sucesso!");
                    System.out.println("| ------------------------------------------------------------------ |");
                }
            }
        }

        final String yesOrNo = Leitura.inDados("| Deseja cadastrar um novo veículo de passeio? (Sim/Não)");

        if ((yesOrNo != null && !Objects.equals(yesOrNo, "")) && (yesOrNo.trim().toLowerCase().equals("yes") || yesOrNo.trim().toLowerCase().equals("sim") || (yesOrNo.trim().toLowerCase().equals("y") || yesOrNo.trim().toLowerCase().equals("s"))))
            this.cadastrarVeiculoPasseio();
        else
            exibirMenu();
    }

    private void imprimirTodosOsVeiculoDePasseio() {

        showClock();
        System.out.println("| ------------------------------------------------------------------ |");

        this.veiculoService.getAllPasseio()
                .forEach(Passeio::print);

        exibirMenu();
    }

    private void imprimirTodosOsVeiculoDeCarga() {

        showClock();
        System.out.println("| ------------------------------------------------------------------ |");

        this.veiculoService.getAllCarga()
                .forEach(Carga::print);

        exibirMenu();
    }

    private void imprimirVeiculoDeCargaPelaPlaca() {

        final String placa = Leitura.inDados("| Insira a placa: ");

        final Carga carga = this.veiculoService.findCargaByPlaca(placa);

        if (carga != null) {
            showClock();
            System.out.println("| ------------------------------------------------------------------ |");
            carga.print();
        } else {
            System.out.println("| ------------------------------------------------------------------ |");
            System.out.println("| Veículo não encontrado com essa placa!");
        }

        exibirMenu();
    }

    private void alterarVeiculoDeCargaPelaPlaca() {

        final String placa = Leitura.inDados("| Insira a placa: ");

        final Carga carga = this.veiculoService.findCargaByPlaca(placa);

        if (carga != null) {
            showClock();
            System.out.println("| ------------------------------------------------------------------ |");
            carga.print();
            alterarDadosPadraoParaVeiculosDeCarga(carga);
            carga.setCargaMax(Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a carga máxima:")));
            carga.setTaxa(Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a taxa:")));

            System.out.println("| Veículo alterado com sucesso!");
        } else {
            System.out.println("| ------------------------------------------------------------------ |");
            System.out.println("| Veículo não encontrado com essa placa!");
        }

        exibirMenu();
    }

    private void imprimirVeiculoDePasseioPelaPlaca() {

        final String placa = Leitura.inDados("| Insira a placa: ");

        final Passeio passeio = this.veiculoService.findPasseioByPlaca(placa);

        if (passeio != null) {
            showClock();
            System.out.println("| ------------------------------------------------------------------ |");
            passeio.print();
        } else {
            System.out.println("| ------------------------------------------------------------------ |");
            System.out.println("| Veículo não encontrado com essa placa!");
        }
        exibirMenu();
    }

    private void alterarVeiculoDePasseioPelaPlaca() {

        final String placa = Leitura.inDados("| Insira a placa: ");

        final Passeio passeio = this.veiculoService.findPasseioByPlaca(placa);

        if (passeio != null) {
            showClock();
            System.out.println("| ------------------------------------------------------------------ |");
            passeio.print();
            alterarDadosPadraoParaVeiculosDePasseio(passeio);
            passeio.setQtdePassageiros(Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a quantidade de passageiros:")));

            System.out.println("| Veículo alterado com sucesso!");
        } else {
            System.out.println("| ------------------------------------------------------------------ |");
            System.out.println("| Veículo não encontrado com essa placa!");
        }

        exibirMenu();
    }

    private void cadastrarDadosPadraoParaVeiculosDeCarga(final Carga carga) {

        carga.setPlaca(Leitura.inDados("| Informe a placa: "));
        carga.setMarca(Leitura.inDados("| Informe a marca: "));
        carga.setModelo(Leitura.inDados("| Informe o modelo: "));

        carga.setVelocMax(trataVelocidadeMaximaCarga());

        carga.getMotor().setPotencia(Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a potência do motor: ")));
        carga.getMotor().setQtdPist(Leitura.trataInDadosNumericos(Leitura.inDados("| Quantos pistões esse motor tem: ")));

    }

    private void cadastrarDadosPadraoParaVeiculosDePasseio(final Passeio passeio) {

        passeio.setPlaca(Leitura.inDados("| Informe a placa: "));
        passeio.setMarca(Leitura.inDados("| Informe a marca: "));
        passeio.setModelo(Leitura.inDados("| Informe o modelo: "));

        passeio.setVelocMax(trataVelocidadeMaximaPasseio());

        passeio.getMotor().setPotencia(Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a potência do motor: ")));
        passeio.getMotor().setQtdPist(Leitura.trataInDadosNumericos(Leitura.inDados("| Quantos pistões esse motor tem: ")));

    }

    private void alterarDadosPadraoParaVeiculosDeCarga(final Carga carga) {

        carga.setMarca(Leitura.inDados("| Informe a marca: "));
        carga.setModelo(Leitura.inDados("| Informe o modelo: "));

        carga.setVelocMax(trataVelocidadeMaximaCarga());

        carga.getMotor().setPotencia(Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a potência do motor: ")));
        carga.getMotor().setQtdPist(Leitura.trataInDadosNumericos(Leitura.inDados("| Quantos pistões esse motor tem: ")));

    }

    private int trataVelocidadeMaximaCarga() {
        final int velocidadeMaxima = Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a velocidade máxima (Km/h): "));
        try {
            return trataVelocidadeMaxima(velocidadeMaxima);
        } catch (VelocException e) {
            System.out.println(e.getMessage());
            System.out.println("Assumindo a velocidade máxima de 120 Km/H para veículos de carga");
            return 120;
        }
    }

    private void alterarDadosPadraoParaVeiculosDePasseio(final Passeio passeio) {

        passeio.setMarca(Leitura.inDados("| Informe a marca: "));
        passeio.setModelo(Leitura.inDados("| Informe o modelo: "));

        passeio.setVelocMax(trataVelocidadeMaximaPasseio());

        passeio.getMotor().setPotencia(Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a potência do motor: ")));
        passeio.getMotor().setQtdPist(Leitura.trataInDadosNumericos(Leitura.inDados("| Quantos pistões esse motor tem: ")));

    }

    private int trataVelocidadeMaximaPasseio() {
        final int velocidadeMaxima = Leitura.trataInDadosNumericos(Leitura.inDados("| Informe a velocidade máxima (Km/h): "));
        try {
            return trataVelocidadeMaxima(velocidadeMaxima);
        } catch (VelocException e) {
            System.out.println(e.getMessage());
            System.out.println("| Assumindo a velocidade máxima de 150 Km/H para veículos de passeio");
            return 150;
        }
    }

    private int trataVelocidadeMaxima(final int velocidadeMaxima) throws VelocException {
        if (velocidadeMaxima > 250 || velocidadeMaxima < 150)
            throw new VelocException("| A velocidade máxima está fora dos limites brasileiros");
        return velocidadeMaxima;
    }
}
