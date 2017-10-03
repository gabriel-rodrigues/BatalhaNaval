package br.com.apesoftware.batalhanaval.views;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import br.com.apesoftware.batalhanaval.game.Navy;

/**
 * Created by gabriel.rodrigues on 02/10/17.
 */

public class CouracadoView extends AppCompatImageView implements Navy {

    public CouracadoView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setContentDescription("Couraçado");
    }

    @Override
    public int getSize() {
        return 5;
    }
}
