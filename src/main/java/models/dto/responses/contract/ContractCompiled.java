package models.dto.responses.contract;

import lcp.lib.datastructures.Triple;
import lcp.lib.dfa.states.DfaState;
import lcp.lib.dfa.states.FinalStates;
import lcp.lib.dfa.transitions.TransitionData;

import java.io.Serializable;
import java.util.ArrayList;

public record ContractCompiled(String sourceCode, String bytecode, DfaState initialState, FinalStates finalStates,
                               ArrayList<Triple<DfaState, DfaState, TransitionData>> transitions) implements Serializable {

    @Override
    public String toString() {
        return "ContractCompiled{" +
                "sourceCode='" + sourceCode + '\'' +
                ", bytecode='" + bytecode + '\'' +
                ", initialState=" + initialState +
                ", finalStates=" + finalStates +
                ", transitions=" + transitions +
                '}';
    }
}
