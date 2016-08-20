/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xzclient;

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
public class ClientSocket {
    File fname;
    String sname;

    ClientSocket(File f, String type) {
        fname=f;
        sname=type;
    }    
    
    public void SendType(){
        Socket socket = null;
        try
        {
            String host = "localhost";
            int port = 25000;
            InetAddress address = InetAddress.getByName(host);
            
            socket = new Socket(address, port);
 
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);            
 
            String sendMessage = ""+sname;
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Client says type of uncompressed file's desired compression is : "+sendMessage);
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
            int port = 26000;
            InetAddress address = InetAddress.getByName(host);
            
            socket = new Socket(address, port);
 
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            String sendMessage = ""+fname.length();
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Client says size of uncompressed file is : "+sendMessage);
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
    
    public void SendUncompressed() throws IOException{
        
        int SOCKET_PORT=13267;
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
                System.out.println("Client waiting to send uncompressed files to server...");
                try {
                        sock = servsock.accept();
                        System.out.println("Accepted connection: "+sock);
                        File myFile = new File("C:\\xampp\\apps\\owncloud\\data\\admin\\files\\uncompressed files\\uncompressed.tar");
                        byte[] mybytearray = new byte[(int)myFile.length()];
                        fis = new FileInputStream(myFile);
                        bis = new BufferedInputStream(fis);
                        bis.read(mybytearray,0,mybytearray.length);
                        os = sock.getOutputStream();
                        System.out.println("Sending "+FILE_TO_SEND+"("+mybytearray.length+" bytes)");
                        os.write(mybytearray,0,mybytearray.length);
                        os.flush();
                        System.out.println("Done.");
                        flag = true;
                } catch (Exception e) {
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
