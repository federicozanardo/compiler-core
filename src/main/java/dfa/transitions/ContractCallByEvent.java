package dfa.transitions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ContractCallByEvent extends TransitionData {
    private final String obligationFunctionName;

    @Override
    public String toString() {
        return "ContractCallByEvent{" +
                "obligationFunctionName='" + obligationFunctionName + '\'' +
                '}';
    }
}
