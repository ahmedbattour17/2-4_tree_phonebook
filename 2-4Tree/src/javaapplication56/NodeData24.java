/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication56;

/**
 *
 * @author Ahmed Yasser
 */
public class NodeData24 {
	public String dData; // one data item

	public NodeData24(String dValue) // constructor
	{
		dData = dValue;
	}

	public void displayItem() // display item, format "27,"
	{
		System.out.print(dData+",");
	}

}