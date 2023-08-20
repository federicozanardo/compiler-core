package models.contract;

import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ContractToCompile {
    private final String sourceCode;
}

