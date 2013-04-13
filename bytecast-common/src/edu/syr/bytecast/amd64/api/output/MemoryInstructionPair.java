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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.mInstructionAddress != null ? this.mInstructionAddress.hashCode() : 0);
        hash = 89 * hash + (this.instruction != null ? this.instruction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MemoryInstructionPair other = (MemoryInstructionPair) obj;
        if (this.mInstructionAddress != other.mInstructionAddress && (this.mInstructionAddress == null || !this.mInstructionAddress.equals(other.mInstructionAddress))) {
            return false;
        }
        if (this.instruction != other.instruction && (this.instruction == null || !this.instruction.equals(other.instruction))) {
            return false;
        }
        return true;
    }

 
    
    
}
