/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.worksheet5;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author James
 */
public class FileServiceImpl implements FileService {

    @Override
    public Set<MediaItem> getAllMediaItems(String rootFolder) {
        Path p = Paths.get(rootFolder);
        if (!p.isAbsolute()) {
            Path currentWorkingFolder = Paths.get("").toAbsolutePath();
            rootFolder = Paths.get(currentWorkingFolder.toString(), rootFolder).toString();
        }
        Set<MediaItem> items = new HashSet<>();
        SimpleFileVisitor myVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith("mp3")) {
                    MediaItem m = new MediaItem();
                    m.setAbsolutePath(file.toString());
                    items.add(m);
                }
                return FileVisitResult.CONTINUE;
            }
        };
        try {
            Files.walkFileTree(Paths.get(rootFolder), myVisitor);
        } catch (IOException ex) {}
        return items;
    }

    @Override
    public Set<MediaItem> getItemsToRemove(Set<Set<MediaItem>> duplicates) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<MediaItem> dupes = new ArrayList();
        Set<String> temp = new LinkedHashSet();
        Set<MediaItem> result = new LinkedHashSet();
        duplicates.forEach(dupes::addAll);
        
        for(MediaItem item : dupes){
            Path p = Paths.get(item.getAbsolutePath());
            String filename = p.getFileName().toString();
            
            if(!temp.contains(filename)){
                temp.add(filename);
            }else{
                result.add(item);
            }
        }
        
        return result;
    
    }
    
    @Override
    public boolean removeFiles(Set<MediaItem> listToRemove) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /**
        *
        * @param file
        * @param attrs
        * @return
        */
        String rootFolder = "test_folders";
        Path p = Paths.get(rootFolder);
        
            if(!p.isAbsolute()){
                Path currentWorkingFolder = Paths.get("").toAbsolutePath();
                rootFolder = Paths.get(currentWorkingFolder.toString(), rootFolder).toString();
            }
        
        SimpleFileVisitor m = new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
                
                
                for(MediaItem item : listToRemove){
                    Path d = Paths.get(item.getAbsolutePath());
                    if(file.equals(d)){
                        Files.delete(file);
                        System.out.println(file.getFileName());
                    }
                }
                return FileVisitResult.CONTINUE;
                
                
            }   
        };
        
        try{
            Files.walkFileTree(Paths.get(rootFolder), m);
            
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }



}
