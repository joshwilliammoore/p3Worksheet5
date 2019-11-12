/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.worksheet5;

import ku.piii2019.worksheet5.MediaInfoSource;
import ku.piii2019.worksheet5.DuplicateFindFromMetaData;
import ku.piii2019.worksheet5.DuplicateFindFromFilename;
import ku.piii2019.worksheet5.DuplicateFinder;
import ku.piii2019.worksheet5.MediaItem;
import ku.piii2019.worksheet5.MediaInfoSourceFromID3;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author James
 */
@RunWith(Parameterized.class)
public class DuplicateFinderTest {
    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        
        Collection<Object[]> listOfInstances = new ArrayList<>();
        
            
        // for Question 1:
        listOfInstances.add(new Object[]{new DuplicateFindFromFilename(), 
                                         new FileStoreOriginalNames(), 
                                         null, 
                                         rightAnswersForFilename});
        // for Question 2: 
        listOfInstances.add(new Object[]{new DuplicateFindFromMetaData(),
                                         new FileStoreShortNames(),
                                         new MediaInfoSourceFromID3(), 
                                         rightAnswersForID3});
        
        return listOfInstances;
    }
    public DuplicateFinderTest(DuplicateFinder testThisOneNext, 
                               FileStore fs, 
                               MediaInfoSource mis, 
                               List<Boolean> rightAnswers) {
       instance =  testThisOneNext;
       fileStore = fs;
       mediaInfoSource = mis;
       rightAnswersForAreDuplicates = rightAnswers;
    }
    
    DuplicateFinder instance = null;
    FileStore fileStore = null;
    MediaInfoSource mediaInfoSource = null;
    List<Boolean> rightAnswersForAreDuplicates = null;
    static List<Boolean> rightAnswersForFilename = Arrays.asList(true, true, false);
    static List<Boolean> rightAnswersForID3 = Arrays.asList(false, false, true);
    
String sep = File.separator;
 List<MediaItem> item1list = Arrays.asList(new MediaItem()
                                                .setAbsolutePath("c:" + sep + "file.mp3"),
                                              new MediaItem()
                                                .setAbsolutePath("c:" + sep + "file.mp3")
                                                .setTitle("title")
                                                .setArtist("artist"),
                                              new MediaItem()
                                                .setTitle("title")
                                                .setArtist("artist")
                                                .setAlbum("album") 
                                                .setAbsolutePath("c:" + sep + "file.mp3")
                                                        );
    List<MediaItem> item2list = Arrays.asList(new MediaItem()
                                                .setAbsolutePath("c:" + sep + "file.mp3"),
                                              new MediaItem()
                                                .setAbsolutePath("c:" + sep + "sameFilename" + sep +"file.mp3")
                                                .setTitle("title")
                                                .setArtist("artist"),
                                              new MediaItem()
                                                .setTitle("title ")
                                                .setArtist(" artist")
                                                .setAlbum("Album") 
                                                .setAbsolutePath("c:" + sep + "differentFilename.mp3")
                                                        );    

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
//        Workshop5TestHelper.deleteFolderRecursively
//                    (new File(Workshop5TestHelper.TEMP_INPUT_FOLDER_FOR_ORIGINAL_FILENAMES));
        Workshop5TestHelper.
                deleteFolderWithFileVisitor(Workshop5TestHelper.TEST_SCRATCH_FOLDER);
    }

    /**
     * Test of getAllDuplicates method, of class DuplicateFindFromFilename.
     */
    //@Ignore
    @Test
    public void testGetAllDuplicates() {
        System.out.println("getAllDuplicates");
        
        initializeTestFolder();
        
        String rootTestFolder = Paths.get(Workshop5TestHelper.TEST_SCRATCH_FOLDER)
                                     .toAbsolutePath()
                                     .toString();
        
        Set<MediaItem> allMediaItems = fileStore.getAllMediaItems(rootTestFolder, mediaInfoSource);
        Set<Set<MediaItem>> expResult = fileStore.getAllDuplicates(rootTestFolder, mediaInfoSource);
        Set<Set<MediaItem>> result = instance.getAllDuplicates(allMediaItems);
        
        System.out.println("the expected result:");
        Workshop5TestHelper.print1(expResult);
        System.out.println("the actual result:");
        Workshop5TestHelper.print1(result);

        assertEquals(expResult, result);
    }
  //  @Ignore
    @Test
    public void testGetDuplicates() {
        System.out.println("getDuplicates");
        
        initializeTestFolder();
        
        String rootTestFolder = Paths.get(Workshop5TestHelper.TEST_SCRATCH_FOLDER)
                                     .toAbsolutePath()
                                     .toString();
        
        Set<MediaItem> allMediaItems = fileStore.getAllMediaItems(rootTestFolder, mediaInfoSource);
        Set<Set<MediaItem>> expectedOutputSets = fileStore.getAllDuplicates(rootTestFolder, mediaInfoSource);
        
        for(Set<MediaItem> expectedOutput : expectedOutputSets) {
            MediaItem testInputItem = expectedOutput.iterator().next();
            Set<MediaItem> actualOutput = instance.getDuplicatesToThis(allMediaItems, testInputItem);    
            assertEquals(expectedOutput,  actualOutput);
        }
    }
    //@Ignore
    @Test
    public void testAreDuplicates() {
        System.out.println("areDuplicates");
        
        int n = rightAnswersForAreDuplicates.size();
        for(int i = 0;i<n;i++)
        {
            boolean actualReturnValue = instance.areDuplicates(item1list.get(i),
                                                                       item2list.get(i));
            boolean expectedReturnValue = rightAnswersForAreDuplicates.get(i);
            
            String comment = "class is " + instance.getClass() + " and index is " + i;
            assertEquals(comment, expectedReturnValue, actualReturnValue);
        }
    }

    private void initializeTestFolder() {
        
        Path cwdPath = Paths.get("").toAbsolutePath();
        Path testSrcFolder = Paths.get(Workshop5TestHelper.TEST_SRC_FOLDER);
        Path testScratchFolder = 
                Paths.get(Workshop5TestHelper.TEST_SCRATCH_FOLDER);

        File srcFolder = new File(Workshop5TestHelper.TEST_SRC_FOLDER);
        File destFolder = new File(Workshop5TestHelper.TEST_SCRATCH_FOLDER);
        try {
//            Workshop5TestHelper.deleteFolderRecursively(destFolder);    
            Workshop5TestHelper.deleteFolderWithFileVisitor(testScratchFolder.toString());
        } catch (Exception e) {
            // no problem
            e.printStackTrace();
        }
        try {
            Files.createDirectory(testScratchFolder);
//            Workshop5TestHelper.copyFolder(cwdPath, srcFolder, destFolder);
            Path srcPath = Paths.get(cwdPath.toString(), 
                                     Workshop5TestHelper.TEST_SRC_FOLDER,
                                     fileStore.getRootFolder());
            Path dstPath = Paths.get(cwdPath.toString(), 
                                     Workshop5TestHelper.TEST_SCRATCH_FOLDER );
            Path relDstFolder = Paths.get(fileStore.getRootFolder() );
            
            Workshop5TestHelper.copyFolder(srcPath, dstPath, relDstFolder);
        } catch (Exception e) {
            // problem
            e.printStackTrace();
            fail("could not create test folder");
        }
        // remove temporary folder, if it is there
        // copy permanent folder to temp location

    }

}
