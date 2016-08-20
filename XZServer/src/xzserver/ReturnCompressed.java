/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xzserver;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Rohan
 */
public class ReturnCompressed {
    File fname;
    String sname;

    ReturnCompressed(File f, String type) {
        fname=f;
        sname=type;        
    }    
    
    public void SendType(){
        Socket socket = null;
        try
        {
            String host = "localhost";
            int port = 27000;
            InetAddress address = InetAddress.getByName(host);
            
            socket = new Socket(address, port);
 
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);            
 
            String sendMessage = ""+sname;
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Compressed file's type sent to the client : "+sendMessage);
            }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        //send compresion type
    }
   
    public void SendSize(){
        Socket socket = null;
        try
        {
            String host = "localhost";
            int port = 28000;
            InetAddress address = InetAddress.getByName(host);
            
            socket = new Socket(address, port);
 
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            String sendMessage = ""+fname.length();
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Compressed file's size sent to the Client : "+sendMessage);
            }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        //send filesize
    }
    
    public void SendCompressed() throws IOException{
        
        int SOCKET_PORT=13268;
        String FILE_TO_SEND=fname.getPath();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        ServerSocket servsock = null;
        Socket sock = null;
        boolean flag = false;
        try {
            servsock = new ServerSocket(SOCKET_PORT);
            while(flag==false){
                System.out.println("Server waiting to send compressed file...");                ;
                try {
                        String loc = null;
                        sock = servsock.accept();
                        System.out.println("Accepted connection: "+sock);
                        if(sname.equals("XZ"))
                            loc= "C:\\TARDUMP\\XZ\\done.tar.xz";
                        else if(sname.equals("GZIP"))
                            loc = "C:\\TARDUMP\\XZ\\done.tar.gzip";
                        File myFile = new File(loc);                        
                        byte[] mybytearray = new byte[(int)myFile.length()];
                        fis = new FileInputStream(myFile);
                        bis = new BufferedInputStream(fis);
                        bis.read(mybytearray,0,mybytearray.length);
                        os = sock.getOutputStream();
                        System.out.println("Sending "+FILE_TO_SEND+"("+mybytearray.length+" bytes)");
                        System.out.println("prewrite");
                        os.write(mybytearray,0,mybytearray.length);
                        System.out.println("postwrite");
                        os.flush();
                        System.out.println("Done.");
                        flag=true;
                } catch (IOException e) {
                    System.out.println("THIS BROKE");
                }
                finally {
                    if(bis!=null) bis.close();
                    if(os!=null) os.close();
                    if(sock!=null) sock.close();
                }
            }
        } catch (Exception e) {
        }
        finally{
            if(servsock!=null) servsock.close();
        }
    }
}
