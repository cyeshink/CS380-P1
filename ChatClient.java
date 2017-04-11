import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient{

    public static void main(String[] args) throws IOException{
        int port = 38001;
        String host = "codebank.xyz";
        
       try(Socket socket = new Socket(host,port)){
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            Scanner kb = new Scanner(System.in);
            String chat = "";
            Runnable reader = () -> {
                while (socket.isConnected()){  
                    String inchat;  
                    try{	
                        inchat = br.readLine();
                        if(!inchat.equals(null)){
                            System.out.println(inchat);
                        }  
                    } catch (Exception e){
                            return;
                    } 
	            }
            };
            Thread readerThread = new Thread(reader);
            String client;
            System.out.print("Please enter your username: ");
            client = kb.nextLine();
            out.printf(client + "%n");
            readerThread.start();            
            while(socket.isConnected()){
                out.printf(chat = kb.nextLine() + "%n");               
            }
       }
    }
}