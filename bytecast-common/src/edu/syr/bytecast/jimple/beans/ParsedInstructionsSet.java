/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.jimple.beans;

import edu.syr.bytecast.amd64.api.output.MemoryInstructionPair;
import java.util.List;

/**
 *
 * @author QSA
 */
public class ParsedInstructionsSet {
    private List<MemoryInstructionPair> instructions_List;
    private JInstructionInfo info;
    public List<MemoryInstructionPair> getInstructions_List()
    {
        return this.instructions_List;
    }
    
    public JInstructionInfo getInfo()
    {
        return this.info;
    }
    
    public void setInstructions_List(List<MemoryInstructionPair> list)
    {
        this.instructions_List= list;
    }
    
    public void setInfo(JInstructionInfo type)
    {
        this.info = type;
    }
    
}

