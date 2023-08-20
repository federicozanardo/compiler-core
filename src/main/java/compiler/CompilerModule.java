package compiler;

import lcp.lib.communication.module.Module;
import lcp.lib.communication.module.channel.ChannelMessage;
import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lcp.lib.communication.module.channel.ModuleChannel;
import lcp.lib.communication.module.channel.responses.RequestNotFound;
import models.contract.ContractCompiled;
import models.contract.ContractToCompile;
import models.dto.requests.CompileContract;
import models.dto.responses.CompiledContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CompilerModule extends Module {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CompilerService service;

    public CompilerModule() {
        this.service = new CompilerService(this);
    }

    @Override
    public void send(String receiverModuleId, ChannelMessagePayload channelMessagePayload) {
        logger.debug("[{}] payload: {}", new Object() {}.getClass().getEnclosingMethod().getName(), channelMessagePayload);
        ModuleChannel channel = this.findChannel(this.getId(), receiverModuleId);

        if (channel != null) {
            channel.send(new ChannelMessage(this.getId(), channelMessagePayload));
        } else {
            logger.error("Impossible to find a channel with {}!", receiverModuleId);
        }
    }

    @Override
    public void receive(ChannelMessage message) {
        logger.debug("[{}] from: {}, payload: {}", new Object() {}.getClass().getEnclosingMethod().getName(), message.getSenderModuleId(), message.getPayload());
    }

    @Override
    public ChannelMessage sendAndReceive(String receiverModuleId, ChannelMessagePayload channelMessagePayload) {
        logger.debug("[{}] payload: {}", new Object() {}.getClass().getEnclosingMethod().getName(), channelMessagePayload);
        ModuleChannel channel = this.findChannel(this.getId(), receiverModuleId);

        if (channel != null) {
            return channel.sendAndReceive(new ChannelMessage(this.getId(), channelMessagePayload));
        } else {
            logger.error("Impossible to find a channel with {}!", receiverModuleId);
            return null;
        }
    }

    @Override
    public ChannelMessage receiveAndResponse(ChannelMessage message) {
        logger.debug("[{}] from: {}, payload: {}", new Object() {}.getClass().getEnclosingMethod().getName(), message.getSenderModuleId(), message.getPayload());

        if (message.getPayload() instanceof CompileContract) {
            try {
                ContractCompiled contractCompiled = service.compile(((CompileContract) message.getPayload()).getContractToCompile().getSourceCode());
                return new ChannelMessage(this.getId(), new CompiledContract(contractCompiled));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ChannelMessage(this.getId(), new RequestNotFound(message.getPayload().getClass().getSimpleName()));
        }
    }
}
