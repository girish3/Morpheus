package github.girish3.morpheus;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.util.Property;

/**
 * Created by Girish on 07/01/16.
 */
public class MorpheusDrawable extends Drawable {

    private Paint mPaint;
    private int mBorderRadius;


    public MorpheusDrawable(@ColorInt int color, int borderRadius) {
        mPaint = new Paint();
        mPaint.setColor(color);
        mBorderRadius = borderRadius;
    }

    public static final Property<MorpheusDrawable, Integer> BORDER_RADIUS = new Property<MorpheusDrawable, Integer>(Integer.class, "BORDER_RADIUS") {
        @Override
        public Integer get(MorpheusDrawable drawable) {
            return drawable.getBorderRadius();
        }

        @Override
        public void set(MorpheusDrawable drawable, Integer radius) {
            drawable.setBorderRadius(radius);
        }
    };

    public static final Property<MorpheusDrawable, Integer> COLOR = new Property<MorpheusDrawable, Integer>(Integer.class, "COLOR") {
        @Override
        public Integer get(MorpheusDrawable drawable) {
            return drawable.getColor();
        }

        @Override
        public void set(MorpheusDrawable drawable, Integer color) {
            drawable.setColor(color);
        }
    };

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(new RectF(getBounds()), mBorderRadius, mBorderRadius, mPaint);
    }

    public int getColor() {
        return mPaint.getColor();
    }

    public void setColor(int color) {
        mPaint.setColor(color);
        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    public int getBorderRadius() {
        return mBorderRadius;
    }

    public void setBorderRadius(int mBorderRadius) {
        this.mBorderRadius = mBorderRadius;
        invalidateSelf();
    }
}
