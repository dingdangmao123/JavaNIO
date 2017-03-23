package RedisTools;

import java.util.*;
import java.nio.channels.*;
import java.nio.channels.SelectionKey;   
import java.nio.channels.SocketChannel; 
import java.nio.*;

public class echo{

	public static void write(Redis msg,String res){


		ByteBuffer cb=ByteBuffer.allocate(128);
		for(byte v:res.getBytes())
			cb.put(v);

		cb.flip();

		try{

			msg.cli.write(cb);

		}catch(Exception e){

			e.printStackTrace();
			System.out.println("write exception!");

		}finally{
			
			cb.clear();
		}


	}
}