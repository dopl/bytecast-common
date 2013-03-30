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

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SshExec {

  private String m_privateKey;
  private byte[] m_password;
  private String m_knownHosts;
  private String m_username;
  private String m_host;
  
  private List<Byte> m_outputBytes;
          
  public SshExec(String private_key_filename, byte[] private_key_pass, 
                 String known_hosts_filename, String username, 
                 String host){
    
    m_privateKey = private_key_filename;
    m_password = private_key_pass;
    m_knownHosts = known_hosts_filename;
    m_username = username;
    m_host = host;
  }
  
  public void exec(String command) throws Exception {
    JSch jsch=new JSch(); 
    jsch.addIdentity(m_privateKey, m_password);
    jsch.setKnownHosts(m_knownHosts);
    Session session = jsch.getSession(m_username, m_host);
    session.connect();
    
    Channel channel = session.openChannel("exec");
    ChannelExec channel_exec = (ChannelExec) channel;
    
    channel_exec.setCommand(command);
    
    ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
    
    channel_exec.setInputStream(System.in, true);
    channel_exec.setOutputStream(output_stream);
    channel_exec.setErrStream(System.err);
    
    channel_exec.connect();
    
    InputStream channel_is = channel_exec.getInputStream();
    m_outputBytes = new ArrayList<Byte>();
    outer_loop:
    while(true){
      byte[] buffer = new byte[4096];
      while(channel_is.available()>0){
        int len = channel_is.read(buffer, 0, 4096);
        if(len == -1){
          break outer_loop;
        }
        for(int i = 0; i < len; ++i){
          m_outputBytes.add(buffer[i]);
        }
      }
      if(channel_exec.isClosed()){
        break;
      }
    }
    
    channel_exec.disconnect(); 
    session.disconnect();
  }
  
  public String getOutputString(){
    StringBuilder ret = new StringBuilder();
    for(Byte value : m_outputBytes){
      ret.append((char) value.byteValue());
    }
    return ret.toString();
  }
}
