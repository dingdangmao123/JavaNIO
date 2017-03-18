package RedisClass;

import java.nio.channels.SocketChannel;  

public interface myRedis{
	
	String run(SocketChannel sc,String[] token);


}