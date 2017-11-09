 
import java.io.BufferedReader;
   
  
  
public class ClientListening implements Runnable {   
	
    BufferedReader buf;   
    public ClientListening(BufferedReader buf){  
         this.buf = buf;
    }  
      
    
    @Override  
    public void run() {  
        try{               
            boolean flagUser =true;
            while(flagUser){  

            	String echo = buf.readLine();  
            	System.out.println(echo);  					
            	Thread.sleep(100);
            }
        }catch(Exception e){  
            e.printStackTrace();  
        }
    } 
}
