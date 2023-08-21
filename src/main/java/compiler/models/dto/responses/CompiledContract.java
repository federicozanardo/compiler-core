package models.dto.responses;

import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import models.contract.ContractCompiled;

@AllArgsConstructor
@Getter
@ToString
public class CompiledContract extends ChannelMessagePayload {
    private final ContractCompiled contractCompiled;
}
