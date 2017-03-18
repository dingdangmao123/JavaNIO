package RedisTools;

import java.util.*;
import java.lang.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.channels.SelectionKey;  
import java.nio.channels.Selector;  
import java.nio.channels.ServerSocketChannel;  
import java.nio.channels.SocketChannel;  

import RedisClass.myRedis;

public class RedisRoute{
	
public static HashMap<String,String> cmd;

static{

		cmd=RedisDom.config();
}

public static String parse(SocketChannel sc,String str){

String[] token=str.trim().toLowerCase().split("\\s+");

if(token.length<=1){

	  RedisLog.log(sc,"invalid command");

	  return "invalid command";

}

String key=token[0];

System.out.println("*************");
Iterator iterator = RedisRoute.cmd.keySet().iterator();
String tmp;

 while(iterator.hasNext()){                      
      tmp =(String)iterator.next();
    System.out.println(tmp+": "+RedisRoute.cmd.get(tmp));              
}

	System.out.println("*************");


if(!RedisRoute.cmd.containsKey(key)){

		RedisLog.log(sc,"unknown command");
		return "unknown command";
	}

myRedis app;	

try{

Class<?> cls=Class.forName("RedisClass.Redis"+(String)RedisRoute.cmd.get(key));

app=(myRedis)cls.newInstance();

}catch(Exception e){

	e.printStackTrace();

	RedisLog.log(sc,e.toString());

	return "system error!";
}
return app.run(sc,token);
}
}