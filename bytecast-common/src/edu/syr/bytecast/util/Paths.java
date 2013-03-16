/*
 * This file is part of Bytecast.
 *
 * Bytecast is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Bytecast is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Bytecast.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
 
package edu.syr.bytecast.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Paths {
    
    private static Paths m_instance;
    private Map<String, String> m_paths;
    private String m_root;
    
    public static synchronized Paths v(){
        if(m_instance == null){
            m_instance = new Paths();
        }
        return m_instance;
        
        
    }
    
    //Set the root directory of Bytecast.
    public void setRoot(String root_dir)
    {
        File new_file = new File(root_dir);
        m_root = new_file.getAbsolutePath();
    }
    
    //
    //Parse a file that contains the paths configuration.
    //
    //Paths must have a KEY with no white spaces, followed by an equals,
    //and then a path in quotes. Example:
    //
    //  TEST_PATH = "/path/to/test/file"
    //
    //  Comments must start with a #.
    //
    //  Throws an exception if a malformed line is caught.
    //
    
    public void parsePathsFile(String paths_file) throws Exception
    {
        BufferedReader br = null;
        m_paths = new TreeMap<String,String>();
        
        try {
            String current_line;
            
            br = new BufferedReader(new FileReader(m_root + "/" + paths_file));
            
            while((current_line = br.readLine()) != null)
            {
                parseCfgLine(current_line);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    //Parses the paths file from the default location.    
    public void parsePathsFile() throws Exception
    {
        parsePathsFile("bytecast-common/bytecast-common/cfg/paths.cfg");
    }
    
    //Parses a single configuration line
    private void parseCfgLine(String line) throws Exception
    {
        
        //remove leading whitespace
        String cleaned = line.replaceAll("^\\s+", "");
        
        if(cleaned.length() > 0 && cleaned.charAt(0) != '#')
        {
            String key;
            String value;

            int eq_idx = cleaned.indexOf("=");
            int qt1_idx = cleaned.indexOf("\"");
            int qt2_idx = cleaned.indexOf("\"", qt1_idx+1);

            //make sure the indices make sense.
            if(eq_idx != 0 && qt1_idx > eq_idx && qt2_idx > qt1_idx)
            {
                key = cleaned.substring(0, eq_idx);
                value = cleaned.substring(qt1_idx+1,qt2_idx);
                
                //clean any leading whitespace from the key.
                //cleaning the value is not necessary as we assume anything
                //between the quotes is a valid path.
                key = key.replaceAll("\\s", "");
                
                m_paths.put(key, value);

            }
            else
            {
                throw new Exception("Malformed Paths Configuration Line");
            }
        }
    }
    
    //Retyrns the current root path.
    public String getRoot()
    {
        return m_root;
    }
    
    //Returns a path from a Key.
    public String getPath(String key)
    {
        if(m_paths.containsKey(key))
        {
            return m_root + "/" + m_paths.get(key);
        }
        else
        {
            return "";
        }
    }
    
}
