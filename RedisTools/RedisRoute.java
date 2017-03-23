package RedisTools;

import java.util.*;
import java.lang.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.channels.SelectionKey;  
import java.nio.channels.Selector;  
import java.nio.channels.ServerSocketChannel;  
import java.nio.channels.SocketChannel; 
import java.lang.reflect.*; 

public class RedisRoute{

	public static HashMap<String,String> cmd;
	static{

					cmd=RedisDom.config();
	}

	public static String parse(SocketChannel sc,String str){

		String[] token=str.trim().toLowerCase().split("\\s+");

		if(token.length<1){

			RedisLog.log(sc,"invalid command");
			return "invalid command";
		}

		String key=token[0];
		if(!RedisRoute.cmd.containsKey(key)){

			RedisLog.log(sc,"unknown command");
			return "unknown command";
		}

		try{

			String[] way=RedisRoute.cmd.get(key).split("\\.");
			if(way.length!=2){
				RedisLog.log(sc,"config error!");
				return "config error!";
			}

			Class<?> cls=Class.forName("RedisClass."+way[0]);
			Object app=cls.newInstance();
			Method action=cls.getMethod(way[1],String.class);

			return (String)(action.invoke(app,str));

		}catch(Exception e){

			e.printStackTrace();
			RedisLog.log(sc,e.toString());

			return "system error!";
		}

	}

}