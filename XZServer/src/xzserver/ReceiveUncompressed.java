/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xzserver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Rohan
 */
public class ReceiveUncompressed {
    public File file;
    public String type;
    public int fsize;
    String sizestr;
    
    
    public void getType(){
        boolean flag = false;
        Socket socket = null;
        try
        {
 
            int port = 25000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 25000 for uncompressed file's desired type of compression");
 
            //Server is running always. This is done using this while(true) loop
            while(flag==false)
            {
                //Reading the message from the client
                socket = serverSocket.accept();                
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                type = br.readLine();
                System.out.println("Server recieves desired compression type to be: "+type);
                flag=true;
                 }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
        
        //receive compression type as string
    }
    
    public void getSize(){
        
        boolean flag = false;
        Socket socket = null;
        try
        {
 
            int port = 26000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 26000 for file size of uncompressed file");            
            
            //Server is running always. This is done using this while(true) loop
            while(flag==false)
            {
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String sizestr = br.readLine();
                fsize = Integer.parseInt(sizestr);                
                System.out.println("Size of uncompressed file is: "+fsize);
                flag=true;
                 }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
        
        //get File size in bytes
    }
    
    
    public void getUncompressed() throws IOException{
        
    int SOCKET_PORT = 13267; 
    String SERVER = "127.0.0.1"; 
    String FILE_TO_RECEIVED = "C:\\xampp\\apps\\owncloud\\data\\admin\\files\\uncompressed files\\gotit.tar";  
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
        System.out.println("Receiving of Uncompressed file started");
    try {
      sock = new Socket(SERVER, SOCKET_PORT);
      System.out.println("Connecting to Client to receive uncompressed file...");

      // receive file
      byte [] mybytearray  = new byte [6022386];
      InputStream is = sock.getInputStream();
      fos = new FileOutputStream(FILE_TO_RECEIVED);
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead;

      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

      bos.write(mybytearray, 0 , current);
      bos.flush();
      System.out.println("File " + FILE_TO_RECEIVED
          + " downloaded (" + current + " bytes read)");
    }
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
        System.out.println("File Received");
        //recieve uncompressed type as string
    }
    
}
