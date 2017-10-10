package br.com.apesoftware.batalhanaval.command;

import android.support.annotation.NonNull;

import br.com.apesoftware.batalhanaval.game.Point;
import br.com.apesoftware.batalhanaval.game.Tabuleiro;
import br.com.apesoftware.batalhanaval.views.BatalhaNavalView;

/**
 * Created by gabriel.rodrigues on 06/10/17.
 */

public abstract class ModCommand {

    protected final Tabuleiro mTabuleiro;
    private BatalhaNavalView.BatalhaNavalViewListener mListener;



    public ModCommand(Tabuleiro tabuleiro) {

        this.mTabuleiro = tabuleiro;
        this.mTabuleiro.setNecessaryToRedesign(false);

    }


    @NonNull
    public BatalhaNavalView.BatalhaNavalViewListener getListener() {
        return mListener;
    }



    public void setListener(@NonNull BatalhaNavalView.BatalhaNavalViewListener listener) {

        if(listener == null)
            throw new IllegalArgumentException("listener nao pode ser nulo");

        this.mListener = listener;
    }

    public abstract void handlerWith(Point point);
}
