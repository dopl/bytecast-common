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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RunProcess {
  
  private StreamEater m_stdout;
  private StreamEater m_stderr;
  
  public int exec(String command, File start_dir) throws IOException, InterruptedException {
    Process p = Runtime.getRuntime().exec(command, getEnv(), start_dir);
    m_stdout = new StreamEater(p.getInputStream());
    m_stderr = new StreamEater(p.getErrorStream());
    return p.waitFor();
  }
  
  public int exec(String command, String[] env, File start_dir) throws IOException, InterruptedException {
    Process p = Runtime.getRuntime().exec(command, env, start_dir);
    m_stdout = new StreamEater(p.getInputStream());
    m_stderr = new StreamEater(p.getErrorStream());
    return p.waitFor();
  }
  
  private String[] getEnv() {
    Map<String, String> env_map = System.getenv();
    String[] ret = new String[env_map.size()];
    int index = 0;
    for(String key : env_map.keySet()){
      String value = env_map.get(key);
      ret[index] = key+"="+value;
      ++index;
    }
    return ret;
  }
  
  public List<String> getOutput(){
    String str = m_stdout.getString();
    return split(str);
  }
  
  public List<String> getError(){
    String str = m_stderr.getString();
    return split(str);
  }

  private List<String> split(String str) {
    //http://stackoverflow.com/questions/454908/split-java-string-by-new-line
    String lines[] = str.split("\\r?\\n");
    List<String> ret = new ArrayList<String>();
    for(String line : lines){
      ret.add(line);
    }
    return ret;
  }
}
