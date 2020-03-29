package manager;

public interface IFileManager {
	public IFile[] goTo(IFile catalog);
	public IFile[] getContent();
	public IFile[] up();
	public IFile[] delete(IFile target);
	public void edit(IFile target);
	public IFile[] create(String name, String ext, String content);
	public IFile[] mkdir(String name);
}
