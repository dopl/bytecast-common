/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.jimple.api;


import edu.syr.bytecast.amd64.api.constants.IBytecastAMD64;
/**
 *
 * @author QSA
 */
public interface IJimple {
    //this is the prime function for client
    boolean createJimple(IBytecastAMD64 amd64Object, String fileName);
}

