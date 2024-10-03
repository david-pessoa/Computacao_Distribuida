package Treino1;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject; 

public class Server extends UnicastRemoteObject implements Methods
{
    public Server() throws RemoteException 
    { 
        super(); 
    } 

    private boolean ehPrimo(int a)
    {
        for(int i = 2; i < a; i++)
        {
            if(a % i == 0)
                return false;
        }
        return true;
    }

    public boolean EhPrimoGemeo(int a, int b) throws RemoteException
    { 
        if((a - b == 2 || a - b == -2) && ehPrimo(a) && ehPrimo(b))
            return true;
        else
            return false;
    } 
    public static void main(String args[]) 
    { 
        try 
        { 
            Server stub = new Server();  
             
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Methods", stub);
            System.out.println("Ligado no registro"); 
        } 
        catch (Exception e) 
        { 
            System.out.println("error: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    }
}
