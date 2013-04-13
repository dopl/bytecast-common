/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.interfaces.interp;

import edu.syr.bytecast.amd64.api.output.IExecutableFile;

/**
 *
 * @author shawn
 */
public interface IBytecastInterp {
    public long runProgram(IExecutableFile input, String[] args);
}
