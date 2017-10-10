package br.com.apesoftware.batalhanaval.game;

import br.com.apesoftware.batalhanaval.command.ModCommand;
import br.com.apesoftware.batalhanaval.command.ModFireCommand;
import br.com.apesoftware.batalhanaval.command.ModHiddenNavyCommand;

/**
 * Created by gabriel.rodrigues on 06/10/17.
 */

public class ModFactory {


    public static ModCommand create(Tabuleiro tabuleiro) {

        if(tabuleiro.isModeIsFire()) {
            return new ModFireCommand(tabuleiro);
        }

        return new ModHiddenNavyCommand(tabuleiro);
    }
}
