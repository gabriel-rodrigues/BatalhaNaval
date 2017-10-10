package br.com.apesoftware.batalhanaval.command;

import br.com.apesoftware.batalhanaval.game.Point;
import br.com.apesoftware.batalhanaval.game.Tabuleiro;

/**
 * Created by gabriel.rodrigues on 06/10/17.
 */

public class ModFireCommand extends ModCommand {


    public ModFireCommand(Tabuleiro tabuleiro) {
        super(tabuleiro);
    }

    @Override
    public void handlerWith(Point point) {

        this.mTabuleiro.addFire(point);
        this.mTabuleiro.setNecessaryToRedesign(true);

    }
}
