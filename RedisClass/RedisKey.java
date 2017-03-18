package RedisClass;

import java.util.*;
import java.nio.channels.SocketChannel;  


public class RedisKey implements myRedis{

 public static HashMap<String,String> storage=new HashMap<String,String>();

 public String run(SocketChannel sc,String[] token){

 			if(token[0].equals("set"))
 			{
 				if(token.length!=3)
 					 return "set error";

 				String old=RedisKey.storage.put(token[1],token[2]);	
				show();
				return old;

 			}else if(token[0].equals("del")){

				if(token.length!=2)
 					 return "del error";
				String old=RedisKey.storage.remove(token[1]);	
				show();
 				return old;

 			}else{
 				
 				if(token.length!=2)
 					 return "key get error";
 				String old=RedisKey.storage.get(token[1]);
				show();
				return old;
 			}


 }
public void show(){
  
	System.out.println("*************");
 Iterator iterator = RedisKey.storage.keySet().iterator();
 String tmp;

 while(iterator.hasNext()){                      
      tmp =(String)iterator.next();
    System.out.println(tmp+":"+RedisKey.storage.get(tmp));              
}
	System.out.println("*************");

}

}