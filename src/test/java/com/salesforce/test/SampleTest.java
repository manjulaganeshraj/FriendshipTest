package com.salesforce.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SampleTest {

    @Test
    public void testOne() {
        Friendship friendship = new Friendship();
        friendship.makeFriend("Aaron", "Bella");

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Bella");
        assertResults(expected, friendship.getDirectFriends("Aaron"));
    }

    protected static void assertResults(List<String> expected, List<String> actual) {
        Assert.assertNotNull(actual);
        Collections.sort(actual);
        Collections.sort(expected);
        Assert.assertTrue(expected.equals(actual));
    }
    
    protected static void assertFalse(List<String> expected, List<String> actual) {
        Assert.assertNotNull(actual);
        Collections.sort(actual);
        Collections.sort(expected);
        Assert.assertFalse(expected.equals(actual));
    }
    


	@Test
	public void testPositiveDirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("Steve", "Jason");
	    frndShip.makeFriend("Steve", "Cole");
	    String[] frnds = {"Jason", "Cole"};
	    
        assertResults(Arrays.asList(frnds), frndShip.getDirectFriends("Steve"));
	}

	@Test
	public void testNegativeTypoDirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("Steve", "Jason");
	    frndShip.makeFriend("Steve", "Cole");
	    String[] frnds = {"Jason", "jsh"}; 
	    assertFalse(Arrays.asList(frnds), frndShip.getDirectFriends("Steve"));
	    
	}
	
	@Test
	public void testNegativeMissingValueDirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("Steve", "Jason");
	    frndShip.makeFriend("Steve", "Cole");
	    String[] frnds = {"Jason"}; 
	    
	    assertFalse(Arrays.asList(frnds), frndShip.getDirectFriends("Steve"));
	}
	
	@Test
	public void testNegativeMissingKeyDirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("Steve", "Jason");
	    frndShip.makeFriend("Steve", "Cole");
	    assertResults(new ArrayList<String>(), frndShip.getDirectFriends("Michael"));
	}
	
	@Test
	public void testPositiveIndirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("Steve", "Jason");
	    frndShip.makeFriend("Steve", "Cole");
	    frndShip.makeFriend("Jason", "Michael");
	    frndShip.makeFriend("Cole", "Alex");
	    String[] frnds = {"Alex", "Michael"};
	    
	    assertResults(Arrays.asList(frnds), frndShip.getIndirectFriends("Steve"));
	}
	


	@Test
	public void testNegativeIndirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("Steve", "Jason");
	    frndShip.makeFriend("Steve", "Cole");
	    frndShip.makeFriend("Jason", "Michael");
	    frndShip.makeFriend("Cole", "Alex");
	    String[] frnds = {"Alex"};
	    
	    assertFalse(Arrays.asList(frnds), frndShip.getIndirectFriends("Steve"));
	}

	@Test
	public void testNegativeEmptySpaceIndirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("Steve", "Jason");
	    frndShip.makeFriend("Steve", "Cole");
	    frndShip.makeFriend("Jason", "Michael");
	    frndShip.makeFriend("Cole", "  ");
	    String[] frnds = {"  ", "Michael"};
	    
	    assertResults(Arrays.asList(frnds), frndShip.getIndirectFriends("Steve"));
	}

	@Test
	public void testNegativeZeroLengthIndirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("Steve", " ");
	    frndShip.makeFriend("Steve", "Cole");
	    frndShip.makeFriend("Jason", "Michael");
	    frndShip.makeFriend("Cole", "Alex");
	    String[] frnds = {"", "Michael"};
	    
	    assertFalse(Arrays.asList(frnds), frndShip.getIndirectFriends("Steve"));
	}

}