package yui;

import com.CLI.Executable;

import java.util.Arrays;

import com.CLI.BasicPoint;
import com.CLI.CLI;
import com.CLI.Point;

import manager.IFileManager;

public class App {
	
	private IFileManager manager;
	private CLI cli;
	
	public App(IFileManager manager) {
		this.manager = manager;
		
		System.out.println(Arrays.toString(manager.getContent().toArray()));
		
		Point[] options = {
			new BasicPoint("start", new Executable() {
				public void run() {
					System.out.println("янях фнос");
				}
			})
		};
		
		cli = new CLI(options);
		cli.start();
	}
}
