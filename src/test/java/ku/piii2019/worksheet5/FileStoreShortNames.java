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

/**
 *
 * @author James
 */
public class FileStoreShortNames implements FileStore {
    
    @Override
    public String getRootFolder() {
        return "short_filenames";
    }
    
    
    public  Set<MediaItem> getAllMediaItems(String absoluteTestFolder,  
                                              MediaInfoSource myMediaInfoSource) {
        List<String> relativeFilenames = new ArrayList<String>();
        relativeFilenames.add("collection-A" + File.separator + "file1.mp3");
        relativeFilenames.add("collection-A" + File.separator + "file2.mp3");
        relativeFilenames.add("collection-A" + File.separator + "file3.mp3");
        relativeFilenames.add("collection-A" + File.separator + "file4.mp3");
        relativeFilenames.add("collection-A" + File.separator + "file5.mp3");
        relativeFilenames.add("collection-A" + File.separator + "folder1" + 
                                               File.separator + "file1.mp3");
        relativeFilenames.add("collection-A" + File.separator + "file6.mp3");
        relativeFilenames.add("collection-A" + File.separator + "folder2" + 
                                               File.separator + "file1.mp3");
        relativeFilenames.add("collection-A" + File.separator + "folder3" + 
                                               File.separator + "file1.mp3");
        relativeFilenames.add("collection-A" + File.separator + "folder3" + 
                                               File.separator + "file2.mp3");
        relativeFilenames.add("collection-B" + File.separator + "item1.mp3");
        relativeFilenames.add("collection-B" + File.separator + "item2.mp3");
        relativeFilenames.add("collection-B" + File.separator + "item3.mp3");
        relativeFilenames.add("collection-B" + File.separator + "item4.mp3");
        relativeFilenames.add("collection-B" + File.separator + "item5.mp3");
        relativeFilenames.add("collection-B" + File.separator + "directory1" + 
                                               File.separator + "item1.mp3");
        relativeFilenames.add("collection-B" + File.separator + "directory2" + 
                                               File.separator + "item1.mp3");
        
        List<String> absoluteFilenames = new ArrayList<>();
        for (String filename : relativeFilenames) {
            String abs = Paths.get(absoluteTestFolder, 
                                   getRootFolder(), 
                                   filename).toString();
            absoluteFilenames.add(abs);
        }
        return FileStore.filenamesToMediaItems(absoluteFilenames, myMediaInfoSource);
    }
    public  Set<Set<MediaItem>> getAllDuplicates(String absoluteTestFolder,  
                                              MediaInfoSource myMediaInfoSource) {
        List<String> firstDuplicateFilenames = new ArrayList<>();
        firstDuplicateFilenames.add("collection-A" + 
                                    File.separator + "folder2" + 
                                    File.separator + "file1.mp3");
        firstDuplicateFilenames.add("collection-A" + 
                                    File.separator + "file3.mp3");
        firstDuplicateFilenames.add("collection-B" + 
                                    File.separator + "item1.mp3");
        List<String> secondDuplicateFilenames = new ArrayList<>();
        secondDuplicateFilenames.add("collection-A" + 
                                    File.separator + "folder3" + 
                                    File.separator + "file2.mp3");
        secondDuplicateFilenames.add("collection-B" + 
                                    File.separator + "directory2" + 
                                    File.separator + "item1.mp3");

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
