/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xzserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

/**
 *
 * @author Rohan
 */
public class CompressXZ {

    /**
     * @param args the command line arguments
     */
    public void comp() throws FileNotFoundException, IOException, Exception {
        
        System.out.println("XZ compression requested");
        FileOutputStream dest = new FileOutputStream("C:\\TARDUMP\\XZ\\done.tar.xz");
        CompressorOutputStream cos = new CompressorStreamFactory().createCompressorOutputStream(CompressorStreamFactory.XZ, dest);
        String input = "C:\\xampp\\apps\\owncloud\\data\\admin\\files\\uncompressed files\\gotit.tar";
        IOUtils.copy(new FileInputStream(input), cos);
        cos.close();
        System.out.println("XZ compression successful");
    }
    
}
