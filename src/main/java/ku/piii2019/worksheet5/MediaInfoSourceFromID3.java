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
             
             
             
             if(myMp3File.hasId3v1Tag()){
                myMp3File.getId3v1Tag().getAlbum();
                myMp3File.getId3v1Tag().getArtist();
                myMp3File.getId3v1Tag().getTitle();
             }
             if(myMp3File.hasId3v2Tag()){
                ID3v2 album = myMp3File.getId3v2Tag();
                MediaItem albumData = new MediaItem();
                MediaItem a = albumData.setAlbum(album.toString());
                Mp3File al = new Mp3File(a.getAbsolutePath());
                al.getId3v2Tag().getAlbumArtist();
                myMp3File.getId3v2Tag().getArtist();
                myMp3File.getId3v2Tag().getTitle();
                System.out.println("Boo");
             }else{
                throw new UnsupportedOperationException("Not supported yet.");
             }
             
   }


}
