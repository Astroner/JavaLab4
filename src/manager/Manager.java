package manager;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Manager implements IFileManager {
	
	private String root;
	private File current;
	private ArrayList<IFile> files = new ArrayList<IFile>();
	private HashMap<IFile, File> linker = new HashMap<IFile, File>();
	
	public Manager(String _root) throws Exception {
		root = _root;
		File file = new File(root);
		if(!file.isDirectory()) {
			throw new Exception("root doesn't folder type");
		}
		current = file;
		refresh(file);
		/*
		 * System.out.println(Arrays.deepToString(files.toArray()));
		 * System.out.println(Arrays.deepToString(this.goTo(files.get(2)).toArray()));
		 * System.out.println(Arrays.deepToString(this.goTo(files.get(2)).toArray()));
		 * System.out.println(Arrays.deepToString(this.delete(files.get(0)).toArray()));
		 * System.out.println(Arrays.deepToString(this.create("static", "txt",
		 * "ASDASDASD").toArray()));
		 * System.out.println(Arrays.deepToString(this.mkdir("statics").toArray()));
		 * this.edit(files.get(1));
		 */
	}

	
	public ArrayList<IFile> goTo(IFile catalog) {
		if(!files.contains(catalog)) {
			System.err.println("Folder does not exist.");
			return files;
		}
		if(!linker.containsKey(catalog)) {
			System.err.println("Folder does not exist.");
			return files;
		}
		File file = linker.get(catalog);
		if(!file.isDirectory()) {
			System.err.println("This is not a folder.");
			return files;
		}
		refresh(file);
		current = file;
		return files;
	}

	@Override
	public ArrayList<IFile> getContent() {
		return files;
	}

	@Override
	public ArrayList<IFile> up() {
		File parent = current.getParentFile();
		if(parent == null || !parent.exists()) {
			System.err.println("Have no parent folder.");
			return files;
		}
		refresh(parent);
		current = parent;
		return files;
	}

	@Override
	public ArrayList<IFile> delete(IFile target) {
		if(!files.contains(target)) {
			System.err.println("File does not exist.");
			return files;
		}
		if(!linker.containsKey(target)) {
			System.err.println("File does not exist.");
			return files;
		}
		File file = linker.get(target);
		if(!file.isFile()) {
			System.err.println("This is not a file.");
			return files;
		}
		file.delete();
		refresh(current);
		return files;
	}

	@Override
	public void edit(IFile target) {
		if(!files.contains(target)) {
			System.err.println("File does not exist.");
		}
		if(!linker.containsKey(target)) {
			System.err.println("File does not exist.");
		}
		File file = linker.get(target);
		if(!file.isFile()) {
			System.err.println("This is not a file.");
		}
		if (Desktop.isDesktopSupported()) {
		    try {
				Desktop.getDesktop().edit(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
		    // dunno, up to you to handle this
		}
	}

	@Override
	public ArrayList<IFile> create(String name, String ext, String content) {
		File file = new File(current.getAbsolutePath() + "\\" + name + "." + ext);
		try {
			if(file.createNewFile()) {
				FileWriter writer = new FileWriter(file);
				writer.write(content);
				writer.close();
				refresh(current);
			}else {
				System.err.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return files;
	}

	@Override
	public ArrayList<IFile> mkdir(String name) {
		File file = new File(current.getAbsolutePath() + "\\" + name);
		if(file.mkdir()) {
			refresh(current);
		}else {
			System.err.println("Folder already exists.");
		}
		return files;
	}
	

	public boolean hasParent() {
		if(current.getParent() == null) return false;
		return true;
	}
	
	private Extension getExtension(File file) {
		String name = file.toString();
		String ext = name.substring(name.lastIndexOf(".")  + 1);
		switch (ext) {
			case "txt": return Extension.txt;
			case "docx": return Extension.docx;
			default: return Extension.other;
		}
	}
	
	private void refresh(File file) {
		files = new ArrayList<IFile>();
		linker = new HashMap<IFile, File>();
		for(File f : file.listFiles()) {
			if(f.isDirectory()) {
				IFile folder = new Folder(f.getName());
				files.add(folder);
				linker.put(folder, f);
			}else {
				String name = f.getName();
				int dotIndex = name.indexOf(".");
				IFile file1 = new BasicFile(
					name.substring(
						0, 
						dotIndex == -1 ? name.length() - 1 : dotIndex
					), 
					getExtension(f)
				);
				files.add(file1);
				linker.put(file1, f);
			}
		}
	}


}
