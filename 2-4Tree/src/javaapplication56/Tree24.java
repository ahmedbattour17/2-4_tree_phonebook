/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication56;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
public class Tree24 {

	
	static boolean stillplaying =true;
	public static void main(String[] args) throws Exception  {
		Tree24 G =new Tree24();
		TwoFour theTree = new TwoFour();
		 Scanner sc = new Scanner(System.in);


		
		
		
		
		
		
	       
	        int ch;
	        
	        System.out.print("\n**WELCOME TO OUR REGISTER SYSTEM**\n");

	      
 while (stillplaying=true) { 
	 
	              System.out.println("\n-------------------------------------------------------------------------------------");
	            System.out.println("\n 1. Search             \n 2. Insertion/Creation \n 3. print tree \n 4. Removal \n 5. height of tree \n 6. number of tree node  ");
	                 System.out.println("\n-------------------------------------------------------------------------------------");
	            System.out.println("ENTER YOUR CHOICE:-");
	            ch = sc.nextInt();
	           String value = null;
	          
	            if(ch==1) {
	            	theTree.findstrr();
	            	
                	
	            }	
	            else {
	            if(ch==2) {
	            	long startTime = System.nanoTime();
	            	System.out.println("enter element to inseart");
                	value=sc.next();
;                	theTree.insert(value);
	            	long endTime = System.nanoTime();
	            	long totalTime = endTime - startTime;
	            	System.out.println("it take "+totalTime/1000000000 + " milliseconds ");
                                
	            }
	            else {
	            	if(ch==3) {
	            		
	           	theTree.displayTree(0);
	            	}
	            
	            	 else
	            	if(ch==4) {
	            
	      
	    
                
	            		System.out.println("Enter value to delete: ");
						value = sc.next();
						Node24 del = theTree.find(value);
						if (del != null)
							{
								if(theTree.delete(del,value)!=null)
									System.out.println("Deleted " + value);
								else{
									System.err.println("Not Deleted!!!");
								}
							}
						else
							System.out.println("Could not find " + value);
//	            	}
	            	}
                        else {
	            	if(ch==5) {
	            System.out.println("the height of tree is "+	theTree.height());	
                        }	            		
                        else{
                           if(ch==6) 
                               theTree.count_node();
                        }	            		
	            	

}
}	                
} 
}
}
} 

	                
