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
package edu.syr.bytecast.amd64.api.output;

import edu.syr.bytecast.amd64.api.instruction.IInstruction;
import java.util.List;
import java.util.Map;

public interface ISection {
    /**
     * Returns a map of memory addresses and the instruction objects stored at that address
     * @return 
     */
    public Map<Long,IInstruction> getAllInstructionObjects();
    
    /**
     * Returns the start address of the section. This would be the same as the first element of the map
     * @return 
     */
    public long getSectionStartMemAddr();
    
    
    public String getSectionName();
    /**
     * Specifies if this section is the entry point section or not
     * @return 
     */
    

    public boolean isEntryPoint();
}
