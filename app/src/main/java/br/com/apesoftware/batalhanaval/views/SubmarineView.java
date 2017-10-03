package br.com.apesoftware.batalhanaval.views;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import br.com.apesoftware.batalhanaval.game.Navy;

/**
 * Created by gabriel.rodrigues on 03/10/17.
 */

public class SubmarineView extends AppCompatImageView implements Navy {


    public SubmarineView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setContentDescription("Um Submarino");
    }

    @Override
    public int getSize() {
        return 4;
    }
}
