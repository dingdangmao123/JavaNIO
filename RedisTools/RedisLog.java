package RedisTools;
import java.nio.channels.SocketChannel; 
import java.net.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.net.*;
import java.util.*;

public class RedisLog{

	public static BufferedWriter bufflog;

	static{
	try{
	
  bufflog=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("redis.log")));
  
	}catch(Exception e){}

	}
	public static void log(SocketChannel sc,String str){
			
		try{

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		InetSocketAddress iaddr=(InetSocketAddress)sc.getRemoteAddress();
		RedisLog.bufflog.write("["+iaddr.getPort()+" "+df.format(new Date())+"] "+str);
		RedisLog.bufflog.newLine();
		RedisLog.bufflog.flush();

		}catch(Exception e){

		e.printStackTrace();
		System.out.println("log exception!");

		}
		}}
