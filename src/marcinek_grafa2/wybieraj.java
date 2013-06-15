package marcinek_grafa2;

import java.io.*;
import java.util.StringTokenizer;

public class wybieraj {
	StringTokenizer linia=null;
	 public String get(BufferedReader in)  {
	        String tmp_linia;
	        String value = null;
	            if (linia == null) {
	                try {
	                    do {
	                        tmp_linia = in.readLine();
	                    } while (tmp_linia.charAt(0) == '#');
	                    linia = new StringTokenizer(tmp_linia);
	                } catch (IOException ex) {
	                }
	            }

	            if (linia.hasMoreTokens()) {
	                value = linia.nextToken();
	            } else {
	                try {
	                    do {
	                        tmp_linia = in.readLine();
	                    } while (tmp_linia.charAt(0) == '#');
	                    linia = new StringTokenizer(tmp_linia);
	                } catch (IOException ex) {
	                }
	                value = linia.nextToken();
	            }
	        return value;
	    }
	 @SuppressWarnings("deprecation")
	public String get(DataInputStream in)  {
	        String tmp_linia;
	        String value = null;
	            if (linia == null) {
	                try {
	                    do {
	                        tmp_linia = in.readLine();
	                    } while (tmp_linia.charAt(0) == '#');
	                    linia = new StringTokenizer(tmp_linia);
	                } catch (IOException ex) {
	                }
	            }

	            if (linia.hasMoreTokens()) {
	                value = linia.nextToken();
	            } else {
	                try {
	                    do {
	                        tmp_linia = in.readLine();
	                    } while (tmp_linia.charAt(0) == '#');
	                    linia = new StringTokenizer(tmp_linia);
	                } catch (IOException ex) {
	                }
	                value = linia.nextToken();
	            }
	        return value;
	    }
}
