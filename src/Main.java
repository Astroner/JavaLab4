import manager.IFileManager;
import manager.Manager;
import yui.App;

public class Main {

	public static void main(String[] args) throws Exception {
		String root = "/";
		IFileManager manager = new Manager(root);
		
		
		App app = new App(manager);
	}

}
