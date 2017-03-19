import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.channels.SelectionKey;  
import java.nio.channels.Selector;  
import java.nio.channels.ServerSocketChannel;  
import java.nio.channels.SocketChannel;  
import java.util.regex.*;



import RedisClass.myRedis;
import RedisTools.*;



public  class RedisServer{


	
		public static LinkedList<SelectionKey> key;
		public static LinkedBlockingQueue<SelectionKey> client;
		public static LinkedBlockingQueue<Redis> command;

		public static Selector selector;
		public static SelectionKey sk;
		public static ServerSocketChannel server;
		public static Thread tp;

		public static void main(String[] argv){

		System.out.println("server start........");

		init(argv);
		run();

	}

	public static void run(){

		int flag=0;
		Set selectedKeys;
		SocketChannel sockcli;
		SelectionKey keycli;
		SelectionKey keytmp;
		ByteBuffer cb=ByteBuffer.allocate(128);
		Iterator it;
		Redis redisApp;

		while(true){
				
				try{

						flag=selector.select();

				}catch(Exception e){

					e.printStackTrace();

					continue;
				}

				if(flag<=0)
						continue;

				selectedKeys=selector.selectedKeys(); 
		 		it= selectedKeys.iterator();

				while(it.hasNext()){ 


						keycli=(SelectionKey)it.next(); 

						try{
							
							//Thread.sleep(3000);

    					if(keycli.isAcceptable()) { 

    						System.out.println("accept");
    						sockcli=(SocketChannel)((ServerSocketChannel)(keycli.channel())).accept();
								sockcli.configureBlocking(false);
    						keytmp=sockcli.register(selector,SelectionKey.OP_READ);
    						client.add(keytmp);
    					
							}else if(keycli.isReadable()){

								SocketChannel ssk=((SocketChannel)(keycli.channel()));
								StringBuilder sbcmd=new StringBuilder();
								
								if(-1==ssk.read(cb)){

								   		keycli.cancel();
											ssk.close();
											key.remove(keycli);
											System.out.println("client close.....");
											continue;

								   	}

								cb.flip();

								while(cb.hasRemaining()){
										sbcmd.append((char)cb.get());
								}

								cb.clear();
								

								redisApp=cacheRedis.get();
								redisApp.cli=ssk;
								redisApp.cmd=sbcmd.toString();

								command.put(redisApp);
								String slog=sbcmd.toString();
								RedisLog.log(ssk,slog.substring(0,slog.length()-1));
								
							}

						

			}catch(Exception e){

				e.printStackTrace();
			
			}

			it.remove();
				

		}



	}



	}

public static void init(String[] argv){

		try{

  	key=new LinkedList<SelectionKey>();
		client=new LinkedBlockingQueue<SelectionKey>();
		command=new LinkedBlockingQueue<Redis>();


  	server= ServerSocketChannel.open();

		server.socket().bind(new InetSocketAddress("127.0.0.1",Integer.parseInt(argv[0])));

		server.configureBlocking(false);

		selector=Selector.open();
		sk=server.register(selector,SelectionKey.OP_ACCEPT);

		pusher app=new pusher(client,command);
		tp=new Thread(app);
		tp.start();

		}catch(Exception e){ 

		e.printStackTrace();
		System.exit(0);

	}


	}

} 



