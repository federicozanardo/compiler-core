package dfa.transitions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class ContractCallByParty extends TransitionData {
    private final String functionName;
    private final String party;
    private final ArrayList<String> arguments;

    @Override
    public String toString() {
        return "ContractCallByParty{" +
                "functionName='" + functionName + '\'' +
                ", party=" + party +
                ", arguments=" + arguments +
                '}';
    }
}
