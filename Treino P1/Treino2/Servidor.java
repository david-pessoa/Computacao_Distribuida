package Treino2;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

public class Servidor extends UnicastRemoteObject implements Calculadora
{
    public Servidor() throws RemoteException
    {
        super();
    }


    public Fracao soma(Fracao frac1, Fracao frac2) throws RemoteException {
        Fracao frac_result = new Fracao();

        frac_result.denominador = frac1.denominador * frac2.denominador;
        frac1.numerador *= frac2.denominador;
        frac2.numerador *= frac1.denominador;

        frac_result.numerador = frac1.numerador + frac2.numerador;

        frac_result.simplificar();

        return frac_result;
    }

    public Fracao subtracao(Fracao frac1, Fracao frac2) throws RemoteException
    {
        Fracao frac_result = new Fracao();

        frac_result.denominador = frac1.denominador * frac2.denominador;
        frac1.numerador *= frac2.denominador;
        frac2.numerador *= frac1.denominador;

        frac_result.numerador = frac1.numerador - frac2.numerador;

        frac_result.simplificar();

        return frac_result;
    }

    public Fracao multiplicacao(Fracao frac1, Fracao frac2) throws RemoteException
    {
        Fracao frac_result = new Fracao();

        frac_result.numerador = frac1.numerador * frac2.numerador;
        frac_result.denominador = frac1.denominador * frac2.denominador;

        frac_result.simplificar();
        return frac_result;

    }

    public Fracao divisao(Fracao frac1, Fracao frac2) throws RemoteException
    {
        Fracao invertida = new Fracao(frac2.denominador, frac2.numerador);
        return multiplicacao(frac1, invertida);
    }

    public static void main(String[] args) 
    {
        try
        {
            Servidor stub = new Servidor();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Calculadora", stub);
            System.out.println("Ligado no resgistro");

        }
        catch(Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
