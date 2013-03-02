/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.amd64.api.constants;

/**
 *
 * @author hapan
 */
public class OperandTypeMemoryLogicalAddress {

  private RegisterType m_segment;
  private OperandTypeMemoryEffectiveAddress m_effectiveAddress;

  /**
   *
   * @param segment
   * @param effectiveAddress
   */
  public OperandTypeMemoryLogicalAddress(RegisterType segment, OperandTypeMemoryEffectiveAddress effectiveAddress) {
    this.m_segment = segment;
    this.m_effectiveAddress = effectiveAddress;
  }

  public OperandTypeMemoryLogicalAddress(RegisterType segment, RegisterType base, RegisterType index, int scale, int offset) {
    this.m_segment = segment;
    this.m_effectiveAddress = new OperandTypeMemoryEffectiveAddress(base, index, scale, offset);
  }

  public OperandTypeMemoryEffectiveAddress getEffectiveAddress() {
    return m_effectiveAddress;
  }

  public RegisterType getSegment() {
    return m_segment;
  }
  
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OperandTypeMemoryLogicalAddress)) {
            return false;
        }
        OperandTypeMemoryLogicalAddress param = (OperandTypeMemoryLogicalAddress) obj;
        if (this.m_effectiveAddress == null) {
            return param.m_effectiveAddress == null && this.m_segment == param.m_segment;
        } else {
            return this.m_effectiveAddress.equals(param.m_effectiveAddress) && this.m_segment == param.m_segment;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.m_segment != null ? this.m_segment.hashCode() : 0);
        hash = 23 * hash + (this.m_effectiveAddress != null ? this.m_effectiveAddress.hashCode() : 0);
        return hash;
    }
}
