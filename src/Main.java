import manager.*;
import yui.App;

public class Main {

	public static void main(String[] args) {
		
		IFileManager testManager = new IFileManager() {

			private IFile[] files = {
				new Folder("Попки"),
				new BasicFile("Курсач", Extension.txt)
			};
			

			@Override
			public IFile[] goTo(IFile catalog) {
				if(catalog.getExtension() != Extension.folder) return files;
				
				IFile[] fils = { new BasicFile("asdasd", Extension.txt) };
				
				return fils;
			}

			@Override
			public IFile[] getContent() {
				return files;
			}

			@Override
			public IFile[] up() {
				return files;
			}

			@Override
			public IFile[] delete(IFile target) {
				IFile[] result = new IFile[files.length - 1];
					
				int index = 0;
				
				for (IFile file : files) {
					if(file != target) {
						result[index] = file;
						index++;
					}
				}
				
				return result;
			}

			public void edit(IFile target) {
				
			}

			
			public IFile[] create(String name, String ext, String content) {
				return files;
			}

			@Override
			public IFile[] mkdir(String name) {
				return files;
			}
			
		};
		
		App app = new App(testManager);
	}

}
