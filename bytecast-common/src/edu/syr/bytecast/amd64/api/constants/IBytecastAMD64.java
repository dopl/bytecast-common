/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.amd64.api.constants;

import edu.syr.bytecast.amd64.api.output.IExecutableFile;
import edu.syr.bytecast.interfaces.fsys.IBytecastFsys;


/**
 *
 * @author bytecast
 */
public interface IBytecastAMD64 {

    IExecutableFile buildInstructionObjects(String execFilePath, IBytecastFsys fsys);
    
}
