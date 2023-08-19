package models.dto.requests.contract;

public record ContractToCompile(String sourceCode) {

    @Override
    public String toString() {
        return "ContractToCompile{" +
                "sourceCode='" + sourceCode + '\'' +
                '}';
    }
}

