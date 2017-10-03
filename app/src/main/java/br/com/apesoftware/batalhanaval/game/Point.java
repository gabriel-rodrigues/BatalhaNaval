package br.com.apesoftware.batalhanaval.game;

/**
 * Created by gabriel.rodrigues on 03/10/17.
 */

public class Point {

    private int line;
    private int column;
    private Navy navy;

    public Point(int line, int column) {
        this.line = line;
        this.column = column;
    }


    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Navy getNavy() {
        return navy;
    }

    public void setNavy(Navy navy) {
        this.navy = navy;
    }

    @Override
    public boolean equals(Object obj) {

        Point pointCompare = (Point)obj;

        return this.getLine() == pointCompare.getLine() &&
               this.getColumn() == pointCompare.getColumn();
    }
}
