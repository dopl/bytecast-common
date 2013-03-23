/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.amd64.api.output;

import edu.syr.bytecast.amd64.api.instruction.IInstruction;

/**
 *
 * @author bytecast
 */
public class MemoryInstructionPair {
    private Long mInstructionAddress;
    private IInstruction instruction;

    public MemoryInstructionPair(Long mInstructionAddress, IInstruction instruction) {
        this.mInstructionAddress = mInstructionAddress;
        this.instruction = instruction;
    }

    /**
     * @return the mInstructionAddress
     */
    public Long getmInstructionAddress() {
        return mInstructionAddress;
    }

 
    /**
     * @return the instruction
     */
    public IInstruction getInstruction() {
        return instruction;
    }

 
    
    
}
