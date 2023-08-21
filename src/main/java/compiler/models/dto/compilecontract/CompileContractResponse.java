package compiler.models.dto.compilecontract;

import compiler.models.contract.ContractCompiled;
import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CompileContractResponse extends ChannelMessagePayload {
    private final ContractCompiled contractCompiled;
}
