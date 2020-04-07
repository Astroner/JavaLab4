package yui;

import java.util.Arrays;

import manager.IFileManager;

public class App {
	
	private IFileManager manager;
	
	public App(IFileManager manager, UI ui) {
		this.manager = manager;
		
		System.out.println(Arrays.toString(manager.getContent().toArray()));
	
			
	}
}
