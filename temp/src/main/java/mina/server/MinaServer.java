package mina.server;

import mina.TestFactory;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MinaServer {
    private IoAcceptor ioAcceptor;

    public MinaServer(int port) {
        ioAcceptor = new NioSocketAcceptor();
        try {
            //设置处理器
            ioAcceptor.setHandler(new ServerHandler());
            //绑定端口
            ioAcceptor.bind(new InetSocketAddress(port));
            //规定协议，设定过滤，这里用TextLineCodecFactory文本协议
            ioAcceptor.getFilterChain().addLast("test", new ProtocolCodecFilter(new TestFactory()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
