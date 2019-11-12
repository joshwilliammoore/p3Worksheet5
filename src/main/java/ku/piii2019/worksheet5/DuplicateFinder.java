/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.worksheet5;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author James
 */
public interface DuplicateFinder {

    // classes that implement DuplicateFinder must include a method for this:
    boolean areDuplicates  (MediaItem m1, MediaItem m2);
    
    
    
    default public Set<Set<MediaItem>>  getAllDuplicates (Set<MediaItem> allMediaItems){
        //throw new UnsupportedOperationException("Not written yet."); //To change body of generated methods, choose Tools | Templates.
            
        Set<Set<MediaItem>> result = new LinkedHashSet<>();
        Set<MediaItem> dups1 = new LinkedHashSet<>();
        Set<MediaItem> dups2 = new LinkedHashSet<>();
        Set<String> uniqueFiles = new LinkedHashSet<>();
        Set<String> dupeFiles = new LinkedHashSet<>();
        Set<String> temp = new LinkedHashSet<>();
        
        for(MediaItem item : allMediaItems){
            /*Path p = Paths.get(item.getAbsolutePath());
            String filename = p.getFileName().toString();*/
            String filename = item.getTitle();
            System.out.println(filename);
            if(!uniqueFiles.contains(filename)){
                uniqueFiles.add(filename);
            }else{
                dupeFiles.add(filename);
            }
        }
        
        for(MediaItem item : allMediaItems){
            Path p = Paths.get(item.getAbsolutePath());
            String filename = p.getFileName().toString();
            //String filename = item.getTitle();
            if(dupeFiles.contains(filename)){
                if(temp.isEmpty()){
                    temp.add(filename);
                    dups1.add(item);
                }
                if(!temp.contains(filename)){
                    dups2.add(item);
                }else{
                    dups1.add(item);
                }
            }
        }
        
        result.add(dups1);
        result.add(dups2);
        return result;
    }
    
    
    
    default public Set<MediaItem> getDuplicatesToThis  (Set<MediaItem> inThese, 
                                                  MediaItem toThis) {
      //throw new UnsupportedOperationException("Not written yet."); //To change body of generated methods, choose Tools | Templates.
       Set<MediaItem> result = new HashSet<>();
      for(MediaItem i:inThese){	 
        if(i.getTitle().equalsIgnoreCase(toThis.getTitle().trim())&&
            i.getAlbum().equalsIgnoreCase(toThis.getAlbum().trim())&&
            i.getArtist().equalsIgnoreCase(toThis.getArtist().trim())){
            result.add(i);
        }   	  		     	     	  	
      }
      System.out.println(result);
      return result;
    }
       
       
 
}
