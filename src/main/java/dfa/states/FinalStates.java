package dfa.states;

import java.io.Serializable;
import java.util.HashSet;

public record FinalStates(HashSet<DfaState> acceptanceStates, HashSet<DfaState> failingStates) implements Serializable {

    public boolean isStateInFinalStates(DfaState state) {
        return (acceptanceStates.contains(state) || failingStates.contains(state));
    }

    public boolean isStateInAcceptanceStates(DfaState state) {
        return acceptanceStates.contains(state);
    }

    public boolean isStateInFailingStates(DfaState state) {
        return failingStates.contains(state);
    }

    @Override
    public String toString() {
        return "FinalStates{" +
                "acceptanceStates=" + acceptanceStates +
                ", failingStates=" + failingStates +
                '}';
    }
}
