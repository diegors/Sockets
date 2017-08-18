package ssl.socket.server;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.net.ssl.*;

public class SSLServer {
    public static void main(String args[]) throws Exception
    {
        try{
        //Create a SSLServersocket
        SSLServerSocketFactory factory=(SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket sslserversocket=(SSLServerSocket) factory.createServerSocket(2005);
        
        SSLSocket sslsocket=(SSLSocket) sslserversocket.accept();
        
        DataInputStream is=new DataInputStream(sslsocket.getInputStream());
        PrintStream os=new PrintStream(sslsocket.getOutputStream());
        while(true)  
        {
            
            String input=is.readUTF();
            String ketqua=input.toUpperCase();
            os.println(ketqua);
        }
        }
        catch(IOException e)
        {
           System.out.print(e);
        }
    }
}