package models.dto.responses.contract;

import dfa.states.DfaState;
import dfa.states.FinalStates;
import dfa.transitions.TransitionData;
import lcp.lib.datastructures.Triple;

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
