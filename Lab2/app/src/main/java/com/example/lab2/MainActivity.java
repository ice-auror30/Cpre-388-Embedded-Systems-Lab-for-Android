package com.example.lab2;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author jamiekujawa
 *
 */
public class MainActivity extends Activity {

    /**
     * A string builder to represent the first number entered in the series of entries
     */
    private StringBuilder expression1;

    /**
     * A string builder to represent the second number entered in the series of entries
     */
    private StringBuilder expression2;

    /**
     * A string to represent the last operator performed
     */
    private String oldOperator;

    /**
     * A boolean flag to identify if an operator has been used or not
     *
     */
    private boolean op_pressed = false;

    private String result;

    /**
     * TAG to be used in log
     */
    private static String TAG= "MainActivity";

    /**
     *
     * Double variables to evaluate the answer
     */
    Double d,d1,answer;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare all buttons used within the layout
        Button zero = (Button) findViewById(R.id.button0);
        Button one = (Button) findViewById(R.id.button1);
        Button two = (Button) findViewById(R.id.button2);
        Button three = (Button) findViewById(R.id.button3);
        Button four = (Button) findViewById(R.id.button4);
        Button five = (Button) findViewById(R.id.button5);
        Button six = (Button) findViewById(R.id.button6);
        Button seven = (Button) findViewById(R.id.button7);
        Button eight = (Button) findViewById(R.id.button8);
        Button nine = (Button) findViewById(R.id.button9);
        Button times = (Button) findViewById(R.id.buttontimes);
        Button clear = (Button) findViewById(R.id.buttonClear);
        Button equal = (Button) findViewById(R.id.buttonEqual);
        Button decimal = (Button) findViewById(R.id.buttonDecimal);
        Button divide = (Button) findViewById(R.id.buttondivide);
        Button add = (Button) findViewById(R.id.buttonplus);
        Button subtract = (Button) findViewById(R.id.buttonminus);

        // declare main text view
        final TextView main = (TextView) findViewById(R.id.CalculatorText);

        // Main Strings to represent the expressions
        expression1 = new StringBuilder();
        expression2 = new StringBuilder();

