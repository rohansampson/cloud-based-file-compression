/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xzclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;

/**
 *
 * @author Rohan
 */
public class TARmaker {
    
    File file;
    
    public TARmaker(File f) throws IOException, FileNotFoundException, ArchiveException{
        file=f;
        maketarball();
        //changing the original file
        f=new File("C:\\xampp\\apps\\owncloud\\data\\admin\\files\\uncompressed files\\uncompressed.tar");
    }
    
    public void maketarball() throws FileNotFoundException, IOException, ArchiveException{
        
                System.out.println("TAR archiving started");
                System.out.println("Reading from :"+file.getPath());
                System.out.println("Writing to : C:\\xampp\\apps\\owncloud\\data\\admin\\files\\uncompressed files\\uncompressed.tar");
                /* Output Stream - that will hold the physical TAR file */
                OutputStream tar_output = new FileOutputStream(new File("C:\\xampp\\apps\\owncloud\\data\\admin\\files\\uncompressed files\\uncompressed.tar"));
                /* Create Archive Output Stream that attaches File Output Stream / and specifies TAR as type of compression */
                ArchiveOutputStream my_tar_ball = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.TAR, tar_output);
                /* Create Archieve entry - write header information*/
                File tar_input_file= file;
                TarArchiveEntry tar_file = new TarArchiveEntry(tar_input_file);
                /* length of the TAR file needs to be set using setSize method */
                tar_file.setSize(tar_input_file.length());
                my_tar_ball.putArchiveEntry(tar_file);
                IOUtils.copy(new FileInputStream(tar_input_file), my_tar_ball);
                /* Close Archieve entry, write trailer information */
                my_tar_ball.closeArchiveEntry();                
                my_tar_ball.finish(); 
                /* Close output stream, our files are zipped */
                tar_output.close();
    }
}
