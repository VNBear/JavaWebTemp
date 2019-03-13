package mina.client;

import mina.TestFactory;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

public class MinaClient  extends IoHandlerAdapter {
    private IoConnector ioConnector;

    public MinaClient(String host, int port)
    {
        ioConnector = new NioSocketConnector();
        //过滤信息，封装成文本格式输出
        ioConnector.getFilterChain().addLast("test", new ProtocolCodecFilter(new TestFactory()));

        ioConnector.setHandler(new ClientHandler("Hello world!"));
        ioConnector.connect(new InetSocketAddress(host, port));
    }
}
