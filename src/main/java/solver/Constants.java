package solver;

import java.util.Collections;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by will on 5/24/17.
 */
public class Constants {
	public final static char START_CHAR = '&';
	public final static char END_CHAR = '*';
	public final static char NODE_CHAR = 0x20;	//space character
	public final static Set<Character> NODE_CHARS = Collections.unmodifiableSet(
			new HashSet<>(Arrays.asList( START_CHAR, END_CHAR, NODE_CHAR )));

	public final static String UP = "up";
	public final static String RIGHT = "right";
	public final static String DOWN = "down";
	public final static String LEFT = "left";
}
