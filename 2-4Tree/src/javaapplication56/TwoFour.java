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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

class TwoFour {
    int count=0;
	Tree24 G =new Tree24();
	private Node24 root = new Node24(); // make root node

	// insert a NodeData
	public void insert(String dValue) 
	{
           

		Node24 curNode = root;
		NodeData24 tempItem = new NodeData24(dValue);

		while (true) {
			if (curNode.isFull()) // if node full,
			{
				split(curNode); // split it
				curNode = curNode.getParent(); // back up
												// search once
				curNode = getNextChild(curNode, dValue);
			} // end if(node is full)

			else if (curNode.isLeaf()) // if node is leaf,
				break; // go insert
			// node is not full, not a leaf; so go to lower level
			else
				curNode = getNextChild(curNode, dValue);
                } // end while
                
		curNode.insertItem(tempItem);
                      count++;
	} 

	public void split(Node24 thisNode) // split the node
	{
		// assumes node is full
            NodeData24 itemB;
            NodeData24 itemC;
            Node24 parent;
		Node24 child2, child3;
		int itemIndex;

		itemC = thisNode.removeItem(); // remove items from
		itemB = thisNode.removeItem(); // this node
		child2 = thisNode.disconnectChild(2); // remove children
		child3 = thisNode.disconnectChild(3); // from this node

		Node24 newRight = new Node24(); // make new node

		if (thisNode == root) // if this is the root,
		{
			root = new Node24(); // make new root
			parent = root; // root is our parent
			root.connectChild(0, thisNode); // connect to parent
		} else
			// this node not the root
			parent = thisNode.getParent(); // get parent

		// deal with parent
		itemIndex = parent.insertItem(itemB); // item B to parent
		int n = parent.getNumItems(); // total items?

		for (int j = n - 1; j > itemIndex; j--) // move parent's
		{ // connections
			Node24 temp = parent.disconnectChild(j); // one child
			parent.connectChild(j + 1, temp); // to the right
		}
		// connect newRight to parent
		parent.connectChild(itemIndex + 1, newRight);

		// deal with newRight
		newRight.insertItem(itemC); // item C to newRight
		newRight.connectChild(0, child2); // connect to 0 and 1
		newRight.connectChild(1, child3); // on newRight
	} // end split()

	// gets appropriate child of node during search for value
	public Node24 getNextChild(Node24 theNode, String dValue) {

		// Should be able to do this w/o a loop, since we should know
		// index of correct child already

		int j;
		// assumes node is not empty, not full, not a leaf
		int numItems = theNode.getNumItems();
		for (j = 0; j < numItems; j++) // for each item in node
		{ // are we less?
		int p=	dValue.compareTo(theNode.getItem(j).dData);
			if (p<0)
				return theNode.getChild(j); // return left child
		} // end for // we're greater, so
		return theNode.getChild(j); // return right child
	}

	public void displayTree(int i) {
	long startTime = System.nanoTime();	
            if (i == 0) {
			recDisplayTree(root, 0, 0);
		} else
			inorderdisplay(root, 0, 0);
		System.out.println();
                long endTime = System.nanoTime();
	            	long totalTime = endTime - startTime;
	            	System.out.println("it take "+totalTime/10000000 + " milliseconds ");
	}

