package br.com.apesoftware.batalhanaval.factory;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by gabriel.rodrigues on 07/10/17.
 */

public class SongFactory {

    @SuppressWarnings("deprecation")
    public static SoundPool create() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new SoundPool.Builder()
                    .setMaxStreams(3)
                    .build();
        }

        return new SoundPool(3, AudioManager.STREAM_MUSIC,0);

    }

}
