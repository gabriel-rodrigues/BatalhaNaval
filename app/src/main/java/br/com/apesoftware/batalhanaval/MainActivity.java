package br.com.apesoftware.batalhanaval;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.apesoftware.batalhanaval.game.Navy;
import br.com.apesoftware.batalhanaval.views.BatalhaNavalView;

public class MainActivity extends Activity implements BatalhaNavalView.BatalhaNavalViewListener {

    private BatalhaNavalView mBatalhaNavalView;
    private List<Navy> mNavys;
    private int mFirstPositionNavy = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mBatalhaNavalView = this.findViewById(R.id.batalhaNavalView);

        this.mNavys = new ArrayList<>();
        this.mNavys.add((Navy)this.findViewById(R.id.encouracado));
        this.mNavys.add((Navy)this.findViewById(R.id.corveta));
        this.mNavys.add((Navy)this.findViewById(R.id.destroier));
        this.mNavys.add((Navy)this.findViewById(R.id.submarino));


        this.mBatalhaNavalView.setListener(this);
        this.mBatalhaNavalView.setCurrencyNavyInGame(this.mNavys.get(mFirstPositionNavy));
    }

    @Override
    public void changeCurrencyNavyInGame() {

        if(this.mFirstPositionNavy < this.mNavys.size() - 1) {
            ++this.mFirstPositionNavy;
            this.mBatalhaNavalView.setCurrencyNavyInGame(this.mNavys.get(mFirstPositionNavy));
        }
    }

    @Override
    public void showFeedbackPlayer() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Agora vamos tentar descobrir onde estao os navios!")
                .setPositiveButton("Pronto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mBatalhaNavalView.invalidate();
                    }
                });


        builder.create().show();
    }

}
