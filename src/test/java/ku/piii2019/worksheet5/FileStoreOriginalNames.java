/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.worksheet5;

import ku.piii2019.worksheet5.MediaInfoSource;
import ku.piii2019.worksheet5.MediaItem;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.fail;

/**
 *
 * @author James
 */
public class FileStoreOriginalNames implements FileStore{
    
    
    @Override
    public String getRootFolder() {
        return "original_filenames";
    }
   
    
    @Override
      public  Set<MediaItem> getAllMediaItems(String absoluteTestFolder,  
                                              MediaInfoSource myMediaInfoSource) {
        List<String> relativeFilenames = new ArrayList<String>();
        relativeFilenames.add("collection-A" + 
                              File.separator + "Anshlavs - Second Trip - clip.mp3");
        relativeFilenames.add("collection-A" + 
                              File.separator + 
                              "DARKPOP BAND ANGELIQUE - PERFECT WORLD (AMBIENT) - clip.mp3");
        relativeFilenames.add("collection-A" + 
                              File.separator + "Gem Reflection - Tubeman - clip.mp3");
        relativeFilenames.add("collection-A" + 
                              File.separator + "IX - la chichonera - clip.mp3");
        relativeFilenames.add("collection-A" + 
                              File.separator + "Omnibrain - Neverending - clip.mp3");
        relativeFilenames.add("collection-A" + 
                              File.separator + "After Many Days" + 
                              File.separator + "Cannibal Eyes - clip.mp3");
        relativeFilenames.add("collection-A" + 
                              File.separator + "Freak Fandango Orchestra - No means no - clip.mp3");
        relativeFilenames.add("collection-A" + 
                              File.separator + "Freak Fandango Orchestra" + 
                              File.separator + "Freak Fandango Orchestra - No means no - clip.mp3");
        relativeFilenames.add("collection-A" + 
                              File.separator + "Orxata Sound System" + 
                              File.separator + "als nous amos - clip.mp3");
        relativeFilenames.add("collection-A" + 
                              File.separator + "Orxata Sound System" + 
                              File.separator + "fuster meets guevara - clip.mp3");
        relativeFilenames.add("collection-B" + 
                              File.separator + "Freak Fandango Orchestra - No means no - clip.mp3");
        relativeFilenames.add("collection-B" + 
                              File.separator + "Red Horizons - Shadows of Promises - clip.mp3");
        relativeFilenames.add("collection-B" + 
                              File.separator + "Richard Stallman - Guantanamero - clip.mp3");
        relativeFilenames.add("collection-B" + 
                              File.separator + "Stellardrone - Nightscape - clip.mp3");
        relativeFilenames.add("collection-B" + 
                              File.separator + "Wortrausch - Sommerzeit (Doppelzeit) - (Live) - clip.mp3");
        relativeFilenames.add("collection-B" + 
                              File.separator + "After Many Days" + 
                              File.separator + "I Could Burn Your House - clip.mp3");
        relativeFilenames.add("collection-B" + 
                              File.separator + "Orxata Sound System" + 
                              File.separator + "fuster meets guevara - clip.mp3");
        Set<MediaItem> output = new HashSet<MediaItem>();
        
        
        List<String> absoluteFilenames = new ArrayList<>();
        for (String filename : relativeFilenames) {
            String abs = Paths.get(absoluteTestFolder, 
                                   getRootFolder(), 
                                   filename).toString();
            absoluteFilenames.add(abs);
        }
        return FileStore.filenamesToMediaItems(absoluteFilenames, myMediaInfoSource);

    }
    @Override  
    public Set<Set<MediaItem>> getAllDuplicates(String absoluteTestFolder,  
                                              MediaInfoSource myMediaInfoSource) {
        List<String> firstDuplicateFilenames = new ArrayList<>();
        firstDuplicateFilenames.add("collection-A" + 
                                    File.separator + "Freak Fandango Orchestra" + 
                                    File.separator + "Freak Fandango Orchestra - No means no - clip.mp3");
        firstDuplicateFilenames.add("collection-A" + 
                                    File.separator + "Freak Fandango Orchestra - No means no - clip.mp3");
        firstDuplicateFilenames.add("collection-B" + 
                                    File.separator + "Freak Fandango Orchestra - No means no - clip.mp3");
        List<String> secondDuplicateFilenames = new ArrayList<>();
        secondDuplicateFilenames.add("collection-A" + 
                                    File.separator + "Orxata Sound System" + 
                                    File.separator + "fuster meets guevara - clip.mp3");
        secondDuplicateFilenames.add("collection-B" + 
                                    File.separator + "Orxata Sound System" + 
                                    File.separator + "fuster meets guevara - clip.mp3");

        List<String> firstAbsoluteFilenames = new ArrayList<>();
        for (String item : firstDuplicateFilenames) {
            String abs = Paths.get(absoluteTestFolder, 
                                   getRootFolder(),
                                   item).toString();
            
            firstAbsoluteFilenames.add(abs);
        }
        Set<MediaItem> firstDuplicate = FileStore.filenamesToMediaItems(firstAbsoluteFilenames, myMediaInfoSource);
        
        List<String> secondAbsoluteFilenames = new ArrayList<>();
        for (String item : secondDuplicateFilenames) {            
            String abs = Paths.get(absoluteTestFolder, 
                                   getRootFolder(),
                                   item).toString();
            secondAbsoluteFilenames.add(abs);
        }
        Set<MediaItem> secondDuplicate = FileStore.filenamesToMediaItems(secondAbsoluteFilenames, myMediaInfoSource);

        Set<Set<MediaItem>> duplicates = new HashSet<>();
        duplicates.add(firstDuplicate);
        duplicates.add(secondDuplicate);

        return duplicates;
    }

  
}
