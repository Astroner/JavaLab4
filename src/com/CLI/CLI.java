package com.CLI;

import java.util.Scanner;

public class CLI {
	private Point[] points;
	private Scanner scn = new Scanner(System.in);
	
	public CLI(Point[] points){
		this.points = points;
	}
	
	public void start(){
		while(true) {
			this.printLabels();
			int choise = this.readChoise();
			if(choise == this.points.length) {
				break;
			}
			this.points[choise].exec();
		}
	}
	
	private void printLabels() {
		for(int i = 0; i < this.points.length; i++) {
			System.out.println((i + 1) + ". " + this.points[i].getLabel());
		}
		System.out.println((this.points.length + 1) + ". " + "Выход");
	}
	
	private int readChoise() {
		int choise = 0;
		while(true) {
			choise = this.scn.nextInt() - 1;
			if(choise >= 0 && choise <= this.points.length) {
				break;
			}
			System.out.println("Такого пункта нет!");
		}
		return choise;
	}
}
