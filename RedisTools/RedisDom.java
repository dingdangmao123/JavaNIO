package RedisTools;

import java.util.*;
import java.io.*;

import org.jdom2.Document;  
import org.jdom2.Element;  
import org.jdom2.input.SAXBuilder;  


public class RedisDom{

 public static HashMap<String,String> config(){

   HashMap<String,String> config=new HashMap<String,String>();
   StringBuilder sb=new StringBuilder();
   SAXBuilder builder = new SAXBuilder();  
   Document document; 

   try{

     document= builder.build("config.xml");  
     Element root = document.getRootElement();
     Iterator<Element> it=root.getChildren().iterator();  
     Element tmp;
     while(it.hasNext()){
        tmp=it.next();
        config.put(tmp.getName(),tmp.getValue());
    }

    Iterator iterator = config.keySet().iterator();
    String ttmp;

  }catch(Exception e){

    System.out.println(e);
    System.exit(0);

  }

  return config;

  
}

}