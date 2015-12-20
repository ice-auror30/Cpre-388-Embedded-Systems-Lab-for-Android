package edu.iastate.gestures;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * This is a base class for handling swipes in the application.
 */
public class CustomGestureListener extends Activity implements OnGestureListener{
	/*
	 * These variables store activity specific values.
	 */
	private static final String DEBUG_TAG = "Gestures";
	private GestureDetector gesture = null;
	private Class<?> leftActivity = null;
	private Class<?> rightActivity = null;
	
    @Override
   public boolean onTouchEvent(MotionEvent me)
   {
    	if (gesture != null)
    		return gesture.onTouchEvent(me);
    	else
    		return false;
   }

	@Override
	public boolean onDown(MotionEvent e) {
		//insert code here
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		//insert code here
		Log.d(DEBUG_TAG, "onFling: " + e1.toString() + e2.toString());

		if(e1.getX()<e2.getX()){
			Intent intent = new Intent(this, leftActivity);
			startActivity(intent);
			Toast toast = new Toast(getApplicationContext());
			toast.setGravity(Gravity.BOTTOM,0,0);
			toast.makeText(this, "Right Swipe", Toast.LENGTH_SHORT).show();
			//setContentView(R.layout.activity_third);
		}
		else if(e1.getX()>e2.getX()){
			Intent intent = new Intent(this, rightActivity);
			startActivity(intent);
			Toast toast = new Toast(getApplicationContext());
			toast.setGravity(Gravity.BOTTOM,0,0);
			toast.makeText(this, "Left Swipe", Toast.LENGTH_SHORT).show();
		}
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Sets the gesture detector for the activity
	 * @param gesture the gesture detector specific to the activity
	 */
	public void setGestureDetector(GestureDetector gesture){
		this.gesture = gesture;
	}
	
	/**
	 * Sets the left and right activity classes which are swiped to
	 * @param leftActivity	The class for the left Activity
	 * @param rightActivity The class for the right Activity
	 */
	public void setLeftRight(Class<?> leftActivity, Class<?> rightActivity){
		this.leftActivity = leftActivity;
		this.rightActivity = rightActivity;
	}

}
