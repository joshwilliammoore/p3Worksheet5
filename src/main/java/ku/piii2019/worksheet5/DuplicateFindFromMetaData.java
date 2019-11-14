/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.worksheet5;

import com.mpatric.mp3agic.Mp3File;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author James
 */
public class DuplicateFindFromMetaData implements DuplicateFinder{

    @Override
    public boolean areDuplicates(MediaItem m1, MediaItem m2) {
        //throw new UnsupportedOperationException("Not written yet."); //To change body of generated methods, choose Tools | Templates.
        
        try
        {
            if(m1.getTitle().trim().equalsIgnoreCase(m2.getTitle().trim())&&
               m1.getAlbum().trim().equalsIgnoreCase(m2.getAlbum().trim())&&
               m1.getArtist().trim().equalsIgnoreCase(m2.getArtist().trim())){
                return true;
            }
        
        }catch(NullPointerException npex) {
            return false;
        }
        return false;
    }

    
}
