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

  public OperandTypeMemoryLogicalAddress(RegisterType segment, RegisterType base, RegisterType index, int scale, long offset) {
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
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.m_segment != null ? this.m_segment.hashCode() : 0);
        hash = 43 * hash + (this.m_effectiveAddress != null ? this.m_effectiveAddress.hashCode() : 0);
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
        final OperandTypeMemoryLogicalAddress other = (OperandTypeMemoryLogicalAddress) obj;
        if (this.m_segment != other.m_segment) {
            return false;
        }
        if (this.m_effectiveAddress != other.m_effectiveAddress && (this.m_effectiveAddress == null || !this.m_effectiveAddress.equals(other.m_effectiveAddress))) {
            return false;
        }
        return true;
    }
  

}
