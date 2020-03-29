package manager;

public class BasicFile implements IFile {
	
	private String name;
	private Extension ext;
	
	public BasicFile(String _name, Extension _ext) {
		name = _name;
		ext = _ext;
	}
	
	public Extension getExtension() {
		return ext;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public String toString() {
		String view;
		switch (ext) {
			case folder: {
				view = "/";
				break;
			}
			case txt: {
				view = ".txt";
				break;
			}
			case docx: {
				view = ".docx";
				break;
			}
			default:{
				view = ".other";
				break;
			}
		}
		return name + view;
	}
	
}
