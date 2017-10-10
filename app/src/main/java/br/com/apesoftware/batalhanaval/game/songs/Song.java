package br.com.apesoftware.batalhanaval.game.songs;

import android.content.Context;
import android.media.SoundPool;

import br.com.apesoftware.batalhanaval.factory.SongFactory;

/**
 * Created by gabriel.rodrigues on 07/10/17.
 */

public abstract class Song  {

    protected SoundPool soundPool;
    protected int soundId;
    protected Context contexto;

    private int uniqueStreamId;


    public Song(Context contexto) {
        this.contexto  = contexto;
        this.soundPool = SongFactory.create();

        this.settingSong();
    }

    public void play() {
    }

    public void stop() {

        if(uniqueStreamId != 0) {
            this.soundPool.stop(uniqueStreamId);
        }
    }

    protected abstract void settingSong();
}
