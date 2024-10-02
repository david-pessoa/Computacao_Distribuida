import java.rmi.Naming; 
import java.rmi.RemoteException; 

public class Client 
{
    static boolean answer; 
    // The Hello object "obj" is the identifier that is 
  
    // the Hello interface. 
    static Methods obj = null; 
    public static void main(String args[]) 
    { 
        try 
        { 
            obj = (Methods)Naming.lookup("//"
            + "kvist.cs.umu.se"
            + "/Hello"); 
            answer = obj.EhPrimoGemeo(0, 0); 
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
