/**
 * 
 */
package org.tacografo.file.vublock;

import java.util.ArrayList;

import org.tacografo.file.Block;

/**
 * @author Andres Carmona Gil
 *
 */
public class ListActivity extends Block {

	private ArrayList<Activity> activity;
	
	public ListActivity(){
		this.activity=new ArrayList();
	}
	
	public void add(Block b){
		this.activity.add((Activity) b);
	}
}
