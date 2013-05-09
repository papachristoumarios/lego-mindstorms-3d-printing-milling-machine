package core;


/*
                                        ,   ,  
                                        $,  $,     ,            
                                        "ss.$ss. .s'     
                                ,     .ss$$$$$$$$$$s,              
                                $. s$$$$$$$$$$$$$$`$$Ss       
                                "$$$$$$$$$$$$$$$$$$o$$$       ,       
                               s$$$$$$$$$$$$$$$$$$$$$$$$s,  ,s  
                              s$$$$$$$$$"$$$$$$""""$$$$$$"$$$$$,     
                              s$$$$$$$$$$s""$$$$ssssss"$$$$$$$$"   
                             s$$$$$$$$$$'         `"""ss"$"$s""      
                             s$$$$$$$$$$,              `"""""$  .s$$s
                             s$$$$$$$$$$$$s,...               `s$$'  `
                         `ssss$$$$$$$$$$$$$$$$$$$$####s.     .$$"$.   , s-
                           `""""$$$$$$$$$$$$$$$$$$$$#####$$$$$$"     $.$'
                                 "$$$$$$$$$$$$$$$$$$$$$####s""     .$$$|
                                  "$$$$$$$$$$$$$$$$$$$$$$$$##s    .$$" $ 
                                   $$""$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"   `
                                  $$"  "$"$$$$$$$$$$$$$$$$$$$$S""""' 
                             ,   ,"     '  $$$$$$$$$$$$$$$$####s  
                             $.          .s$$$$$$$$$$$$$$$$$####"
                 ,           "$s.   ..ssS$$$$$$$$$$$$$$$$$$$####"
                 $           .$$$S$$$$$$$$$$$$$$$$$$$$$$$$#####"
                 Ss     ..sS$$$$$$$$$$$$$$$$$$$$$$$$$$$######""
                  "$$sS$$$$$$$$$$$$$$$$$$$$$$$$$$$########"
           ,      s$$$$$$$$$$$$$$$$$$$$$$$$#########""'
           $    s$$$$$$$$$$$$$$$$$$$$$#######""'      s'         ,
           $$..$$$$$$$$$$$$$$$$$$######"'       ....,$$....    ,$
            "$$$$$$$$$$$$$$$######"' ,     .sS$$$$$$$$$$$$$$$$s$$
              $$$$$$$$$$$$#####"     $, .s$$$$$$$$$$$$$$$$$$$$$$$$s.
   )          $$$$$$$$$$$#####'      `$$$$$$$$$###########$$$$$$$$$$$.
  ((          $$$$$$$$$$$#####       $$$$$$$$###"       "####$$$$$$$$$$ 
  ) \         $$$$$$$$$$$$####.     $$$$$$###"             "###$$$$$$$$$   s'
 (   )        $$$$$$$$$$$$$####.   $$$$$###"                ####$$$$$$$$s$$'
 )  ( (       $$"$$$$$$$$$$$#####.$$$$$###' -Tua Xiong     .###$$$$$$$$$$"
 (  )  )   _,$"   $$$$$$$$$$$$######.$$##'                .###$$$$$$$$$$
 ) (  ( \.         "$$$$$$$$$$$$$#######,,,.          ..####$$$$$$$$$$$"
(   )$ )  )        ,$$$$$$$$$$$$$$$$$$####################$$$$$$$$$$$"        
(   ($$  ( \     _sS"  `"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$S$$,       
 )  )$$$s ) )  .      .   `$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"'  `$$   
  (   $$$Ss/  .$,    .$,,s$$$$$$##S$$$$$$$$$$$$$$$$$$$$$$$$S""        ' 
    \)_$$$$$$$$$$$$$$$$$$$$$$$##"  $$        `$$.        `$$.
        `"S$$$$$$$$$$$$$$$$$#"      $          `$          `$
Have fun!
*/

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;

/**
 * Handles GCode files as a reader
 * @author Marios Papachristou
 *
 */
public class Reader {

	public String file;
	public String[][][] code;	
	public Reader() {
		
	}
	
	/**
	 * Casts a text file into a String
	 * @param file
	 * @return file as string
	 */
    public String fileToString(String file) {
        String result = null;
        DataInputStream in = null;
        try {
            File f = new File(file);
            byte[] buffer = new byte[(int) f.length()];
            in = new DataInputStream(new FileInputStream(f));
            in.readFully(buffer);
            result = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException("IO problem in fileToString", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) { //ignore it
            }
        }
        return result;
    }

    
		

	/**
	 * Splits GCode code into matrices
	 * @param code
	 * @return <code>String[][][]</code>
	 */
	public String[][][] splitCode(String code) {
		String[] astring = split("\n", code);
		
		ArrayList<String[][]> list = new ArrayList<String[][]>();
		for (String x: astring) {
			String[][] bstring = splitLine(x);
			list.add(bstring);
		} return list.toArray(new String[list.size()][][]);		
	}
	
	
	/**
	 * Splits a line of GCode
	 * @param line
	 * @return <code>String[][]</code>
	 */
	public String[][] splitLine(String line) {
		String[] astring = split(" ", line);
		ArrayList<String[]> list = new ArrayList<String[]>();
		for (String x: astring) {
			String[] bstring = {x.substring(0,1),x.substring(1, x.length())};
			list.add(bstring);
			
		} return list.toArray(new String[list.size()][]);

		
	}
	
	
	/**
	 * Strips GCode Comments
	 * @param astring, commentcharacter = ";"
	 * @return
	 */
	public String stripComments(String astring) {
		char commentcharacter = ';'; //GCode comments are passed with the ";" symbol
		int pos = astring.indexOf(commentcharacter);
		if (astring.charAt(pos-1) == ' ') {
			return astring.substring(0, pos-2); 
		} else {
			return astring.substring(0,pos-1);
		}
	}
	
	/**
	 * Splits a string
	 * @param s
	 * @param S
	 * 
	 */
    private String[] split(String s, String S) {
        ArrayList<String> list = new ArrayList<String>();
        int start = 0;
        while (start < S.length()) {
           int end = S.indexOf(s, start);
           if (end < 0)
              break;
           
           list.add(S.substring(start, end));
           start = end + s.length();
        }
        if (start < S.length())
           list.add(S.substring(start));
        return list.toArray(new String[list.size()]);
     }



	
    
}