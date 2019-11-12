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
        
        return !m1.getAbsolutePath().trim().equalsIgnoreCase(m2.getAbsolutePath().trim());
    }

    
}
