package com.CLI;

public class BasicPoint implements Point{
	private String label;
	private Executable executable;
	
	public BasicPoint(String label, Executable executable){
		this.executable = executable;
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

	public void exec() {
		this.executable.run();
	}
}
