/*
 * File: BlankKarel.java
 * ---------------------
 * This class is a blank one that you can change at will.
 */

import stanford.karel.*;

public class BlankKarel extends SuperKarel {
	public void run() {
		if (frontIsBlocked()){
			turnLeft();
		}
		System.out.print("hi");
		move();
	}
}

