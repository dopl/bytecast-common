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

import edu.syr.bytecast.interfaces.fsys.ExeObjSegment;
import java.util.List;

public interface IExecutableFile {
    
    /**
     * Gets the Sections which have assembly instructions. These ISection objects contain
     * functions to fetch IInstruction Objects and their memory location
     * @return 
     */
    public List<ISection> getSectionsWithInstructions();
    
    /**
     * Gets the Sections in their raw form which have been the output of Fsys. This exists here because
     * there may be sections like ".rodata" which do contain bytes but they represent
     * data and not any instruction. Jimple might need this data to resolve Constants like Strings and numbers etc.
     * @return 
     */
    public List<ExeObjSegment> getSectionsWithRawData();
    
    public String getFileName();
    
    public String getExecFileFormat();
    
    public List<IExecutableFile> getAllDependantFiles();
    
    
}
