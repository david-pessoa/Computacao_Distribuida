package Treino2;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Clientela
{
    static Fracao result_soma;
    static Fracao result_sub;
    static Fracao result_mult;
    static Fracao result_divisao;
    
    public static void main(String args[])
    {
        Fracao frac1 = new Fracao(3, 4); // 3/4
        Fracao frac2 = new Fracao(4, 3); // 4/3
        try
        {
            Registry registry = LocateRegistry.getRegistry("localhost");
            Calculadora stub = (Calculadora) registry.lookup("Calculadora");

            result_soma = stub.soma(frac1, frac2);
            result_sub = stub.subtracao(frac1, frac2);
            result_mult = stub.multiplicacao(frac1, frac2);
            result_divisao = stub.divisao(frac1, frac2);

            System.out.println("Resultado da Soma: " + result_soma.toString()); 
            System.out.println("Resultado da Subtração: " + result_sub.toString());
            System.out.println("Resultado da Multiplicação: " + result_mult.toString());
            System.out.println("Resultado da Divisão: " + result_divisao.toString());
        }
        catch(Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
