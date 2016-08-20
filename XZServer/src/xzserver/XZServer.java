/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xzserver;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rohan
 */
public class XZServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        System.out.println("STARTING RECEIVING UNCOMPRESSED FILE PART");
        
        ReceiveUncompressed ru = new ReceiveUncompressed();
        ru.getType();
        ru.getSize();
        try {
            ru.getUncompressed();
        } catch (IOException ex) {
            Logger.getLogger(XZServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("STARTING COMPRESSION PART");
        System.out.println("compression type: "+ru.type);                
        
        File cf = null;
        String loc = null;
        
        if(ru.type.equals("XZ")){
            System.out.println("Compression 1");
            CompressXZ cxz = new CompressXZ();
            cxz.comp();
            loc = "C:\\TARDUMP\\XZ\\done.tar.xz";
            
        }
        
        else if(ru.type.equals("GZIP")){
            System.out.println("Compression 2");
            CompressGZIP cgz = new CompressGZIP();
            cgz.comp();
            loc = "C:\\TARDUMP\\XZ\\done.tar.gzip";
        }
        
        System.out.println("STARTING SENDING COMPRESSED FILE PART");
        cf = new File(loc);
        System.out.println("File to be sent: "+cf);
        
        ReturnCompressed rc = new ReturnCompressed(cf,ru.type);
        rc.SendType();
        rc.SendSize();
        rc.SendCompressed();
    }
    
}
