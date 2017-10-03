package br.com.apesoftware.batalhanaval.views;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import br.com.apesoftware.batalhanaval.game.Navy;

/**
 * Created by gabriel.rodrigues on 02/10/17.
 */

public class CorvetaView extends AppCompatImageView implements Navy {

    public CorvetaView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setContentDescription("Um corveta");
    }

    @Override
    public int getSize() {
        return 1;
    }
}
