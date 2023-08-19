package dfa.states;

import java.io.Serializable;

public record DfaState(String name) implements Serializable {

    @Override
    public String toString() {
        return "DfaState{" +
                "name='" + name + '\'' +
                '}';
    }
}
