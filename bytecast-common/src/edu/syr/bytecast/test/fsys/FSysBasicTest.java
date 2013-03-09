/*
 * This file is part of Bytecast.
 *
 * Bytecast is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Bytecast is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Bytecast.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package edu.syr.bytecast.test.fsys;

import edu.syr.bytecast.interfaces.fsys.ExeObj;
import edu.syr.bytecast.interfaces.fsys.IBytecastFsys;
import edu.syr.bytecast.test.ITestCase;
import edu.syr.bytecast.test.mockups.MockBytecastFsys;

public class FSysBasicTest implements ITestCase {

  @Override
  public boolean test() {
      
    IBytecastFsys fsys = new MockBytecastFsys(); //get from somewhere (product/mock)
    

    
    
    return false;
  }

  @Override
  public String getMessage() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}
