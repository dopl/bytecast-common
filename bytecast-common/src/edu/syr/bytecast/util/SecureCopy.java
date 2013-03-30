package edu.syr.bytecast.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SecureCopy {
  
  private String m_privateKey;
  private byte[] m_password;
  private String m_knownHosts;
  private String m_username;
  private String m_host;
          
  public SecureCopy(String private_key_filename, byte[] private_key_pass, 
                 String known_hosts_filename, String username, 
                 String host){
    
    m_privateKey = private_key_filename;
    m_password = private_key_pass;
    m_knownHosts = known_hosts_filename;
    m_username = username;
    m_host = host;
  }
  
  public void copy(String src, String dest) throws Exception {
    JSch jsch=new JSch(); 
    jsch.addIdentity(m_privateKey, m_password);
    jsch.setKnownHosts(m_knownHosts);
    Session session = jsch.getSession(m_username, m_host);
    session.connect();
    
    //copy zip to remote
    Channel channel = session.openChannel("exec");
    channel.setOutputStream(System.out, false);
    ChannelExec channel_exec = (ChannelExec) channel;
    channel_exec.setErrStream(System.err, false);
    channel_exec.setCommand("scp -t "+dest);
    channel_exec.connect();
    
    OutputStream out=channel.getOutputStream();
    InputStream in=channel.getInputStream();
    
    File file = new File(src);
    long filesize = file.length();
    String command0="C0644 "+filesize+" "+dest+"\n";
    out.write(command0.getBytes()); out.flush();
    if(checkAck(in)!=0){
      System.exit(0);
    }
    
    FileInputStream fis = new FileInputStream("bytecast-javadoc.zip");
    byte[] buf=new byte[1024];
    while(true){
      int len=fis.read(buf, 0, buf.length);
      if(len<=0) {
        break;
      }
      out.write(buf, 0, len); //out.flush();
    }
    fis.close();
    // send '\0'
    buf[0]=0; out.write(buf, 0, 1); out.flush();
    if(checkAck(in)!=0){
      System.exit(0);
    }
    out.close();
    channel_exec.disconnect();
  }
  
  private int checkAck(InputStream in) throws Exception {
    int b = in.read();
    // b may be 0 for success,
    //          1 for error,
    //          2 for fatal error,
    //          -1
    if(b == 0) {
      return b;
    }
    if(b == -1) {
      return b;
    }
 
    if(b == 1 || b == 2){
      StringBuffer sb = new StringBuffer();
      int c;
      do {
        c = in.read();
        sb.append((char)c);
      }
      while(c!='\n');
      if(b==1){ // error
        System.out.print(sb.toString());
      }
      if(b==2){ // fatal error
        System.out.print(sb.toString());
      }
    }
    return b;
  }
}
