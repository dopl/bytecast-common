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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFolder {
  
  private ZipOutputStream m_zout;
  
  public void zip(String folder, String output_name) throws Exception {
    m_zout = new ZipOutputStream(new FileOutputStream(output_name));
    
    File file = new File(folder);
    zipFolder(file, "/"+file.getName()+"/");
    
    m_zout.flush();
    m_zout.close();
  }

  private void zipFolder(File file, String path) throws Exception {
    ZipEntry folder_entry = new ZipEntry(path);
    m_zout.putNextEntry(folder_entry);
    
    File[] children = file.listFiles();
    for(File child : children){
      if(child.isDirectory()){
        zipFolder(child, path+child.getName()+"/");
      } else {
        zipFile(child, path+child.getName());
      }
    }
  }

  private void zipFile(File file, String name) throws Exception {
    ZipEntry entry = new ZipEntry(name); 
    FileInputStream fin = new FileInputStream(file);
    
    byte[] buffer = readZipEntry(fin);
    entry.setCrc(calcCrc32(buffer));
    entry.setCompressedSize(-1);

    m_zout.putNextEntry(entry);
    
    int size = buffer.length;
    int count = 0;
    while(count < size){
      int len = size - count;
      if(len > 4096){
        len = 4096;
      }
      m_zout.write(buffer, count, len);
      count += len;
    }
    m_zout.flush();
    m_zout.closeEntry();
    fin.close();
  }
  
  
  private long calcCrc32(byte[] buffer){
    CRC32 crc = new CRC32();
    crc.update(buffer);
    return crc.getValue();
  }

  private byte[] readZipEntry(InputStream input_stream) throws IOException {
    List<Byte> bytes = new ArrayList<Byte>();
    while(true){
      byte[] buffer = new byte[4096];
      int len = input_stream.read(buffer);
      if(len == -1)
        break;
      for(int i = 0; i < len; ++i){
        bytes.add(buffer[i]);
      }
    }
    byte[] ret = new byte[bytes.size()];
    for(int i = 0; i < bytes.size(); ++i){
      ret[i] = bytes.get(i);
    }
    return ret;
  }

}
