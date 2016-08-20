/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xzserver;

/**
 *
 * @author Rohan
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import static org.apache.commons.io.FileUtils.getFile;


/**
 *
 * @author Rohan
 */
public class CompressGZIP {

    /**
     * @param args the command line arguments
     */
    public void comp() throws IOException, CompressorException {
        // TODO code application logic here
        System.out.println("GZ compression requested");
        final File input= getFile("C:\\xampp\\apps\\owncloud\\data\\admin\\files\\uncompressed files\\gotit.tar");
        final File output=new File("C:\\TARDUMP\\XZ\\done.tar.gz");
        final OutputStream out=new FileOutputStream(output);
        try {
                final CompressorOutputStream cos=new CompressorStreamFactory().createCompressorOutputStream("gz",out);
                try {
                        IOUtils.copy(new FileInputStream(input),cos);
                    }
                finally {
                            cos.close();
                        }
            }
        finally {
                    out.close();
                }
        System.out.println("Gzip compression successful");
    }
    
}
