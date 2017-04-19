import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

public class Key extends Actor
{
    private boolean isDown;
    private String key;
    private String sound;
    private String notPressed;
    private String pressed;
    
    public Key (String keyName, String soundFile, String keyNotPressed, String keyPressed)
    {
        key = keyName;
        sound = soundFile;
        notPressed = keyNotPressed;
        pressed = keyPressed;
        
        setImage(notPressed + ".png");
    }
    
    /**
     * -act runs every time act is pressed, or every frame while run is active
     * act checks if a key is pressed, and updates the key's image and plays a 
     * specific sound
     */
    public void act()
    {
        if(isDown == false && Greenfoot.isKeyDown(key) == true)
        {
            setImage(pressed + ".png");
            isDown = true;
            
            play();
        }
        
        if(isDown == true && Greenfoot.isKeyDown(key) != true)
        {
            setImage(notPressed + ".png");
            isDown = false;
        }
    }
    
    /**
     * play causes a specific sound to play in response to which key is pressed
     * 
     * @param there are no parameters
     * @return nothing is returned
     */
    private void play()
    {
        Greenfoot.playSound(sound + ".wav");
    }
}

