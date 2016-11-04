package turntotech.org.customviewandtouch;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyExtendedView extends View {

    static int touchDoneCounter = 2;

    static String DEBUG_TAG = "CUSTOM_VIEW_INFO";


    float xa[] = new float[11];
    float ya[] = new float[11];


    //1.1 The constructor is called first
    public MyExtendedView(Context ctx, AttributeSet attrs) {

        super(ctx, attrs);

        //1.1.1 Set the background color to black
        this.setBackgroundColor(Color.BLACK);
    }

    //1.2 This method is called before the view is drawn first, on screen rotation and when forceredraw is called
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        Log.d(DEBUG_TAG, "Counter: " + touchDoneCounter);

        int [] colorsArray = new int[]{Color.YELLOW, Color.BLUE, Color.RED, Color.GREEN, Color.CYAN, Color.DKGRAY,
                                        Color.GRAY, Color.MAGENTA, Color.WHITE, Color.GREEN, Color.TRANSPARENT};

        for (int a = 0; a < touchDoneCounter; a++) {
            Paint paint = new Paint();
            paint.setColor(colorsArray[a]);
            canvas.drawCircle(xa[a], ya[a], 125f, paint);

        }
    }

    //1.3 This is called when a touch is registered
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int action = event.getAction();

        touchDoneCounter = event.getPointerCount();

        //1.4 Here were are just logging the kind of event we got
        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                Log.d(DEBUG_TAG, "Action was DOWN");
                break;

            case (MotionEvent.ACTION_MOVE):
                Log.d(DEBUG_TAG, "Action was MOVE");
                break;

            case (MotionEvent.ACTION_UP):
                Log.d(DEBUG_TAG, "Action was UP");
                //touchDoneCounter = 0;
                break;

            case (MotionEvent.ACTION_POINTER_UP):
                //infoText.setText("ACTION POINTER UP");
                break;

            case (MotionEvent.ACTION_POINTER_DOWN):
                // infoText.setText("ACTION POINTER DOWN");
                break;

            default:
                return super.onTouchEvent(event);
        }
        //1.5 at this point we re-draw the circle where the touch occurred
        redrawViewWithCircle(event);

        return true;
    }



    //1.6
    public void redrawViewWithCircle(MotionEvent event) {

//        //1.6.1 Get index and pointer count(pc)
        int index = event.getActionIndex();
//        int action = event.getAction();
//        //int index2 = event.getActionIndex();
        int pc = event.getPointerCount();

//        int pointerIndex;
//        int pointerID;

        //1.6.2 Get coordinates for circle center. Set the instance variables.
       // this.x = event.getX(index);
       // this.y = event.getY(index);

        for(int a = 0; a < pc ; a++) {

            xa[a] = event.getX(a);
            ya[a] = event.getY(a);


            int pointerID = event.getPointerId(a);
            int x = (int) event.getX(a);
            int y = (int) event.getY(a);
            int id = event.getPointerId(a);
            int action = event.getActionMasked();
            int actionIndex = event.getActionIndex();
            String actionString;

//            if (pc == 2) {
//                this.x1 = event.getX(1);
//                this.y1 = event.getY(1);
//                this.x = event.getX(0);
//                this.y = event.getY(0);
//            }
//            else if (pc == 1) {
//                this.x = event.getX(0);
//                this.y = event.getY(0);
//            }
            this.postInvalidate();

        }




        //1.6.3 Force the view to redraw. Comment this and see what happens.
        //this.postInvalidate();

    }


}