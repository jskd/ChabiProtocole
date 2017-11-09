
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class Server {  
    public static void main(String[] args) throws Exception{  
          
        ServerSocket server = new ServerSocket(1027);  
        Socket client = null;  
        boolean f = true;  
        while(f){  
              
            client = server.accept();  
            
            new Thread(new ServerThread(client)).start(); 
               
        }  
        server.close();  
    }  
} 
