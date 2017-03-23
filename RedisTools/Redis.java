package RedisTools;

import java.net.*;
import java.nio.channels.SocketChannel;  

public class Redis{

	public SocketChannel cli;
	public String cmd;
	public String toString(){

		try{

				InetSocketAddress sk=(InetSocketAddress)cli.getRemoteAddress();
				return sk.getPort()+":"+cmd;

			}catch(Exception e){
				
				e.printStackTrace();
			}
				return "error";
}
}