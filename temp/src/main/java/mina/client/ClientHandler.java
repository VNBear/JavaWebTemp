package mina.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import util.ByteUtil;

public class ClientHandler extends IoHandlerAdapter {
    //发送给服务端的消息
    private String sendToServer;

    public ClientHandler(String words) {
        sendToServer = words;
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        session.write(getByte(sendToServer.getBytes()));
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        //Thread.sleep(100);
        session.write(getByte(sendToServer.getBytes()));
    }

    private byte[] getByte(byte[] bytes) {
        byte[] datas = new byte[bytes.length + 4];
        byte[] head = ByteUtil.intToByteArray(bytes.length);
        System.arraycopy(head, 0, datas, 0, head.length);
        System.arraycopy(bytes, 0, datas, 4, bytes.length);
        return datas;
    }
}