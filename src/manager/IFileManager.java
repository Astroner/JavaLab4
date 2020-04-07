package manager;

import java.util.ArrayList;

public interface IFileManager {
	public boolean hasParent();
	public ArrayList<IFile> goTo(IFile catalog);
	public ArrayList<IFile> getContent();
	public ArrayList<IFile> up();
	public ArrayList<IFile> delete(IFile target);
	public void edit(IFile target);
	public ArrayList<IFile> create(String name, String ext, String content);
	public ArrayList<IFile> mkdir(String name);
}