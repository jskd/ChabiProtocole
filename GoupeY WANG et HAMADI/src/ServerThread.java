import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.io.PrintStream;  
import java.net.Socket;
import java.util.Random;  
  
  
public class ServerThread implements Runnable {  
  
    private Socket client = null;   
    
    public ServerThread(Socket client){  
        this.client = client;  
    }       
    
    @Override  
    public void run() {  
        try{  
            PrintStream out = new PrintStream(client.getOutputStream());              
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));  
            User user = new User();
            
            boolean flagUser =true;
            boolean flagInterface =true;
            while(flagUser){  
                  
                String str =  buf.readLine();
                String[] strs = str.split("\\ ");

            	if(strs[0].equals("Bonjour")) {
            		String idUser = strs[1];
            		user = createUser(idUser);
            		new Thread(new ServerThreadMessage(out,user)).start();
            		System.out.println(idUser+" a connecte avec serveur");
            		while(flagInterface) {

            			String aStr =  buf.readLine();	
		                String[] spStr = aStr.split("\\ ");
				
		                if(spStr[0].equals("ANN")) {
		                	String id = addAdv(user.getIdUser(),aStr);
		                	out.println("This adv's id is: "+id);
		                }else if(spStr[0].equals("MES")) {
		                	if(!addMsg(user.getIdUser(),aStr)) {
		                		out.println("I can't find this userId");
		                	}
		                }else if(spStr[0].equals("DESTORY")) {
		                	if(!deleteAdv(spStr[1])) {
		                		out.println("I can't find this AdvId");
		                	}
		                }else if(spStr[0].equals("LIST-ANNS")) {
					
		                	out.println(getAllAdvs());
		                }else if(spStr[0].equals("LIST-USERS")) {
					
		                	out.println(getAllUsers());
		                }else if(spStr[0].equals("QUIT")) {
		                	System.out.println("users "+user.getIdUser()+" est parti !");
		                	flagInterface = false;
		                	flagUser = false;
		                }else {
		                	out.println("Je ne comprends pas votre command");
		                }
	            		
            		}
            	}else {
            		out.println("le formule n'est pas correct, il faut saisir: 'Bonjour username'");
            	}
            }
            out.close();
            client.close();
        }catch(Exception e){  
            e.printStackTrace();  
        }
       
    }
    
    public String createId() {
    	
    	String str = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
    	Random random = new Random();
    	StringBuffer stringBuffer = new StringBuffer();
    	int stringLength = (int) (Math.random()*10);
    	
    	for (int j = 0; j < stringLength; j++) {
    	    int index = random.nextInt(str.length());
    	    char c = str.charAt(index);
    	    stringBuffer.append(c);    
    	 }
    	String string = stringBuffer.toString();

    	return string;
    }
    
    public User createUser(String str) {
    	
    	User user = new User();
    	user.setIdUser(str);
    	Resource.getUsers().add(user);
    	
    	return user;
    }

    public String getAllAdvs() {
    	String all ="";
	
    	for(int i=0;i<Resource.getAdvs().size();i++) {   		
    		all+=Resource.getAdvs().get(i).getIdUser() +"|"+Resource.getAdvs().get(i).getName()+"|"+Resource.getAdvs().get(i).getPrice();
    		if(i!=Resource.getAdvs().size()-1)
    			all+=" , ";
    	}
	
    	return all;
    }    

    public boolean isExistAdv(String id) {
    	for(int i=0;i<Resource.getAdvs().size();i++) {   		    		
    		if(Resource.getAdvs().get(i).getId().equals(id)) {
    			return true;
    		}
    	}
    	return false;
    }

    public String getAllUsers() {
    	String all ="";
	
    	for(int i=0;i<Resource.getUsers().size();i++) {   		
    		all+=Resource.getUsers().get(i).getIdUser();
    		if(i!=Resource.getUsers().size()-1) {
    			all+=" , ";
    		}
    	}
    	all+="  "+Resource.getUsers().size()+" utiliseurs sur ligne";

    	return all;
    }
    
    public boolean isExistUser(String id) {

    	for(int i=0;i<Resource.getUsers().size();i++) {   		    		
    		if(Resource.getUsers().get(i).getIdUser().equals(id)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public String addAdv(String idUser,String str) {
    	
    	Advertisement adv = new Advertisement();
    	String[] strs = str.split("\\ ");
    	adv.setId(createId());
    	adv.setIdUser(idUser);
    	adv.setName(strs[1]);
    	adv.setPrice(strs[2]);
    	Resource.getAdvs().add(adv);
    	System.out.println(idUser+" a envoye une annonce :"+ str);
    	return adv.getId();
    }

    public boolean deleteAdv(String id){
    	if(isExistAdv(id)) {
    		int j=0;
    		for(int i=0;i<Resource.getAdvs().size();i++) {   		    		
        		if(Resource.getAdvs().get(i).getId().equals(id)) {
        			j=i;
        		}
        	}
    		Resource.getAdvs().remove(j);
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public boolean addMsg(String idSour , String str) {   	   	
    	
    	String[] strs = str.split("\\ ");
    	if(!isExistUser(strs[1])) {
    		return false;
    	}
    	Message message = new Message();
    	message.setId(createId());
    	message.setIdSour(idSour);
    	message.setIdDest(strs[1]);
    	message.setText(strs[2]);
    	message.setIsSend("not yet");
    	Resource.getMes().add(message);
    	System.out.println(idSour+" a envoye une message :"+ str);
    	return true;
    }

}
