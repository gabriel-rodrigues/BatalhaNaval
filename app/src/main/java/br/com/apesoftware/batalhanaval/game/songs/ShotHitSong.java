package br.com.apesoftware.batalhanaval.game.songs;

import android.content.Context;

import br.com.apesoftware.batalhanaval.R;

/**
 * Created by gabriel.rodrigues on 07/10/17.
 */

public class ShotHitSong extends Song {


    public ShotHitSong(Context contexto) {
        super(contexto);
    }

    @Override
    protected void settingSong() {

        this.soundId = this.soundPool.load(contexto, R.raw.explosion, 1);
    }
}