	private void recDisplayTree(Node24 thisNode, int level, int childNumber) {
		System.out.print("level=" + level + " child=" + childNumber + " ");
		thisNode.displayNode(); // display this node
		System.out.println();
		// call ourselves for each child of this node
		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node24 nextNode = thisNode.getChild(j);
			if (nextNode != null)
				recDisplayTree(nextNode, level + 1, j);
			else
				return;
		}
	} // end recDisplayTree()

	public void inorderdisplay(Node24 thisNode, int level, int childNumber) {
		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node24 nextNode = thisNode.getChild(j);
			if (nextNode != null)
				inorderdisplay(nextNode, level + 1, j);
			else {
				thisNode.displayNode();
				return;
			}
			if (j < thisNode.getNumItems())
				thisNode.displayvalue(j);
		}
	}

	public Node24 find(String theValue) {
		return findvalue(root, theValue);
	}

	public Node24 findvalue(Node24 theNode, String theValue) {

		// Should be able to do this w/o a loop, since we should know
		// index of correct child already
		Node24 l = null;
		// assumes node is not empty, not full, not a leaf
		int numItems = theNode.getNumItems();
		// System.out.println(numItems+"-------"+theNode.getItem(0).dData);
		for (int j = 0; j < numItems; j++) // for each item in node
		{ // are we less?
			int p=theValue.compareTo(theNode.getItem(j).dData);
			// System.out.println(theNode.getItem(j).dData);
			
			if (p==0) {
				l = theNode;
				break;
			} else if (p<0 && !theNode.isLeaf()) {
				l = findvalue(theNode.getChild(j), theValue); // return left
																// child
				break;
			} else if (p>0 && !theNode.isLeaf()) {
				l = findvalue(theNode.getChild(j + 1), theValue); // return
																	// right
																	// child

			}
		} // end for // we're greater, so
		
                        return l;
        }

