/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.worksheet5;

import com.mpatric.mp3agic.*;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.Mp3File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class MediaInfoSourceFromID3 implements MediaInfoSource {

    
    public void addMediaInfo(MediaItem m) throws Exception {
             //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             Mp3File myMp3File = new Mp3File(m.getAbsolutePath());
             MediaItem mi = new MediaItem();
             
             ID3v1 id3v1Tag;
             if(myMp3File.hasId3v1Tag()){
                id3v1Tag = myMp3File.getId3v1Tag();
                System.out.println(id3v1Tag.getAlbum());
                m.setAlbum(id3v1Tag.getAlbum());
                System.out.println(id3v1Tag.getArtist());
                m.setArtist(id3v1Tag.getArtist());
                System.out.println(id3v1Tag.getTitle());
                m.setTitle(id3v1Tag.getTitle());
             }
             
             ID3v2 id3v2Tag;
             if(myMp3File.hasId3v2Tag()){
                id3v2Tag = myMp3File.getId3v2Tag();
                System.out.println(myMp3File.getId3v2Tag().getAlbum());
                m.setAlbum(id3v2Tag.getAlbum());
                System.out.println(myMp3File.getId3v2Tag().getArtist());
                m.setArtist(id3v2Tag.getArtist());
                System.out.println(myMp3File.getId3v2Tag().getTitle());
                m.setTitle(id3v2Tag.getTitle());
             }
             
             
             
   }


}
