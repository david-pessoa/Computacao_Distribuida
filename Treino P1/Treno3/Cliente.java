package Treno3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente 
{
    static NumeroComplexo result_soma;
    static NumeroComplexo result_subtracao;
    static NumeroComplexo result_mult;
    static NumeroComplexo result_divisao;
    public static void main(String[] args) 
    {
        NumeroComplexo numero1 = new NumeroComplexo(5, 4);
        NumeroComplexo numero2 = new NumeroComplexo(9, 8);

        try 
        {
            Registry registry = LocateRegistry.getRegistry("localhost");
            Calculator stub = (Calculator) registry.lookup("Calculator");

            result_soma = stub.soma(numero1, numero2);
            result_subtracao = stub.subtracao(numero1, numero2);
            result_mult = stub.multiplicacao(numero1, numero2);
            result_divisao = stub.divisao(numero1, numero2);

            System.out.println("Soma: " + result_soma.toString());
            System.out.println("Subtração: " + result_subtracao.toString());
            System.out.println("Multiplicação: " + result_mult.toString());
            System.out.println("Divisão: " + result_divisao.toString());

        } 
        catch (Exception e) 
        {
            System.out.println("Deu merda: " + e.getMessage());
            e.getStackTrace();
        }
    }
}
