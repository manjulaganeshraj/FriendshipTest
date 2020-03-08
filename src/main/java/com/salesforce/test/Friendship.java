package com.salesforce.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// The Friendship class
//
//
public class Friendship {
    
	private HashMap<String, HashSet<String>> frndMap;

	public Friendship() {
		frndMap =  new HashMap<String, HashSet<String>>();
	}
	
    // This method takes 2 String parameters and
    // makes them "friends" of each other.
    //
    // Note: The order of names does not matter
    // Note: Do not forget to write tests to have good test coverage for this
    // method
    public void makeFriend(String nameKey, String friendName) {
    	// Set friends in the map
		if (nameKey == null || friendName ==null || nameKey.equals(friendName) ||
				nameKey.length() == 0 || friendName.length() == 0) {
		    throw new IllegalArgumentException("Friends cannot be blank");
		}

		if (frndMap.containsKey(nameKey)) {
			frndMap.get(nameKey).add(friendName);
		} else {
			HashSet<String> frndSet = new HashSet<String>();
			frndSet.add(friendName);
			frndMap.put(nameKey, frndSet);
		}
		
		// do the same for next
		if (frndMap.containsKey(friendName)) {
			frndMap.get(friendName).add(nameKey);
		} else {
			HashSet<String> frndSet = new HashSet<String>();
			frndSet.add(nameKey);
			frndMap.put(friendName, frndSet);
		}
    }

    //
    // This method takes 2 String parameters and
    // makes them no longer friends of each other.
    //
    // Note: The order of names does not matter
    // Note: Do not forget to write tests to have good test coverage for this
    // method
    public void unmakeFriend(String nameKey, String friendName) {
    	if (nameKey == null || friendName ==null) {
		    throw new IllegalArgumentException("Friends cannot be blank");
		}
		if (frndMap.containsKey(nameKey)) {
			frndMap.get(nameKey).remove(friendName);
		}
		if (frndMap.containsKey(friendName)) {
			frndMap.get(friendName).remove(nameKey);
		}
    }


    //
    // This method takes a single argument (name) and
    // returns all immediate "friends" of that name
    //
    // For example, A & B are friends, B & C are friends, and C & D are friends.
    // getDirectFriends(B) would return A and C
    // getDirectFriends(D) would return C
    //
    // Note: It should not return duplicate names
    // Note: Do not forget to write tests to have good test coverage for this
    // method
    public List<String> getDirectFriends(String nameKey) {
    	if (frndMap.containsKey(nameKey)) {
			return new ArrayList<String>(frndMap.get(nameKey));
		} else {
			return new ArrayList<String>();
		}
    }


    //
    // This method takes a single argument (name) and
    // returns all the indirect "friends" of that name
    //
    // For example, A & B are friends, B & C are friends, and C & D are friends.
    // getInirectFriends(A) would return C and D
    //
    // Note: It should not return duplicate names
    // Note: Do not forget to write tests to have good test coverage for this
    // method

    public List<String> getIndirectFriends(String nameKey) {
        
    	if (frndMap.containsKey(nameKey)) {
    		HashSet<String> indirectFrnds = new HashSet<String>();
			for(String frnd: frndMap.get(nameKey)) {
				indirectFrnds.addAll(frndMap.get(frnd));
			}
			//remove direct friends
			for (String frnd: frndMap.get(nameKey)) {
				indirectFrnds.remove(frnd);
			}
			
			//remove self, if any
			indirectFrnds.remove(nameKey);
			
			return new ArrayList<String>(indirectFrnds);
		} else {
			return new ArrayList<String>();
		}
    	
    }
}
