package RedisClass;

import java.util.*;
import java.nio.channels.SocketChannel;  


public class RedisList implements myRedis{

public static LinkedList<String> storage=new LinkedList<String>();

public String run(SocketChannel sc,String[] token){

 			if(token[0].equals("push"))
 			{
 				if(token.length<=1)
 					 return "push error";
 			
 				for(int i=0;i<token.length;i++)
 						RedisList.storage.add(token[i]);

				show();
				return "OK!";

 			}else if(token[0].equals("pop")){

				if(token.length!=1)
 					 return "del error";
				String old=RedisList.storage.pop();	
				show();
 				return old;

 			}
return "OK!";

 }
public void show(){
  
System.out.println("*************");
 Iterator iterator = RedisList.storage.iterator();
 String tmp;

 while(iterator.hasNext()){                      
      tmp =(String)iterator.next();
    System.out.print(tmp+" ");              
}

	System.out.println("\n*************");

}

}