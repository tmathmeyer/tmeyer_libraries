package edu.wpi.tmathmeyer.misc;

import java.util.ArrayList;

public class StringUtils {
	public static String[] split(String str, char separatorChar) {
	    if (str == null)
	        return null;

	    final int len = str.length();
	    if (len == 0)
	        return new String[0];
	    
	    final ArrayList<String> list = new ArrayList<String>();
	    int i = 0, start = 0;
	    boolean match = false;
	    boolean lastMatch = false;
	    while (i < len) {
	        if (str.charAt(i) == separatorChar) {
	            if (match) {
	                list.add(str.substring(start, i));
	                match = false;
	                lastMatch = true;
	            }
	            start = ++i;
	            continue;
	        }
	        lastMatch = false;
	        match = true;
	        i++;
	    }
	    if (match || lastMatch)
	        list.add(str.substring(start, i));
	    
	    return list.toArray(new String[list.size()]);
	}
}
