package demo;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.PrintStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpClient
{
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final String strCname = getClass().getName();

  StringBuffer message = new StringBuffer();
  String strMsg = null;

  public String connect(String host, int port, String strMsgp) throws Exception
  {
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    this.strMsg = strMsgp;
    try
    {
      Bootstrap b = new Bootstrap();
      b.group(workerGroup);
      b.channel(NioSocketChannel.class);

      b.handler(new ChannelInitializer()
      {
        public void initChannel(SocketChannel ch) throws Exception
        {
          ch.pipeline().addLast(new ChannelHandler[] { new TcpClientIntHandler(TcpClient.this.message, TcpClient.this.strMsg) });
        }
      });
      ChannelFuture f = b.connect(host, port).channel().closeFuture().await();
      return this.message.toString();
    }
    finally {
      workerGroup.shutdownGracefully(); }  } 
  public String disDecrypt(String strMsg) { String strFname = " disDecrypt : ";
    String strRe = null;
    long lonFlg;
    StackTraceElement[] subSte;
    int i;
    try { String strIp = "10.180.120.81";
      long t1 = System.currentTimeMillis();
      strRe = connect(strIp, 213, strMsg).toString();
      System.out.println(">>>>>>>>>>" + strRe);
      long t2 = System.currentTimeMillis();
      System.out.println("t1 : " + t1 + " ---- " + " t2 : " + t2);
      System.out.println("t2-t1 : " + (t2 - t1));
    }
    catch (Exception ex)
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

    return strRe; } 
  public String disEncrypt(String strMsg) { String strFname = " disEncrypt : ";
    String strRe = null;
    long lonFlg;
    StackTraceElement[] subSte;
    int i;
    try { String strIp = "10.180.120.81";
      long t1 = System.currentTimeMillis();
      strRe = connect(strIp, 203, strMsg).toString();
      System.out.println(">>>>>>>>>>" + strRe);
      long t2 = System.currentTimeMillis();
      System.out.println("t1 : " + t1 + " ---- " + " t2 : " + t2);
      System.out.println("t2-t1 : " + (t2 - t1));
    }
    catch (Exception ex)
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

    return strRe;
  }
}
