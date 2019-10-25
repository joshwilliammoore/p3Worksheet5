/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.worksheet5;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author James
 */
public interface DuplicateFinder {

    // classes that implement DuplicateFinder must include a method for this:
    boolean areDuplicates  (MediaItem m1, MediaItem m2); 
    
    
    
    default public Set<Set<MediaItem>>  getAllDuplicates (Set<MediaItem> allMediaItems){
        throw new UnsupportedOperationException("Not written yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    default public Set<MediaItem> getDuplicatesToThis  (Set<MediaItem> inThese, 
                                                  MediaItem toThis) {
      throw new UnsupportedOperationException("Not written yet."); //To change body of generated methods, choose Tools | Templates.
       
    }
       
       
 
}
