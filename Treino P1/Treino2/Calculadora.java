package Treino2;
import java.rmi.*;

public interface Calculadora extends Remote
{
    public Fracao soma(Fracao frac1, Fracao frac2) throws RemoteException;

    public Fracao subtracao(Fracao frac1, Fracao frac2) throws RemoteException;

    public Fracao multiplicacao(Fracao frac1, Fracao frac2) throws RemoteException;

    public Fracao divisao(Fracao frac1, Fracao frac2) throws RemoteException;
}
