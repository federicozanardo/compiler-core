package models.contract;

import lcp.lib.datastructures.Triple;
import lcp.lib.dfa.states.DfaState;
import lcp.lib.dfa.states.FinalStates;
import lcp.lib.dfa.transitions.TransitionData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@ToString
public class ContractCompiled implements Serializable {
    private final String sourceCode;
    private final String bytecode;
    private final DfaState initialState;
    private final FinalStates finalStates;
    private final ArrayList<Triple<DfaState, DfaState, TransitionData>> transitions;
}
