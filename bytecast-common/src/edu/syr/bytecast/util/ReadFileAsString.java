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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFileAsString {

  public String read(String filename) throws Exception {
    StringBuilder ret = new StringBuilder();
    InputStream fin = new FileInputStream(filename);
    BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
    while(true){
      String line = reader.readLine();
      if(line == null){
        break;
      }
      ret.append(line);
      ret.append("\n");
    }
    fin.close();
    return ret.toString();
  }
}
