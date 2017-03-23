package RedisClass;

import java.util.*;
import java.nio.channels.SocketChannel;  


public class RedisKey{

	public static HashMap<String,String> storage=new HashMap<String,String>();

	public String set(String str){

		String[] token=str.trim().toLowerCase().split("\\s+");
		if(token.length!=3)
			return "set error";

		String old=RedisKey.storage.put(token[1],token[2]);	
		show();
		return old;

	}

	public String del(String str){

		String[] token=str.trim().toLowerCase().split("\\s+");
		if(token.length!=2)
			return "del error";
		String old=RedisKey.storage.remove(token[1]);	
		show();
		return old;


	}

	public String get(String str){
		
		String[] token=str.trim().toLowerCase().split("\\s+");
		if(token.length!=2)
			return "key get error";
		String old=RedisKey.storage.get(token[1]);
		show();
		return old;


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