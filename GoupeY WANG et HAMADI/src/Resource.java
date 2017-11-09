import java.util.ArrayList;
import java.util.List;

public class Resource {

    private static List<User> users = new ArrayList<User>();
    private static List<Advertisement> advs = new ArrayList<Advertisement>();
    private static List<Message> mes = new ArrayList<Message>();
    
    
	public static List<User> getUsers() {
		return users;
	}
	public static void setUsers(List<User> users) {
		Resource.users = users;
	}
	public static List<Advertisement> getAdvs() {
		return advs;
	}
	public static void setAdvs(List<Advertisement> advs) {
		Resource.advs = advs;
	}
	public static List<Message> getMes() {
		return mes;
	}
	public static void setMes(List<Message> mes) {
		Resource.mes = mes;
	} 
    
    
}
