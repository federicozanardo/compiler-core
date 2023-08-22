package compiler;

import compiler.module.CompilerModule;
import lcp.lib.communication.module.Module;
import lcp.lib.communication.module.channel.ChannelMessage;
import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lcp.lib.communication.module.channel.ModelChannelUtils;
import lcp.lib.communication.module.channel.ModuleChannel;
import lcp.lib.exceptions.communication.module.RegisterModuleException;
import lcp.lib.exceptions.communication.module.channel.RegisterChannelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storage.module.StorageModule;

public class Core extends Module {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void send(String receiverModuleId, ChannelMessagePayload payload) {

    }

    @Override
    public void receive(ChannelMessage message) {

    }

    @Override
    public ChannelMessage sendAndReceive(String receiverModuleId, ChannelMessagePayload payload) {
        logger.debug("[{}] payload: {}", new Object() {}.getClass().getEnclosingMethod().getName(), payload);
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
        return null;
    }
}
