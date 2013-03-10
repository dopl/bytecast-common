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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile {
  
  public void copy(File src, File dest) throws IOException {
    InputStream fin = new FileInputStream(src);
    OutputStream fout = new FileOutputStream(dest);
    
    byte[] buffer = new byte[4096];
    while(true){
      int len = fin.read(buffer);
      if(len == -1){
        break;
      }
      fout.write(buffer, 0, len);
    }
    fin.close();
    fout.close();
  }
  
  public void copy(String src, String dest) throws IOException {
    copy(new File(src), new File(dest));
  }
}
