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

package edu.syr.bytecast.test.interfaces;

public class TestResult {
    
    private boolean passed = false;
    private StringBuffer message;
    
    public TestResult()
    {
        message = new StringBuffer();
    }
    
    public void setPassed(boolean passed)
    {
        this.passed = passed;                
    }
    
    public boolean getPassed()
    {
        return passed;
    }
    
    public void appendMessage(String message)
    {
        this.message.append(message);
    }
    
    public String getMessage()
    {
        return message.toString();
    }
}
