package br.com.emanuelvictor.domain.model.craps;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Craps craps = new Craps();
        Scanner scan = new Scanner(System.in);
        while(!craps.isTheEnd()){
            System.out.println("Digite enter para continuar.");
            scan.nextLine(); int soma = craps.runDices();
            System.out.println("Soma: " + soma);
        }
        scan.close();
        if(craps.playerIsWinner())
            System.out.println("Você ganhou!");
        else if(craps.houseIsWinner())
            System.out.println("A banca ganhou!");
        else
            throw new IllegalStateException();
    }
}
