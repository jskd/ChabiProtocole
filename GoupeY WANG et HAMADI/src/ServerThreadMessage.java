 
import java.io.PrintStream;    
  
  
public class ServerThreadMessage implements Runnable {   
    PrintStream out ;              
 
    User user = new User();
    
    
    public ServerThreadMessage(PrintStream out, User user){  
         this.out = out;
         this.user = user;
    }  
      
    
    @Override  
    public void run() {  
        try{               
            boolean flagUser =true;
            while(flagUser){  
                if(isMsg(user.getIdUser())) {
                	Message m = getMsg(user.getIdUser());
                	out.println(m.getIdSour()+" m'envoye un message: "+ m.getText());
                }         

            	Thread.sleep(500);
            }
	    out.close();
        }catch(Exception e){  
            e.printStackTrace();  
        }
    } 
    
    public boolean isMsg(String id) {
    	for(int i=0;i<Resource.getMes().size();i++) {
    		if(Resource.getMes().get(i).getIdDest().equals(id) && !Resource.getMes().get(i).getIsSend().equals("sent")){
    			return true;
    		}
    	}
    	return false;
    }
    
    public Message getMsg(String id) {
    	for(int i=0;i<Resource.getMes().size();i++) {
    		if(Resource.getMes().get(i).getIdDest().equals(id) && !Resource.getMes().get(i).getIsSend().equals("sent")) {
    			Resource.getMes().get(i).setIsSend("sent");
    			return Resource.getMes().get(i);
    		}
    	}
    	return null;
    }
}