//	 public Node24 dele(String theValue){
//	 return delete(find(theValue),theValue);
//	 }
	
	 public Node24 delete(Node24 currnode, String theValue) {
			Node24 y = null;
			
			if (currnode.isLeaf()) {
				if (currnode.getNumItems() == 1) {
					currnode.deletenodevalue(theValue);
					return currnode;
				} else {
					y = deleteleaf_cases(currnode, theValue);
					return y;
				}
			} else {
				// delete interior nodes
				//boolean x = false;
			
				Node24 n = getNextChild(currnode, theValue);
				Node24 c = getinordernode(n);
				NodeData24 d = c.getItem(0);
				String k = d.dData;
				delete(c, d.dData);
						
				Node24 found = find(theValue);
				for(int i = 0; i < found.getNumItems();i++){
					int p=theValue.compareTo(found.getItem(i).dData);
					if(p==0){
						
						found.getItem(i).dData=k;
					}
				}
				return found;
				
			}
		}

		public Node24 deleteleaf_cases(Node24 thisNode, String theValue) {
			String sibling_side = "l";
			Node24 p = thisNode.getParent();
			Node24 sibling = thisNode.getsibiling(theValue);
			if (sibling == null) {
				sibling_side = "r";
				sibling = p.getChild(1);
			}

			if (sibling.getNumItems() == 1) {
				for (int i = 0; i <= p.getNumItems(); i++) {
					if (p.getChild(i) == sibling && sibling_side == "l") {

//						System.out.println("Sibling is on left side & Data is "
//								+ sibling.getItem(0).dData);

						thisNode.setItem(thisNode.getNumItems() - 1, null);
						thisNode.setNumItems(thisNode.getNumItems() - 1);
						NodeData24 d = p.getItem(i);
						sibling.insertItem(d);
						p.disconnectChild(i + 1);
						for (int j = i; j < p.getNumItems(); j++) {
							if (j + 1 < p.getNumItems()) {
								p.setItem(j, p.getItem(j + 1));
								if (j + 2 <= p.getNumItems()) {
									p.connectChild(j + 1, p.disconnectChild(j + 2));
								}
							}
						}
						p.setItem(p.getNumItems() - 1, null);
						p.setNumItems(p.getNumItems() - 1);

						// Check if parent is null
						if (p.getNumItems() == 0) {
//							System.out
//									.println("Parent became null; Now Tree is Re-Balancing");
							if (p != root) {
								p = balancetree(p);
							} else {
								root = sibling;
							}
						}

						return p;

					} else if (p.getChild(i) == sibling && sibling_side == "r") {

//						System.out.println("Sibling is on right side & Data is "
//								+ sibling.getItem(0).dData);

						thisNode.setItem(thisNode.getNumItems() - 1, null);
						thisNode.setNumItems(thisNode.getNumItems() - 1);
						NodeData24 d = p.getItem(i - 1);
						sibling.insertItem(d);
						p.disconnectChild(0);
						p.connectChild(0, p.disconnectChild(1));

						for (int j = i; j < p.getNumItems(); j++) {
							p.setItem(j - 1, p.getItem(j));
							if (j + 1 <= p.getNumItems()) {
								p.connectChild(j, p.disconnectChild(j + 1));
							}
						}
						p.setItem(p.getNumItems() - 1, null);
						p.setNumItems(p.getNumItems() - 1);

						// Check if parent is null
						if (p.getNumItems() == 0) {
//							System.out
//									.println("Parent became null; Now Tree is Re-Balancing");
							if (p != root) {
								p = balancetree(p);
							} else {
								root = sibling;
							}
						}
						return p;

					}
				}
			} else if (sibling.getNumItems() > 1) {
				int f = 0;
				if (sibling_side == "r") {
					f = 0;
				} else {
					f = sibling.getNumItems() - 1;
				}

				for (int i = 0; i <= p.getNumItems(); i++) {
					if (p.getChild(i) == sibling && sibling_side == "l") {
                        thisNode.getItem(0).dData = p.getItem(i).dData;
                        p.getItem(i).dData = sibling.getItem(f).dData;
                        sibling.deletenodevalue(sibling.getItem(f).dData);
                        return p;
                    }

					if (p.getChild(i) == sibling && sibling_side == "r") {
						thisNode.getItem(0).dData = p.getItem(i - 1).dData;
						p.getItem(i - 1).dData = sibling.getItem(f).dData;
						sibling.deletenodevalue(sibling.getItem(f).dData);
						return p;
					}
				}
			}

			return null;
		}
	public Node24 balancetree(Node24 currnode) { // Argument is empty node.
		String sibling_side = "l";
		Node24 p = currnode.getParent();
		Node24 sibling = currnode.getsibiling(-1);
		if (sibling == null) {
			sibling_side = "r";
			sibling = p.getChild(1);
		}

		if (sibling.getNumItems() == 1) {
			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {
					// merge parent and child and remove parent

//					System.out.println("Sibling is on left side & Data is "
//							+ sibling.getItem(0).dData);

					NodeData24 d = p.getItem(i);
					sibling.insertItem(d);
					// p.connectChild(i, newnode);
					sibling.connectChild(sibling.getNumItems(),
							currnode.disconnectChild(0));
					p.disconnectChild(i + 1);
					for (int j = i; j < p.getNumItems(); j++) {
						if (j + 1 < p.getNumItems()) {
							p.setItem(j, p.getItem(j + 1));
							if (j + 2 <= p.getNumItems()) {
								p.connectChild(j + 1, p.disconnectChild(j + 2));
							}
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					// Check if parent is null
					if (p.getNumItems() == 0) {
//						System.out
//								.println("Parent became null; Now Tree is Re-Balancing");
						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}
					return p;
				}

				else if (p.getChild(i) == sibling && sibling_side == "r") {

//					System.out.println("Sibling is on right side & Data is "
//							+ sibling.getItem(0).dData);

					NodeData24 d = p.getItem(i - 1);
					sibling.insertatfront(d);
					sibling.connectChild(0, currnode.disconnectChild(0));
					p.disconnectChild(0);
					p.connectChild(0, p.disconnectChild(1));

					for (int j = i; j < p.getNumItems(); j++) {
						p.setItem(j - 1, p.getItem(j));
						if (j + 1 <= p.getNumItems()) {
							p.connectChild(j, p.disconnectChild(j + 1));
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					// Check if parent is null
					if (p.getNumItems() == 0) {
//						System.out
//								.println("Parent became null; Now Tree is Re-Balancing");
						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}
					return p;
				}

			}

		} else if (sibling.getNumItems() > 1) {
			int f = 0;
			if (sibling_side == "r") {
				f = 0;
			} else {
				f = sibling.getNumItems() - 1;
			}
			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {

//					System.out.println("Sibling is on left side & Data is "
//							+ sibling.getItem(sibling.getNumItems() - 1).dData);
					currnode.setNumItems(currnode.getNumItems()+1);
					currnode.connectChild(1, currnode.disconnectChild(0));
					currnode.connectChild(0,
							sibling.disconnectChild(sibling.getNumItems()));
					currnode.setItem(0, p.getItem(i));
//					currnode.getItem(0).dData = p.getItem(i).dData;
					p.setItem(i, sibling.getItem(f));
//					p.getItem(i).dData = sibling.getItem(f).dData;
					sibling.setItem(sibling.getNumItems() - 1, null);
					sibling.setNumItems(sibling.getNumItems() - 1);
					return p;
				}

				if (p.getChild(i) == sibling && sibling_side == "r") {

//					System.out
//							.println("Sibling is on right side & Data is ---- "
//									+ sibling.getItem(0).dData);
					currnode.setNumItems(currnode.getNumItems()+1);
					currnode.setItem(0, p.getItem(i - 1));
//					System.out.println("Current node value: "
//							+ currnode.getItem(0).dData);
//					System.out.println("Sibling going to parent: "
//							+ sibling.getItem(f).dData);
					p.setItem(i - 1, sibling.getItem(f));
//					System.out.println("Parent Changed to: "
//							+ p.getItem(i - 1).dData);

					currnode.connectChild(1, sibling.disconnectChild(f));
//					System.out.println("Current node right child value"
//							+ currnode.getChild(1).getItem(0).dData);

					for (int j = 0; j < sibling.getNumItems(); j++) {
						if (j + 1 < sibling.getNumItems()) {
							sibling.setItem(j, sibling.getItem(j + 1));
						}
						sibling.connectChild(j, sibling.disconnectChild(j + 1));
					}
//					System.out.println("Sibling first value"
//							+ sibling.getItem(0).dData);
					sibling.setItem(sibling.getNumItems() - 1, null);
					sibling.setNumItems(sibling.getNumItems() - 1);
					//System.out.println(currnode.getItem(0).dData);
					return p;
				}
			}
		}
		return null;
	}
	
	
	
	public Node24 getinordernode(Node24 thisNode){
		Node24 c = null;
		if(thisNode.isLeaf()){
			c = thisNode;
		}
		else{
			c = getinordernode(thisNode.getChild(0));
		}
		return c;
	}
	public void findstrr() { 
long startTime = System.nanoTime();
	Scanner kb = new Scanner(System.in);

	    System.out.println(" enter the content you looking for");
	    String name = kb.next();
	    Scanner scanner;
	    Node24 found = find(name);
		if (found != null) {
			System.out.println("Found " + name);}
		else {
	    
			
                  long endTime   = System.nanoTime();
	            	long totalTime = endTime - startTime;
	            	System.out.println("it take "+totalTime/10000000 + " milliseconds ");
}
        }
	public int height ()
	{
            long startTime = System.nanoTime();
	    Node24 cur = root;
	    int depth = -1;

	    while ( cur != null )
	    {
	        cur = cur.getChild( 0 );
	        depth++;
	    }

	    
            long endTime   = System.nanoTime();
	            	long totalTime = endTime - startTime;
	            	System.out.println("it take "+totalTime/10000000 + " milliseconds ");
return depth;
	}
	
        public void count_node(){
            long startTime = System.nanoTime();
      System.out.println("Total Number of Node: " + count);

    
    long endTime = System.nanoTime();
	            	long totalTime = endTime - startTime;
	            	System.out.println("it take "+totalTime/10000000 + " milliseconds ");
        }
        }

        
