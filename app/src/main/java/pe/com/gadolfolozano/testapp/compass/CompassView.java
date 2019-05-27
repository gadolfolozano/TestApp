package pe.com.gadolfolozano.testapp.compass;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import pe.com.gadolfolozano.testapp.R;

public class CompassView extends View {

    private int borderColor;
    private Paint paint;
    private int widthSize;
    private int heightSize;
    private int textColor;
    private float textSize;
    private float borderSize;

    public CompassView(Context context) {
        this(context, null);
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CompassView);

        borderColor = typedArray.getColor(R.styleable.CompassView_borderColor,
                ContextCompat.getColor(context, R.color.black));
        borderSize = typedArray.getDimension(R.styleable.CompassView_borderSize,
                getContext().getResources().getDimension(R.dimen.border_size));
        textColor = typedArray.getColor(R.styleable.CompassView_textColor,
                ContextCompat.getColor(context, R.color.black));
        textSize = typedArray.getDimension(R.styleable.CompassView_textSize,
                getContext().getResources().getDimension(R.dimen.text_size));
        typedArray.recycle();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        widthSize = getWidth();
        heightSize = getHeight();
        drawBackground(canvas);
        drawLetters(canvas);
        drawNeedle(canvas);
    }

    private void drawBackground(Canvas canvas) {
        paint.reset();
        //circle color
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderSize);
        paint.setAntiAlias(true);

        canvas.drawCircle(widthSize / 2f, heightSize / 2f, (int) (Math.abs(widthSize / 2) * 0.95), paint);
    }

    private void drawLetters(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);

        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1);
        paint.setColor(textColor);
        paint.setTextSize(textSize);

        float textWidth = paint.measureText("X");

        canvas.drawText("N", ((widthSize - textWidth) / 2), (int) (textWidth * 2), paint);
        canvas.drawText("S", ((widthSize - textWidth) / 2), (int) (heightSize - textWidth / 1.3), paint);
        canvas.drawText("W", (int) (textWidth / 1.4), (int) ((heightSize + textWidth) / 2), paint);
        canvas.drawText("E", (int) (widthSize - textWidth * 1.6), (int) ((heightSize + textWidth) / 2), paint);
    }

    private void drawNeedle(Canvas canvas) {
        Paint paintRed = new Paint();
        paintRed.setColor(Color.RED);
        paintRed.setStyle(Paint.Style.FILL_AND_STROKE);
        paintRed.setAntiAlias(true);
        paintRed.setStrokeWidth(5.0f);

        int size = 14;
        int padding = 110;

        Point firstPoint = new Point(widthSize / 2, padding);
        Point secoondPoint = new Point((widthSize / 2) - size, heightSize / 2);
        Point thirdPoint = new Point((widthSize / 2) + size, heightSize / 2);

        Path path = new Path();
        path.moveTo(firstPoint.x, firstPoint.y);
        path.lineTo(secoondPoint.x, secoondPoint.y);
        path.lineTo(thirdPoint.x, thirdPoint.y);
        path.lineTo(firstPoint.x, firstPoint.y);
        path.close();

        canvas.drawPath(path, paintRed);

        Paint paintBlack = new Paint();
        paintBlack.setColor(Color.BLACK);
        paintBlack.setStyle(Paint.Style.FILL_AND_STROKE);
        paintBlack.setAntiAlias(true);
        paintBlack.setStrokeWidth(5.0f);

        Point firstPointBlack = new Point(widthSize / 2, heightSize - padding);
        Point secoondPointBlack = new Point((widthSize / 2) - size, heightSize / 2);
        Point thirdPointBlack = new Point((widthSize / 2) + size, heightSize / 2);

        Path pathBlack = new Path();
        pathBlack.moveTo(firstPointBlack.x, firstPointBlack.y);
        pathBlack.lineTo(secoondPointBlack.x, secoondPointBlack.y);
        pathBlack.lineTo(thirdPointBlack.x, thirdPointBlack.y);
        pathBlack.lineTo(firstPointBlack.x, firstPointBlack.y);
        pathBlack.close();
        canvas.drawPath(pathBlack, paintBlack);

        canvas.drawCircle(widthSize / 2, heightSize / 2, 20, paint);
    }

    public int spToPx(float sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
