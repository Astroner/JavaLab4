import manager.*;
import yui.App;

public class Main {

	public static void main(String[] args) throws Exception {
		String root = "/JavaLabs/";
		IFileManager manager = new Manager(root);
		
		
		App app = new App(manager);
	}

}
