package br.com.apesoftware.batalhanaval.command;

import br.com.apesoftware.batalhanaval.game.Navy;
import br.com.apesoftware.batalhanaval.game.Point;
import br.com.apesoftware.batalhanaval.game.Tabuleiro;

/**
 * Created by gabriel.rodrigues on 06/10/17.
 */

public class ModHiddenNavyCommand extends ModCommand {

    private final int FIRST_POINT = 0;

    public ModHiddenNavyCommand(Tabuleiro tabuleiro) {
        super(tabuleiro);
    }

    @Override
    public void handlerWith(Point point) {

       this.handlerPointNotExistentInTabuleiro(point);
       this.notifyMaxOfPointInTabuleiroIfNecessary();
    }

    private void handlerPointNotExistentInTabuleiro(Point point) {

        if (!this.mTabuleiro.existe(point)) {
            this.addAllPointsInTabuleiroForSizeNavyFrom(point);
            this.getListener().changeCurrencyNavyInGame();
            this.mTabuleiro.setNecessaryToRedesign(true);
        }
    }


    private void notifyMaxOfPointInTabuleiroIfNecessary() {

        if (this.mTabuleiro.maxOfPoints()) {
            this.getListener().showFeedbackPlayer();
            this.mTabuleiro.setModeIsFire(true);
        }
    }

    private void addAllPointsInTabuleiroForSizeNavyFrom(Point firstPoint) {

        Navy navy  = firstPoint.getNavy();
        if(navy != null) {
            int count = 0;

            do {

                if(count == FIRST_POINT) {
                    this.mTabuleiro.add(firstPoint);
                }
                else {
                    Point newPoint = new Point(firstPoint.getLine(), firstPoint.getColumn() + count);
                    this.mTabuleiro.add(newPoint);
                }

                ++count;

            } while (count < navy.getSize());
        }

    }
}