		/*
		 * Set up all key listener events
		 */
        zero.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (oldOperator == null) {
                    expression1.append('0');
                    main.setText(expression1.toString());
                } else {
                    expression2.append('0');
                    main.setText(expression1.toString() + " " + oldOperator + " " + expression2.toString());
                }
            }
        });

        one.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (oldOperator == null) {
                    expression1.append('1');
                    main.setText(expression1.toString());
                } else {
                    expression2.append('1');
                    main.setText(expression1.toString() + " " + oldOperator + " " + expression2.toString());
                }
            }

        });

        two.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (oldOperator == null) {
                    expression1.append('2');
                    main.setText(expression1.toString());
                } else {
                    expression2.append('2');
                    main.setText(expression1.toString() + " " + oldOperator + " " + expression2.toString());
                }
            }
        });

        three.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (oldOperator == null) {
                    expression1.append('3');
                    main.setText(expression1.toString());
                } else {
                    expression2.append('3');
                    main.setText(expression1.toString() + " " + oldOperator + " " + expression2.toString());
                }
            }
        });

        four.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (oldOperator == null) {
                    expression1.append('4');
                    main.setText(expression1.toString());
                } else {
                    expression2.append('4');
                    main.setText(expression1.toString() + " " + oldOperator + " " + expression2.toString());
                }
            }
        });

        five.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (oldOperator == null) {
                    expression1.append('5');
                    main.setText(expression1.toString());
                } else {
                    expression2.append('5');
                    main.setText(expression1.toString() + " " + oldOperator + " " + expression2.toString());
                }
            }
        });

        six.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (oldOperator == null) {
                    expression1.append('6');
                    main.setText(expression1.toString());
                } else {
                    expression2.append('6');
                    main.setText(expression1.toString() + " " + oldOperator + " " + expression2.toString());
                }
            }
        });

        seven.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (oldOperator == null) {
                    expression1.append('7');
                    main.setText(expression1.toString());
                } else {
                    expression2.append('7');
                    main.setText(expression1.toString() + " " + oldOperator + " " + expression2.toString());
                }
            }
        });

        eight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (oldOperator == null) {
                    expression1.append('8');
                    main.setText(expression1.toString());
                } else {
                    expression2.append('8');
                    main.setText(expression1.toString() + " " + oldOperator + " " + expression2.toString());
                }
            }
        });

        nine.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (oldOperator == null) {
                    expression1.append('9');
                    main.setText(expression1.toString());
                } else {
                    expression2.append('9');
                    main.setText(expression1.toString() + " " + oldOperator + " " + expression2.toString());
                }
            }

        });

        times.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(oldOperator!=null){
                    result = evaluate(oldOperator);
                    expression1.setLength(0);
                    expression1.append(result);
                    expression2.setLength(0);
                    // oldOperator=null;
                    main.setText(expression1);

                }
                else{
                    oldOperator="*";
                }
                oldOperator="*";
                /*if(oldOperator==null) {
                    oldOperator = "*";
                }
                else{
                    result = evaluate(oldOperator);
                    expression1.setLength(0);
                    expression1.append(result);
                    expression2.setLength(0);
                    //oldOperator=null;
                    main.setText(expression1);
                }*/

                //main.setText(evaluate(oldOperator));//TODO
            }

        });

        clear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                expression1.setLength(0);
                expression2.setLength(0);
                oldOperator=null;
                main.setText(expression1);//TODO
            }

        });

        equal.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                result=evaluate(oldOperator);
                expression1.setLength(0);
                expression1.append(result);
                expression2.setLength(0);
                oldOperator=null;
                main.setText(expression1);
                //TODO
            }

        });

        decimal.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO
                if (oldOperator == null) {
                    expression1.append('.');
                    main.setText(expression1.toString());
                } else {
                    expression2.append('.');
                    main.setText(expression1.toString() + " " + oldOperator + " " + expression2.toString());
                }
            }

        });

        divide.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(oldOperator!=null){
                    result = evaluate(oldOperator);
                    expression1.setLength(0);
                    expression1.append(result);
                    expression2.setLength(0);
                    // oldOperator=null;
                    main.setText(expression1);

                }
                else{
                    oldOperator="/";
                }
                oldOperator="/";
                /*if(oldOperator==null) {
                oldOperator = "/";
            }
            else{
                result = evaluate(oldOperator);
                expression1.setLength(0);
                expression1.append(result);
                expression2.setLength(0);
                //oldOperator=null;
                main.setText(expression1);
            }*/
                //TODO
            }

        });

        add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(oldOperator!=null){
                    result = evaluate(oldOperator);
                    expression1.setLength(0);
                    expression1.append(result);
                    expression2.setLength(0);
                    // oldOperator=null;
                    main.setText(expression1);

                }
                else{
                    oldOperator="+";
                }
                oldOperator="+";

                /*if(oldOperator==null) {
                oldOperator = "+";
            }
            else{
                result = evaluate(oldOperator);
                expression1.setLength(0);
                expression1.append(result);
                expression2.setLength(0);
                   // oldOperator=null;
                main.setText(expression1);
            }*/
                //TODO
            }

        });

        subtract.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(oldOperator!=null){
                    result = evaluate(oldOperator);
                    expression1.setLength(0);
                    expression1.append(result);
                    expression2.setLength(0);
                    // oldOperator=null;
                    main.setText(expression1);

                }
                else{
                    oldOperator="-";
                }
                oldOperator="-";/*if(oldOperator==null) {
                    oldOperator = "-";
                }
                else{
                    result = evaluate(oldOperator);
                    expression1.setLength(0);
                    expression1.append(result);
                    expression2.setLength(0);
                    //oldOperator=null;
                    main.setText(expression1);
                }*/
                //TODO
            }

        });

        //main.setText(expression1.append("0.0"));

    }

    /**
     * This method will evaluate an operation using expression1 and expression 2
     *
     * @param buttonPressed or operation to be performed
     * @return the result of the operation
     */
    public String evaluate(String buttonPressed) {
        //TODO
        switch (buttonPressed){
            case "*":  d = Double.parseDouble(expression1.toString());
                       d1 = Double.parseDouble(expression2.toString());
                       answer = d*d1;
                       //oldOperator=null;
                       break;
            case "+":  d = Double.parseDouble(expression1.toString());
                       d1 = Double.parseDouble(expression2.toString());
                       answer = d+d1;
                       //oldOperator=null;
                       break;
                       //return answer.toString();
            case "-":  d = Double.parseDouble(expression1.toString());
                       d1 = Double.parseDouble(expression2.toString());
                       answer = d-d1;
                       //oldOperator=null;
                       break;
                       //return answer.toString();
            case "/":  d = Double.parseDouble(expression1.toString());
                       d1 = Double.parseDouble(expression2.toString());
                       answer = d/d1;
                       //oldOperator=null;
                       break;
                       //return answer.toString();
            default:   break;
        }
        return answer.toString();
    }

}