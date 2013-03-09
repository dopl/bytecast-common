package edu.syr.bytecast.interfaces.fsys;

import java.util.*;
import java.io.*;

public class ExeObj {

    //Returns the entry point of the program of the program
    public long getEntryPointIndex() {
        return m_entryPointIndex;
    }

    //Sets the entry point of the program
    public void setEntryPointIndex(long entry_point) {
        m_entryPointIndex = entry_point;
    }

    //returns all memory segments
    public List<ExeObjSegment> getSegments() {
        return m_segments;
    }

    //Returns a group of bytes from a virtual address
    public List<Byte> getBytesFromAddr(long start_addr, int size) throws Exception {
        for (int i = 0; i < m_segments.size(); i++) {
            if (start_addr >= m_segments.get(i).getStartAddress()
                    && start_addr + size < m_segments.get(i).getEndAddress()) {
                int offset = (int) (start_addr - m_segments.get(i).getStartAddress());
                return m_segments.get(i).getBytes(offset, size);
            }
        }
        throw new Exception("Address range not found in any segment.");
    }

    //Sets all segments
    public void setSegments(List<ExeObjSegment> segments) {
        m_segments = segments;
    }

    //Sets all functions
    public void setFunctions(List<ExeObjFunction> functions) {
        m_functions = functions;
    }

    //Returns all functions. 
    public List<ExeObjFunction> getFunctions() {
        return m_functions;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + (int) (this.m_entryPointIndex ^ (this.m_entryPointIndex >>> 32));
        hash = 13 * hash + (this.m_segments != null ? this.m_segments.hashCode() : 0);
        hash = 13 * hash + (this.m_functions != null ? this.m_functions.hashCode() : 0);
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
        final ExeObj other = (ExeObj) obj;
        if (this.m_entryPointIndex != other.m_entryPointIndex) {
            return false;
        }
        if (this.m_segments != other.m_segments && (this.m_segments == null || !this.m_segments.equals(other.m_segments))) {
            return false;
        }
        if (this.m_functions != other.m_functions && (this.m_functions == null || !this.m_functions.equals(other.m_functions))) {
            return false;
        }
        return true;
    }


    private long m_entryPointIndex;
    private List<ExeObjSegment> m_segments;
    private List<ExeObjFunction> m_functions;
}
