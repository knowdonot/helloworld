package demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.apache.catalina.websocket.MessageInbound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NioClient
{
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final String strCname = getClass().getName();

  private String HOST = "10.167.210.202";
  private int PORT = 0;
  private NioClientHandler objNioClientHandler;
  private Bootstrap bootstrap;
  private Channel channel;
  private static NioClient objNioClient;

  public static synchronized NioClient initialized()
  {
    if (objNioClient == null) {
      objNioClient = new NioClient();
    }
    return objNioClient;
  }

  private Bootstrap getBootstrap()
  {
    String strFname = "getBootstrap : ";

    EventLoopGroup group = new NioEventLoopGroup();
    Bootstrap b = new Bootstrap();
    ((Bootstrap)b.group(group)).channel(NioSocketChannel.class);

    b.option(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true));
    return b;
  }

  private Channel getChannel(String host, int port, MessageInbound objMessageInbound) {
    Channel channel = null;
    try {
      this.objNioClientHandler = new NioClientHandler();
      this.objNioClientHandler.setMessageInbound(objMessageInbound);
      this.bootstrap.handler(new ChannelInitializer()
      {
        protected void initChannel(Channel ch) throws Exception {
          ChannelPipeline pipeline = ch.pipeline();
          pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(2147483647, 0, 4, 0, 4));
          pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
          pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
          pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

          pipeline.addLast("handler", NioClient.this.objNioClientHandler);
        }
      });
      channel = this.bootstrap.connect(host, port).sync().channel();
    }
    catch (Exception e)
    {
      return null;
    }
    return channel;
  }

  private void sendMsg(String msg, int intPort, MessageInbound objMessageInbound) throws Exception {
    if ((objMessageInbound != null) && 
      (intPort > 0) && 
      (msg != null) && (msg.trim().length() > 0)) {
      this.PORT = intPort;
      if (this.bootstrap == null) {
        this.bootstrap = getBootstrap();
        if (this.bootstrap != null) {
          this.channel = getChannel(this.HOST, intPort, objMessageInbound);
        }
      }
      if (this.channel != null)
        this.channel.writeAndFlush(msg).sync();  }  } 
  public String disDecrypt(String strMsg, MessageInbound objMessageInbound) { String strFname = " disDecrypt : ";
    String strRe = null;
    long lonFlg;
    StackTraceElement[] subSte;
    int i;
    try { if ((objMessageInbound == null) || 
        (strMsg == null) || (strMsg.trim().length() <= 0))
        break label179;
      sendMsg(strMsg, 8100, objMessageInbound);
    } catch (Exception ex)
    {
      lonFlg = System.currentTimeMillis();
      this.logger.error(this.strCname + strFname + ex + "||" + lonFlg);
      subSte = ex.getStackTrace();
      i = 0; } for (; i < subSte.length; i++) {
      this.logger.error(
        subSte[i].getClassName() + 
        subSte[i].getMethodName() + 
        ":" + subSte[i].getLineNumber() + 
        "||" + lonFlg);
    }

    label179: return strRe; } 
  public String disEncrypt(String strMsg, MessageInbound objMessageInbound) { String strFname = " disDecrypt : ";
    String strRe = null;
    long lonFlg;
    StackTraceElement[] subSte;
    int i;
    try { if ((objMessageInbound == null) || 
        (strMsg == null) || (strMsg.trim().length() <= 0))
        break label179;
      sendMsg(strMsg, 8000, objMessageInbound);
    } catch (Exception ex)
    {
      lonFlg = System.currentTimeMillis();
      this.logger.error(this.strCname + strFname + ex + "||" + lonFlg);
      subSte = ex.getStackTrace();
      i = 0; } for (; i < subSte.length; i++) {
      this.logger.error(
        subSte[i].getClassName() + 
        subSte[i].getMethodName() + 
        ":" + subSte[i].getLineNumber() + 
        "||" + lonFlg);
    }

    label179: return strRe;
  }
}