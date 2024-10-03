package Treno3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote
{
    public NumeroComplexo soma(NumeroComplexo num1, NumeroComplexo num2) throws RemoteException;
    public NumeroComplexo subtracao(NumeroComplexo num1, NumeroComplexo num2) throws RemoteException;
    public NumeroComplexo multiplicacao(NumeroComplexo num1, NumeroComplexo num2) throws RemoteException;
    public NumeroComplexo divisao(NumeroComplexo num1, NumeroComplexo num2) throws RemoteException;
}
