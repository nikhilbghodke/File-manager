package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here\
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the directory to be searched");
        String file=sc.next();
        System.out.println("Enter the string to be searched");
        String text=sc.next();
        Search.search(file,text);

    }
}
