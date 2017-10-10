package br.com.apesoftware.batalhanaval.game;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gabriel.rodrigues on 03/10/17.
 */

public class Tabuleiro {

    private List<Point> mNavisPoints = new ArrayList<>();
    private List<Point> mFirePoints  = new ArrayList<>();

    private boolean mModeIsFire;
    private boolean mNecessaryToRedesign;

    private final int COUNT_MAX_POINTS_WITH_NAVY = 4;

    public Tabuleiro() {
        this.setModeIsFire(false);
    }

    public boolean existe(Point point) {

        for (Point p : this.mNavisPoints) {
            if(p.equals(point)) {
                return true;
            }
        }

        return false;
    }

    public void add(Point point) {
        this.mNavisPoints.add(point);
    }

    public void addFire(Point fire) {
        this.mFirePoints.add(fire);
    }

    public Point getFire(Point fire) {

        for(Point f : this.mFirePoints) {
            if(f.equals(fire)) {
                return fire;
            }
        }

        return  null;
    }

    @Nullable
    public Navy getNavyByPoint(Point point) {

        if(!this.existe(point)) {
            return null;
        }

        for(Point p : this.mNavisPoints) {
            if(p.equals(point)) {
                return p.getNavy();
            }
        }

        return null;
    }

    public boolean maxOfPoints() {

        int countPointWithNavys = 0;

        for (Point point: this.mNavisPoints) {
             if(point.getNavy() != null) {
                 ++countPointWithNavys;
             }
        }


        return countPointWithNavys == COUNT_MAX_POINTS_WITH_NAVY;
    }


    public void setModeIsFire(boolean isModeFire) {
        this.mModeIsFire = isModeFire;
    }

    public boolean isModeIsFire() {
        return this.mModeIsFire;
    }

    public boolean isNecessaryToRedesign() {
        return this.mNecessaryToRedesign;
    }

    public void setNecessaryToRedesign(boolean isNecessary) {
        this.mNecessaryToRedesign = isNecessary;
    }
}
