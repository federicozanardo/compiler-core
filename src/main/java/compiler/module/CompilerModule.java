package compiler.module;

import compiler.models.contract.ContractCompiled;
import compiler.models.dto.compilecontract.CompileContractRequest;
import compiler.models.dto.compilecontract.CompileContractResponse;
import lcp.lib.communication.module.Module;
import lcp.lib.communication.module.channel.ChannelMessage;
import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lcp.lib.communication.module.channel.ModuleChannel;
import lcp.lib.communication.module.channel.responses.RequestNotFound;
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
    public void send(String receiverModuleId, ChannelMessagePayload payload) {
        logger.debug("[{}] payload: {}", new Object() {
        }.getClass().getEnclosingMethod().getName(), payload);
        ModuleChannel channel = this.findChannel(this.getId(), receiverModuleId);

        if (channel != null) {
            channel.send(new ChannelMessage(this.getId(), payload));
        } else {
            logger.error("Impossible to find a channel with {}!", receiverModuleId);
        }
    }

    @Override
    public void receive(ChannelMessage message) {
        logger.debug("[{}] from: {}, payload: {}", new Object() {
        }.getClass().getEnclosingMethod().getName(), message.getSenderModuleId(), message.getPayload());
    }

    @Override
    public ChannelMessage sendAndReceive(String receiverModuleId, ChannelMessagePayload payload) {
        logger.debug("[{}] payload: {}", new Object() {
        }.getClass().getEnclosingMethod().getName(), payload);
        ModuleChannel channel = this.findChannel(this.getId(), receiverModuleId);

        if (channel != null) {
            return channel.sendAndReceive(new ChannelMessage(this.getId(), payload));
        } else {
            logger.error("Impossible to find a channel with {}!", receiverModuleId);
            return null;
        }
    }

    @Override
    public ChannelMessage receiveAndResponse(ChannelMessage message) {
        logger.debug("[{}] from: {}, payload: {}", new Object() {
        }.getClass().getEnclosingMethod().getName(), message.getSenderModuleId(), message.getPayload());

        if (message.getPayload() instanceof CompileContractRequest) {
            try {
                ContractCompiled contractCompiled = service.compile(((CompileContractRequest) message.getPayload()).getContractToCompile().getSourceCode());
                return new ChannelMessage(this.getId(), new CompileContractResponse(contractCompiled));
            } catch (IOException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        } else {
            return new ChannelMessage(this.getId(), new RequestNotFound(message.getPayload().getClass().getSimpleName()));
        }
    }
}
