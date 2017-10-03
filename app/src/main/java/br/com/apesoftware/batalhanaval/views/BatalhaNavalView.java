package br.com.apesoftware.batalhanaval.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import br.com.apesoftware.batalhanaval.R;
import br.com.apesoftware.batalhanaval.game.Point;
import br.com.apesoftware.batalhanaval.game.Navy;
import br.com.apesoftware.batalhanaval.game.Tabuleiro;

/**
 * Created by gabriel.rodrigues on 02/10/17.
 */

public class BatalhaNavalView extends View {


    private int mTamanho;
    private Tabuleiro mTabuleiro;
    private Paint mPaint;
    private GestureDetector mGestureDetector;
    private Navy mCurrencyNavyInGame;
    private BatalhaNavalViewListener mListener;
    private Bitmap mImageFire;
    private Bitmap mImageWater;

    private final int VAZIO   = 0;
    private final int COLUMNS = 10;


    public BatalhaNavalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.mTabuleiro = new Tabuleiro();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.mGestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mGestureDetector = new GestureDetector(this.getContext(), new BatalhaNavalGestureDetector());
        this.mImageWater      = BitmapFactory.decodeResource(this.getResources(), R.drawable.agua);
        this.mImageFire       = BitmapFactory.decodeResource(this.getResources(), R.drawable.fogo);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        this.mImageWater = null;
        this.mImageFire = null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(this.getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            Resources resources = this.getResources();

            float quadranteMinimo = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, resources.getDisplayMetrics());

            this.mTamanho  = (int) quadranteMinimo * COLUMNS;
        }
        else if (this.getLayoutParams().width == ViewGroup.LayoutParams.MATCH_PARENT) {
            mTamanho = Math.min(MeasureSpec.getMode(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        }
        else {
            mTamanho = this.getLayoutParams().width;
        }

        this.setMeasuredDimension(this.mTamanho, this.mTamanho);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        this.mPaint.setColor(Color.BLACK);
        this.mPaint.setStrokeWidth(3);

        this.drawLinesVerticalIn(canvas);
        this.drawLinesHorizontalIn(canvas);

        if(!mTabuleiro.isModeIsFire()) {
            this.drawNavyIfNeededIn(canvas);
        }
        else {
            this.drawFireOrWaterIn(canvas);
        }

    }


    public Navy getCurrencyNavyInGame() {
        return mCurrencyNavyInGame;
    }

    public void setCurrencyNavyInGame(Navy currencyNavyInGame) {
        this.mCurrencyNavyInGame = currencyNavyInGame;
    }

    public void setListener(BatalhaNavalViewListener listener) {

        this.mListener = listener;
    }

    private void drawFireOrWaterIn(Canvas canvas) {

        for(int line = 0; line < COLUMNS; line++) {
            for(int column = 0; column < COLUMNS; column++) {

                Point point = new Point(line, column);

                int x = point.getColumn() * getQuadrante();
                int y = point.getLine()   * getQuadrante();

                Rect rect       = new Rect(x, y, x + getQuadrante(), y + getQuadrante());
                Point firePoint = this.mTabuleiro.getFire(point);

                if(firePoint != null) {
                    if(this.mTabuleiro.existe(firePoint)) {
                        canvas.drawBitmap(this.mImageFire, null, rect, null);
                    }
                    else {
                        canvas.drawBitmap(this.mImageWater, null, rect, null);
                    }
                }
            }
        }

    }

    private void drawNavyIfNeededIn(Canvas canvas) {

        for(int line = 0; line < COLUMNS; line++) {
            for(int column = 0; column < COLUMNS; column++) {

                Point point = new Point(line, column);

                int x = point.getColumn() * getQuadrante();
                int y = point.getLine()   * getQuadrante();


                if(this.mTabuleiro.existe(point)) {
                    Navy navy                    = this.mTabuleiro.getNavyByPoint(point);
                    Rect retangulo               = new Rect(x, y, x + getQuadrante() * navy.getSize(), y + getQuadrante());
                    AppCompatImageView imageView = (AppCompatImageView) navy;
                    imageView.buildDrawingCache();

                    canvas.drawBitmap(imageView.getDrawingCache(), null, retangulo, null);
                }
            }
        }

    }

    private void drawLinesVerticalIn(Canvas canvas) {

        for(int vertical = 1; vertical < COLUMNS; vertical++) {
            canvas.drawLine(this.getQuadrante() * vertical, 0, this.getQuadrante() * vertical, mTamanho, mPaint);
        }
    }

    private void drawLinesHorizontalIn(Canvas canvas) {

        for(int horizontal = 1; horizontal < COLUMNS; horizontal++) {
            canvas.drawLine(0, this.getQuadrante() * horizontal, mTamanho, this.getQuadrante() * horizontal, mPaint);
        }
    }

    private int getQuadrante() {

        int quadrante = this.mTamanho / COLUMNS;
        return quadrante;

    }

   private class BatalhaNavalGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {

            if(event.getAction() == MotionEvent.ACTION_UP) {

                int line   = (int)(event.getY() / getQuadrante());
                int column = (int)(event.getX() / getQuadrante());

                Point point = new Point(line, column);


                if(!mTabuleiro.isModeIsFire()) {
                    if (!mTabuleiro.maxOfPoints()) {
                        if (!mTabuleiro.existe(point)) {
                            point.setNavy(getCurrencyNavyInGame());
                            mTabuleiro.add(point);

                            if (mListener != null) {
                                mListener.changeCurrencyNavyInGame();
                            }

                            invalidate();
                        }

                        if (mTabuleiro.maxOfPoints() && mListener != null) {
                            mListener.showFeedbackPlayer();
                            mTabuleiro.setModeIsFire(true);
                        }
                    }
                }
                else {
                    mTabuleiro.addFire(point);
                    invalidate();
                }

                return  true;
            }

            return super.onSingleTapUp(event);
        }
    }


    public interface BatalhaNavalViewListener {

        void changeCurrencyNavyInGame();
        void showFeedbackPlayer();
    }
}
