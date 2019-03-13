package mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MessageEncoder extends ProtocolEncoderAdapter{
    @Override
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput out) throws Exception {
        try {
            //System.out.println("客户端进入编码器");
            byte[] b = (byte[])o;
            int sum =b.length;
            IoBuffer io = IoBuffer.allocate(sum);
            io.setAutoExpand(true);
            io.clear();
            io.position(0);// 清空缓存并重置
            io.put(b);
            io.flip();
            out.write(io);
        } catch (Exception e) {

        }finally{
            if(out!=null){
                out.flush();
            }
        }
    }
}
