/**
 * 
 */
package com.tachographStructure.file.vuBlocks;

import java.util.ArrayList;

import com.tachographStructure.file.Block;

/**
 * @author Andres Carmona Gil
 *
 */
public class ListActivity extends Block {

	private ArrayList<Activity> activity;
	
	
	public ListActivity(){
		this.activity=new ArrayList();
	}
	
	public void add(Activity b){
		this.activity.add(b);
	}

	/**
	 * @return the activity
	 */
	public ArrayList<Activity> getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(ArrayList<Activity> activity) {
		this.activity = activity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListActivity [activity=" + activity + "]";
	}
	
}
