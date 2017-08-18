package ssl.socket.client;


import java.net.*;
import java.io.*;
import javax.net.ssl.*;


public class SSLClient {
    public static void main(String args[])
    {
        try
        {
       
        SSLSocketFactory factory=(SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslsocket=(SSLSocket) factory.createSocket("127.0.0.1",2005);

        sslsocket.startHandshake();
        
        DataOutputStream os=new DataOutputStream(sslsocket.getOutputStream());
        DataInputStream is=new DataInputStream(sslsocket.getInputStream());

        
        String str="helloworld";
        os.writeBytes(str);

       
        String responseStr;
        if((responseStr=is.readUTF())!=null)
        {
            System.out.println(responseStr);
        }

        os.close();
        is.close();
        sslsocket.close();
        }
        catch(UnknownHostException e)
        {
             System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}