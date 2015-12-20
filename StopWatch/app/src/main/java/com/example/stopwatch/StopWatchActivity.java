package com.example.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 *
 * Sample Stopwatch Android activity
 *
 */
public class StopWatchActivity extends Activity {

    /**
     * REFRESH_RATE defines how often we should update the timer to show how much time has elapsed.
     * refresh every 100 milliseconds
     */
    private final int REFRESH_RATE = 100;

    /**
     * A variable to keep track of the seconds
     */
    
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch);
    }



    // TODO: Create a Stopwatch!
    /**
     * This method will start the current stopwatch clock
     *
     * @param view the current view
     */

    public void startClick(View view){

        //TODO
    }
    /**
     * This method will reset the current stopwatch clock
     *
     * @param view the current view
     */
    public void resetClick(View view){

        //TODO

    }

    /**
     * This method will stop the current stopwatch.
     *
     * @param view the current view
     */
    public void stopClick(View view){

        //TODO

    }

    /**
     * This method will show the stop button when called by hiding the 
     * start and reset button and making the stop button visible.
     */
    private void showStopButton(){

        //TODO

    }

    /**
     * This method will show the start and reset buttons by hiding the 
     * stop button and making the start and reset buttons visible.
     */
    private void hideStopButton(){

        //TODO

    }

    /**
     * Converts the elapsed given time and updates the display
     *
     * @param time the time to update the current display to
     */
    private void updateTimer (long time){


        //Convert the milliseconds,seconds,minutes,hours to String and format to ensure it has a leading zero when required
        //TODO



        //Setting the timer text to the elapsed time
        //TODO

    }

    /**
     * Create a Runnable startTimer that makes timer runnable.
     */
    private Runnable startTimer = new Runnable() {
        public void run() {

            //TODO

        }
    };

}
    