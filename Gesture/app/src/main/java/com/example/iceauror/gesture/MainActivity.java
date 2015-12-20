package com.example.iceauror.gesture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
float x1, y1, x2, y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean onTouchEvent(MotionEvent event){
        TextView textview = new TextView(this);
        if((event.getAction()==MotionEvent.ACTION_DOWN))
        {
                x1 = event.getX();
                y1 = event.getY();
                String s = "" + x1 + "," + y1;
                textview.setText(s);
                setContentView(textview);
            }
         if(event.getAction()==MotionEvent.ACTION_MOVE)
        {
            x2= event.getX();
            y2= event.getY();
            String s= ""+x2+","+y2;
            textview.setText(s);
            setContentView(textview);
        }

        return true;
    }
}
