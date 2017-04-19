import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A piano that can be played with the computer keyboard.
 * 
 * Author: Gade Ricard
 * Teacher: Mr. Hardman
 * Assignment: #4 Parallel Arrays
 * Date Last Modified: April 19
 */
public class Piano extends World
{
    private String[] whiteKeys = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]"};
    private String[] whiteNotes = {"3c", "3d", "3e", "3f", "3g", "3a", "3b", "4c", "4d", "4e", "4f", "4g"};
    
    private String[] blackKeys = {"2", "3", "", "5", "6", "7", "", "9", "0", "", "="};
    private String[] blackNotes = {"3c#", "3d#", "", "3f#", "3g#", "3a#", "", "4c#", "4d#", "", "4f#"};
    
    /**
     * Make the piano.
     */
    public Piano() 
    {
        super(800, 340, 1);
        
        makeKeys();
    }
    
    /**
     * makeKeys creates a new Key object, and updates it based on the data contained in 
     * for loops
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void makeKeys()
    {
        Key currentKey;
        
        for(int i = 0; i < whiteKeys.length; i++)
        {
            currentKey = new Key(whiteKeys[i], whiteNotes[i], "white-key", "white-key-down");
            addObject(currentKey, (i*67)+30, 250);
        }
        
        for(int i = 0; i < blackKeys.length; i++)
        {
            if(blackKeys[i] != "")
            {
                currentKey = new Key(blackKeys[i], blackNotes[i], "black-key", "black-key-down");
                addObject(currentKey, (i*67)+30, 195);
            }
        }
    }
}