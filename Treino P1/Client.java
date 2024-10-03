import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry; 

public class Client 
{
    static boolean answer; 
    public static void main(String args[]) 
    { 
        try 
        { 
            Registry registry = LocateRegistry.getRegistry("localhost");
            Methods stub = (Methods) registry.lookup("Methods");
            answer = stub.EhPrimoGemeo(11, 5); 
            System.out.println("Resposta do servidor RMI é: \""
            + answer + "\""); 
        } 
        catch (Exception e) 
        { 
            System.out.println("Methods exception: "
            + e.getMessage()); 
            e.printStackTrace(); 
        } 
    }
}
