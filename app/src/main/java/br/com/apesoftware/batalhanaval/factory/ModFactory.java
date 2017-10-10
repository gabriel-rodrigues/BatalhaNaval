package br.com.apesoftware.batalhanaval.factory;

import br.com.apesoftware.batalhanaval.command.ModCommand;
import br.com.apesoftware.batalhanaval.command.ModShotCommand;
import br.com.apesoftware.batalhanaval.command.ModHiddenNavyCommand;
import br.com.apesoftware.batalhanaval.game.Tabuleiro;

/**
 * Created by gabriel.rodrigues on 06/10/17.
 */

public class ModFactory {


    public static ModCommand create(Tabuleiro tabuleiro) {

        if(tabuleiro.isModeIsFire()) {
            return new ModShotCommand(tabuleiro);
        }

        return new ModHiddenNavyCommand(tabuleiro);
    }
}
