package mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class TestFactory implements ProtocolCodecFactory {

    private MessageDecoder messageDecoder;
    private MessageEncoder messageEncoder;

    public TestFactory() {
        messageDecoder = new MessageDecoder();
        messageEncoder = new MessageEncoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return messageEncoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return messageDecoder;
    }
}
