package Treno3;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject implements Calculator
{
    public Servidor() throws RemoteException
    {
        super();
    }

    @Override
    public NumeroComplexo soma(NumeroComplexo num1, NumeroComplexo num2) throws RemoteException {
        //a + bi + c + di
        NumeroComplexo resultado = new NumeroComplexo();
        resultado.setReal(num1.getReal() + num2.getReal());
        resultado.setImaginaria(num1.getImaginaria() + num2.getImaginaria());

        return resultado;
    }

    @Override
    public NumeroComplexo subtracao(NumeroComplexo num1, NumeroComplexo num2) throws RemoteException {
        NumeroComplexo resultado = new NumeroComplexo();
        resultado.setReal(num1.getReal() - num2.getReal());
        resultado.setImaginaria(num1.getImaginaria() - num2.getImaginaria());

        return resultado;
    }

    @Override
    public NumeroComplexo multiplicacao(NumeroComplexo num1, NumeroComplexo num2) throws RemoteException {
        // (a + bi) * (c + di) = ac + adi + bci - bd = 
        // = ac - bd + (ad + bc)i
        NumeroComplexo resultado = new NumeroComplexo();
        float parte1 = num1.getReal() * num2.getReal();
        float parte2 = num1.getImaginaria() * num2.getImaginaria();
        resultado.setReal(parte1 - parte2);
        
        float parte3 = num1.getReal() * num2.getImaginaria();
        float parte4 = num1.getImaginaria() * num2.getReal();
        resultado.setImaginaria(parte3 + parte4);

        return resultado;
    }

    @Override
    public NumeroComplexo divisao(NumeroComplexo num1, NumeroComplexo num2) throws RemoteException {
        // (a + bi) / (c + di) =
        // = (a + bi) * (c - di) / c² + d²  //Sinceramente, não sei se tá certo e dane-se
        // = (ac + bd) / c² + d² + (-ad + bc)i / (c² + d²)
        NumeroComplexo resultado = new NumeroComplexo();
        float parte1 = num1.getReal() * num2.getReal();
        float parte2 = num1.getImaginaria() * num2.getImaginaria();
        float numerador1 = parte1 + parte2;
        
        double denominador = Math.pow(num1.getImaginaria(), 2) + Math.pow(num2.getImaginaria(), 2);
        resultado.setReal(numerador1 / (float) denominador);

        float parte3 = - num1.getReal() * num2.getImaginaria();
        float parte4 = num1.getImaginaria() * num2.getReal();
        float numerador2 = parte3 + parte4;
        resultado.setImaginaria(numerador2 / (float) denominador);

        return resultado;
    }

    public static void main(String[] args) 
    {
        try 
        {
            Servidor stub = new Servidor();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Calculator", stub);
            System.out.println("Servidor ligado na porta 1099");
        } 
        catch (Exception e) 
        {
            System.out.println("Deu merda: " + e.getMessage());
            e.getStackTrace();
        }
    }
}
