package yui;

import java.util.ArrayList;
import java.util.Scanner;

import manager.Extension;
import manager.IFile;
import manager.IFileManager;

public class App {
	
	private IFileManager manager;
	private Scanner scn = new Scanner(System.in);
	private ArrayList<IFile> content = new ArrayList<>();
	private MenuItem[] menuItems = {
		new MenuItem() {
			public String getLabel() {
				return "...";
			}

			public void exec() {
				if(manager.hasParent()) {
					manager.up();
				}else {
					System.out.println("This is root folder");
				}
			};
		},
		new MenuItem() {
			public String getLabel() {
				return "Open folder";
			}

			public void exec() {
				System.out.print("Folder index: ");
				int i = readChoise(0, content.size());
				IFile file = content.get(i);
				
				if(file.getExtension() != Extension.folder) {
					System.out.println("It's not folder.");
								
					return;
				}else {
					manager.goTo(file);
				}
			};
		},
		new MenuItem() {
			public String getLabel() {
				return "Open file";
			}

			public void exec() {
				System.out.print("File index: ");
				int i = readChoise(0, content.size());
				
				IFile file = content.get(i);
				if(file.getExtension() == Extension.folder) {
					System.out.println("It's folder.");
								
					return;
				}else {
					manager.edit(file);
				}
			};
		},
		new MenuItem() {
			public String getLabel() {
				return "Create file";
			}

			@Override
			public void exec() {
				System.out.println("File name: ");
				String name = scn.nextLine();
				
				System.out.println("File extention: ");
				String ext = scn.nextLine();
				
				System.out.println("File content: ");
				String content = scn.nextLine();
				
				manager.create(name, ext, content);
			};
		},
		new MenuItem() {
			public String getLabel() {
				return "mkdir";
			}

			@Override
			public void exec() {
				System.out.println("Folder name: ");
				
				String name = scn.nextLine();
				
				manager.mkdir(name);
			};
		},
		new MenuItem() {
			public String getLabel() {
				return "Delete file";
			}

			public void exec() {
				System.out.print("File index: ");
				int i = readChoise(0, content.size());
				
				IFile file = content.get(i);
				
				manager.delete(file);
			};
		}		
	};
	
	public App(IFileManager manager) {
		this.manager = manager;		
	}
	
	public void start() {
		while(true) {
			content = manager.getContent();
			printContent();
			printMenu();
			int status = executeChoise();
			if(status == 1) {
				break;
			}
		}
	}
	
	private void printContent() {	
		for(int i = 0; i < content.size(); i++) {
			IFile file = content.get(i);
			System.out.println((i + 1) + ") " + file.toString());
		}
	}
	
	private void printMenu() {
		for(int i = 0; i < menuItems.length; i++)
			System.out.println((i + 1) + ". " + menuItems[i].getLabel());
		System.out.println((menuItems.length + 1) + ". Exit");
	}
	
	private int executeChoise() {
		int i = readChoise(0, this.menuItems.length);
		if(i == menuItems.length) {
			return 1;
		}
		menuItems[i].exec();
		
		return 0;
	}
	private int readChoise(int min, int max) {
		int choise = 0;
		while(true) {
			choise = this.scn.nextInt() - 1;
			if(choise >= min && choise <= max) {
				break;
			}
			System.out.println("Invalid value");
		}
		System.out.println();
		return choise;
	}
}
