package mina.server;


import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoHandlerAdapter {
        /**
         * 接收到信息后进行逻辑处理
         */
        @Override
        public void messageReceived(IoSession session, Object message)
                throws Exception {
            byte[] data = (byte[]) message;
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                stringBuilder.append(data[i]);
            }
            System.out.println("服务端收到了消息："+stringBuilder.toString());
        }
    }

