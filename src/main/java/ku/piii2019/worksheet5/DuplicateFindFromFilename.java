/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.worksheet5;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author James
 */
public class DuplicateFindFromFilename implements DuplicateFinder {

    @Override
    public boolean areDuplicates(MediaItem m1, MediaItem m2) {
        //throw new UnsupportedOperationException("Not written yet."); //To change body of generated methods, choose Tools | Templates.

        Path p1 = Paths.get(m1.getAbsolutePath());
        Path p2 = Paths.get(m2.getAbsolutePath());
        return p1.getFileName().equals(p2.getFileName());
    }
    
}
