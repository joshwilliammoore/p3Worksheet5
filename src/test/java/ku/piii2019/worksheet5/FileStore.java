/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.worksheet5;

import ku.piii2019.worksheet5.MediaInfoSource;
import ku.piii2019.worksheet5.MediaItem;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.fail;

/**
 *
 * @author James
 */
public interface FileStore {
        public Set<MediaItem> getAllMediaItems(String rootTestFolder, 
                                               MediaInfoSource myMediaInfoSource);
        public Set<Set<MediaItem>> getAllDuplicates(String rootTestFolder,  
                                              MediaInfoSource myMediaInfoSource);
        
        public String getRootFolder();
        
        static Set<MediaItem> filenamesToMediaItems(List<String> absoluteFilenames, 
                                               MediaInfoSource myMediaInfoSource){
            Set<MediaItem> output = new HashSet<MediaItem>();

            for (String filename : absoluteFilenames) {        
                MediaItem m = new MediaItem().setAbsolutePath(filename);
                if(myMediaInfoSource!=null) {
                    try {
                        myMediaInfoSource.addMediaInfo(m);
                    }
                    catch (Exception e) {
                        fail(e.toString());
                    }
                }
                output.add(m);
            }
            return output;
        }
                
                
                                                        

}
