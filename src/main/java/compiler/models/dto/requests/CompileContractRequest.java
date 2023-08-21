package compiler.models.dto.requests;

import compiler.models.contract.ContractToCompile;
import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CompileContractRequest extends ChannelMessagePayload {
    private final ContractToCompile contractToCompile;
}
