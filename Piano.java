import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A piano that can be played with the computer keyboard. Also checks for
 * various musical Chord structures
 * 
 * Author: Gade Ricard
 * Teacher: Mr. Hardman
 * Assignment: #5 Chord Recognizing Piano
 * Date Last Modified: May 5
 */
public class Piano extends World
{
    private String[] whiteKeys = {"a", "s", "d", "f", "g", "h", "j", "k", "l", ";", "'", "z", "x", "c", "v", "b", "n", "m", ","};
    private String[] whiteNotes = {"2c", "2d", "2e", "2f", "2g", "2a", "2b", "3c", "3d", "3e", "3f", "3g", "3a", "3b", "4c", "4d", "4e", "4f", "4g"};
    
    private String[] blackKeys = {"2", "3", "", "5", "6", "7", "", "9", "0", "", "=", "q", "w", "", "r", "t", "", "u"};
    private String[] blackNotes = {"2c#", "2d#", "", "2f#", "2g#", "2a#", "", "3c#", "3d#", "", "3f#", "3g#", "3a#", "", "4c#", "4d#", "", "4f#"};
    
    private Key[] whiteKeyObjects = new Key[19];
    private Key[] blackKeyObjects = new Key[18];
    private Key[] allKeyObjects = new Key[37];
    
    /**
     * Make the piano.
     */
    public Piano() 
    {
        super(1600, 340, 1);
        
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
            
            showText(whiteNotes[i], (i*67)+30, 300);
            whiteKeyObjects[i] = currentKey;
        }
        
        for(int i = 0; i < blackKeys.length; i++)
        {
            if(blackKeys[i] != "")
            {
                currentKey = new Key(blackKeys[i], blackNotes[i], "black-key", "black-key-down");
                addObject(currentKey, (i*67)+65, 195);
                
                blackKeyObjects[i] = currentKey;
            }
            else
            {
                blackKeyObjects[i] = null;
            }
            showText(blackNotes[i], (i*67)+65, 250);
        }
        
        makeAllKeysArray();
    }
    
    /**
     * makeAllKeysArray condenses whiteKeyObjects and blackKeyObjects together into 
     * a larger array called allKeyObjects
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void makeAllKeysArray()
    {
        for(int i = 0; i < allKeyObjects.length; i++)
        {
            if(i % 2 == 0)
            {
                allKeyObjects[i] = whiteKeyObjects[i/2];
            }
            else if (i % 2 != 0)
            {
                allKeyObjects[i] = blackKeyObjects[i/2];
            }
        }
        
        allKeyObjects[allKeyObjects.length - 1] = whiteKeyObjects[whiteKeyObjects.length - 1];
    }
    
    public void act()
    {
        int numAllDown = 0;
        int numNulls = 0;
        
        int[] keyDownLocations = new int[32];
        
        for(int i = 0; i < allKeyObjects.length; i++)
        {
            if(allKeyObjects[i] == null)
            {
                numNulls++;
            }
            else if(allKeyObjects[i].checkDown() == true)
            {
                keyDownLocations[numAllDown] = i - numNulls;
                numAllDown++;
            }
        }
        
        if(numAllDown == 2)
        {
            checkForSeconds(keyDownLocations);
        }
        else if(numAllDown == 3)
        {
            checkForTriads(keyDownLocations);
        }
        else if(numAllDown == 4)
        {
            checkForSevenths(keyDownLocations);
        }
        else
        {
            showText("", getWidth()/2, 30);
            showText("", getWidth()/2, 50);
            showText("", getWidth()/2, 70);
            showText("", getWidth()/2, 90);
        }
    }
    
    /**
     * checkForSeconds checks if the keys currently pressed down form a chord
     * called a 'Second'
     * 
     * @param int downKeys is an array that stores the locations of currently pressed
     * keys
     * @return Nothing is returned
     */
    private void checkForSeconds(int[] downKeys)
    {
        if(downKeys[0] + 1 == downKeys[1] || downKeys[0] + 2 == downKeys[1])
        {
            showText("You have made a Second", getWidth()/2, 30);
        }
    }
    
    /**
     * checkForTriads checks if the keys currently pressed down form a chord
     * called a 'Triad', and 'Inverted Triads'
     * 
     * @param int downKeys is an array that stores the locations of currently pressed
     * keys
     * @return Nothing is returned
     */
    private void checkForTriads(int[] downKeys)
    {
        if(downKeys[0] + 3 == downKeys[1] && downKeys[1] + 4 == downKeys[2] || downKeys[0] + 4 == downKeys[1] && downKeys[1] + 3 == downKeys[2] || downKeys[0] + 3 == downKeys[1] && downKeys[1] + 3 == downKeys[2])
        {
            showText("You have made a Triad", getWidth()/2, 30);
        }
        
        if(downKeys[0] + 4 == downKeys[1] && downKeys[1] + 5 == downKeys[2] || downKeys[0] + 3 == downKeys[1] && downKeys[1] + 5 == downKeys[2] || downKeys[0] + 3 == downKeys[1] && downKeys[1] + 6 == downKeys[2])
        {
            showText("You have made a Triad Inverted 1", getWidth()/2, 50);
        }
        
        if(downKeys[0] + 5 == downKeys[1] && downKeys[1] + 3 == downKeys[2] || downKeys[0] + 5 == downKeys[1] && downKeys[1] + 4 == downKeys[2] || downKeys[0] + 6 == downKeys[1] && downKeys[1] + 3 == downKeys[2])
        {
            showText("You have made a Triad Inverted 2", getWidth()/2, 70);
        }
    }
    
    /**
     * checkForTriads checks if the keys currently pressed down form a chord
     * called a 'Seventh', and 'Inverted Seventh'
     * 
     * @param int downKeys is an array that stores the locations of currently pressed
     * keys
     * @return Nothing is returned
     */
    private void checkForSevenths(int[] downKeys)
    {
        if(downKeys[0] + 3 == downKeys[1] && downKeys[1] + 4 == downKeys[2] && downKeys[2] + 3 == downKeys[3] || downKeys[0] + 4 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 4 == downKeys[3] || downKeys[0] + 3 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 3 == downKeys[3])
        {
            showText("You have made a Seventh", getWidth()/2, 30);
        }
        
        if(downKeys[0] + 4 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 2 == downKeys[3] || downKeys[0] + 3 == downKeys[1] && downKeys[1] + 4 == downKeys[2] && downKeys[2] + 1 == downKeys[3] || downKeys[0] + 3 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 3 == downKeys[3])
        {
            showText("You have made a Seventh Inverted 1", getWidth()/2, 50);
        }
        
        if(downKeys[0] + 3 == downKeys[1] && downKeys[1] + 2 == downKeys[2] && downKeys[2] + 3 == downKeys[3] || downKeys[0] + 4 == downKeys[1] && downKeys[1] + 1 == downKeys[2] && downKeys[2] + 4 == downKeys[3] || downKeys[0] + 3 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 3 == downKeys[3])
        {
            showText("You have made a Seventh Inverted 2", getWidth()/2, 70);
        }
        
        if(downKeys[0] + 2 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 4 == downKeys[3] || downKeys[0] + 1 == downKeys[1] && downKeys[1] + 4 == downKeys[2] && downKeys[2] + 3 == downKeys[3] || downKeys[0] + 3 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 3 == downKeys[3])
        {
            showText("You have made a Seventh Inverted 3", getWidth()/2, 90);
        }
    }
}