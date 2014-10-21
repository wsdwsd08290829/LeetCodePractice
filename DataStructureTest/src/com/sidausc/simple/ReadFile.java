package com.sidausc.simple;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File(args[0]));
		int nodes = s.nextInt();
		int edges = s.nextInt();
		System.out.println(nodes+ " " + edges);
		for(int i=0;i<edges; i++){
			int v = s.nextInt();
			int w = s.nextInt();
			double weight = s.nextDouble();
			System.out.println(v+ " " + w + " "+ weight);
		}
			
	
	}
}
