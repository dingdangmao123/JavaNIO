	package RedisTools;

	import java.net.*;
	import java.nio.channels.SocketChannel;  
	import java.util.concurrent.*;

	public class cacheRedis{


		public static LinkedBlockingQueue<Redis> cache;

		static{
			cache=new LinkedBlockingQueue<Redis>();
		}

		public static Redis get(){

			
			if(cache.size()>0){

				try{

					return cache.take();

				}catch(Exception e){

					RedisLog.log(null,"cache get exception");
					System.out.println("cache get exception");

				}

			}
			return new Redis();

		}
		public static void set(Redis msg){

			if(cache.size()<100){
				msg.cli=null;
				msg.cmd=null;
				try{
					cache.put(msg);
					

				}catch(Exception e){

					RedisLog.log(msg.cli,"cache exception");
					System.out.println("cache exception");

				}
			}
		}
	}