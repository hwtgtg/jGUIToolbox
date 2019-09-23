//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
/**
 * Ein Komponente zum Abspielen von <strong>Audiodateien</strong>
 * 
 * @author  Claus Behl, Hans Witt,
 *
 * Version 1 (12.12.08)
 * 
 */
public class Audio{
    AudioClip clip; 
 
    public Audio(String filename)
    {
        try {
        	File file = new File(filename);
            //Einen Audio-Clip erzeugen
            clip = Applet.newAudioClip(file.toURI().toURL());
            //Audio-Clip abspielen
        } catch(Exception e) {
            // e.printStackTrace(System.out);
        }
    }
    
     public void play()
      {
    	 if (clip != null ) clip.play();
      }
      
      public void playEndlos()
      {
    	  if (clip != null ) clip.loop();
      }

      public void stop()
      {
    	  if (clip != null ) clip.stop();
      }
      

}
