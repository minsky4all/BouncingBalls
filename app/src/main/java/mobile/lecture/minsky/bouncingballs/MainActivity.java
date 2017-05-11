package mobile.lecture.minsky.bouncingballs;

        import android.animation.FloatArrayEvaluator;
        import android.animation.ObjectAnimator;
        import android.animation.ValueAnimator;
        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.support.v7.app.ActionBarActivity;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.animation.AccelerateInterpolator;

        import static android.R.attr.animation;

class MyView extends View {

    float mX = 30;

    public MyView(Context context){
        super(context);
        setBackgroundColor(Color.YELLOW);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() != MotionEvent.ACTION_DOWN)
            return false;

        ValueAnimator moveAnim = ValueAnimator.ofFloat(100, 800);
        moveAnim.setDuration(2000);
        moveAnim.setInterpolator(new AccelerateInterpolator());
        moveAnim.addUpdateListener(new ObjectAnimator.AnimatorUpdateListener(){

            public void onAnimationUpdate(ValueAnimator animation){
                mX = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
        moveAnim.start();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(mX, 500, 100, paint);
    }
}

public class MainActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        MyView w = new MyView(this);
        setContentView(w);
    }
}