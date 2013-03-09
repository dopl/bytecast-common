/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.jimple.api;

import edu.syr.bytecast.amd64.api.instruction.IInstruction;
import edu.syr.bytecast.jimple.beans.ParsedInstructionsSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author QSA
 */
public interface IFilter{
  boolean doTest(Map<Long, IInstruction> instList, int index);
    //void scan(List<IInstruction> unparsed_inst_list, List<ParsedInstructionsSet> parsed_inst_list, List<IFilterRule> rulesList);
    
}
