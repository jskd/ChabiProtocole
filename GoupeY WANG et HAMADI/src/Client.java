import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintStream;  
import java.net.Socket;   
  
public class Client {  
    public static void main(String[] args) throws IOException {  
               
            Socket client = new Socket("127.0.0.1", 1027);   
            
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));                
            PrintStream out = new PrintStream(client.getOutputStream());                
            BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream()));  
            new Thread(new ClientListening(buf)).start(); 
            boolean flag = true;  
            while(flag){  
	                  
	                String str = input.readLine();  	                   
	                out.println(str); 
			
	                if("QUIT".equals(str)){ 
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag = false;  
	                }
            }
            input.close();
            out.close();
 	    if(client!=null){ 
 	    	client.close(); 
 	    }
	}
}  
									                                                                                                                                                         
