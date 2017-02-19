package org.or5e.mp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.or5e.core.PluginException;
import org.or5e.core.plugin.PluginLifecycleAdaptor;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.Equalizer;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

public class MediaPlayerTestSPI extends PluginLifecycleAdaptor{
	static {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), getProperties("vlcNative"));
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        LibXUtil.initialise();
    }
	@Override public String getPluginID() {
		return getName();
	}

	@Override public void initilize() throws PluginException {
	}

	
	@Override public void doProcess() {
		super.doProcess();
	}

	@Override public String getName() {
		return "MediaPlayerSPI";
	}
	public static void main(String[] args) {
		AudioMediaPlayerComponent audioPlayer = new AudioMediaPlayerComponent() {
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                System.exit(0);
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                System.out.println("Failed to play media");
                System.exit(1);
            }
        };
        MediaPlayerFactory mediaPlayerFactory = audioPlayer.getMediaPlayerFactory();
        MediaPlayer mediaPlayer = audioPlayer.getMediaPlayer();
        Map<String, Equalizer> equalizersAvailable = mediaPlayerFactory.getAllPresetEqualizers();
        Set<String> eqName = equalizersAvailable.keySet();
        for (String eq : eqName) {
        	System.out.println("Available Eq Preset: "+eq);
			if(eq.equals("Rock")) {
				System.out.println("Setting preset: "+eq);
				mediaPlayer.setEqualizer(equalizersAvailable.get(eq));
			}
		}
        mediaPlayer.setVolume(50);
        mediaPlayer.playMedia("D:\\Shared\\ITunes\\Songs\\Vedhalam\\Veera Vinayaka.mp3");
        try {
        	System.in.read();
        	mediaPlayer.setEqualizer(equalizersAvailable.get("Party"));
        	System.out.println("Party");
        	System.in.read();
        	mediaPlayer.setEqualizer(equalizersAvailable.get("Pop"));
        	System.out.println("Pop");
        	System.in.read();
        	mediaPlayer.setEqualizer(equalizersAvailable.get("Soft rock"));
        	System.out.println("srock");
        	System.in.read();
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayerFactory.release();
			System.out.println("Exiting main...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main0(String[] args) {
		org.or5e.mp.MediaPlayer _player = org.or5e.mp.MediaPlayerFactory.getMediaPlayerFactory().getMediaPlayer(MediaPlayerType.XTREMEMP);
		System.out.println(_player);
		try {
			_player.play(new URL("http://74.50.122.103:7674/;?icy=http").toURI());
		} catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
