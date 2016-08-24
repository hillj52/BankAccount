package ssa;

import java.util.ArrayList;

public class IdTracker {

	private static IdTracker instance = null;
	
	private ArrayList<Integer> usedIds;
	
	public static IdTracker getInstance() {
		if (instance == null) {
			instance = new IdTracker();
		}
		return instance;
	}
	
	private IdTracker() {
		usedIds = new ArrayList<Integer>();
	}
	
	public void addId(int id) {
		usedIds.add(id);
	}
	
	public boolean isUsed(int id) {
		return usedIds.contains(id);
	}
}
