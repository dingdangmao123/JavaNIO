package RedisClass;

import java.util.*;
import java.nio.channels.SocketChannel;  


public class RedisList{

public static LinkedList<String> storage=new LinkedList<String>();

public String push(String str){

				String[] token=str.trim().toLowerCase().split("\\s+");
 				if(token.length<2)
 					 return "push error";

				for(int i=token.length-1;i>0;i--)
 						RedisList.storage.add(token[i]);

				show();
				return "OK!";


}
public String pop(String str){

				String[] token=str.trim().toLowerCase().split("\\s+");
				for(String v:token)
					 System.out.println(v);
				if(token.length!=1)
 					 return "pop error";
 				if(RedisList.storage.size()==0)
 					 return "there is no element";

				String old=RedisList.storage.pop();	
				show();
 				return old;
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