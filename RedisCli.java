import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.Random;
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.*;

public class RedisCli{
	
	public static void main(String argv[]){

    try{

      Socket socket = new Socket(argv[0],Integer.parseInt(argv[1]));
      OutputStream out=socket.getOutputStream();
      BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedReader bd=new BufferedReader(new InputStreamReader(System.in));
      String tmp;
      int cc;

      while(true){

       try{

        System.out.print(">> ");
        while((tmp=bd.readLine())!=null){

          out.write(tmp.getBytes());
          StringBuilder sb=new StringBuilder();
          while((cc=br.read())!=(int)'*'){
           sb.append((char)cc);

         }

         System.out.println("from server: "+sb.toString());
         System.out.print(">> ");
       }
       
     }catch(Exception e){System.out.println(e);}


  }

 }catch(Exception e){ 
  System.out.println(e);
}

}

}


