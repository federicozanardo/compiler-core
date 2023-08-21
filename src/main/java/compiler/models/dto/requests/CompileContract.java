package models.dto.requests;

import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import models.contract.ContractToCompile;

@AllArgsConstructor
@Getter
@ToString
public class CompileContract extends ChannelMessagePayload {
    private final ContractToCompile contractToCompile;
}
