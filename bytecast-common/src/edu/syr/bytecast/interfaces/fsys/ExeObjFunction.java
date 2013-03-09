
package edu.syr.bytecast.interfaces.fsys;

public class ExeObjFunction {
    private String m_name;
    private long   m_startAddress;
    private int    m_size;
    
    public void setName(String name)
    {
        m_name = name;
    }
    
    public void setStartAddress(long addr)
    {
        m_startAddress = addr;
    }
    
    public void setSize(int size)
    {
        m_size = size;
    }
    
    public String getName()
    {
        return m_name;
    }
    
    public long getStartAddress()
    {
        return m_startAddress;
    }
    
    public int getSize()
    {
        return m_size;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.m_name != null ? this.m_name.hashCode() : 0);
        hash = 53 * hash + (int) (this.m_startAddress ^ (this.m_startAddress >>> 32));
        hash = 53 * hash + this.m_size;
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
        final ExeObjFunction other = (ExeObjFunction) obj;
        if ((this.m_name == null) ? (other.m_name != null) : !this.m_name.equals(other.m_name)) {
            return false;
        }
        if (this.m_startAddress != other.m_startAddress) {
            return false;
        }
        if (this.m_size != other.m_size) {
            return false;
        }
        return true;
    }
}
