package com.example.vincent.accelerometer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import android.hardware.Sensor;

import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;



public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView X, Y;
    private Sensor mySensor;
    private SensorManager mySensorManager;
    private int newX;
    private int newY;
    private int xConversion = 0;
    private int yConversion = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        newX = Math.abs((int)Math.ceil(event.values[0]));
        newY = Math.abs((int)Math.ceil(event.values[1]));
        X.setText("X : "+ newX);
        Y.setText("Y : "+ newY);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor = mySensorManager.getDefaultSensor((Sensor.TYPE_ACCELEROMETER));
        mySensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        X = (TextView)findViewById(R.id.editText);
        Y = (TextView)findViewById(R.id.editText2);


        Button Email = (Button) findViewById(R.id.action_send_email);
        Email.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText value = (EditText) findViewById(R.id.output);
                String message = value.getText().toString();
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:vcheng3@buffalo.edu"));
                sendIntent.putExtra(Intent.EXTRA_TEXT, value.getText());

                String subject = "Value of answer!";

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT,message);
                emailIntent.setType("text/plain");
                startActivity(Intent.createChooser(emailIntent,"Email-to-user"));

            }
        });

    }
    int getBase(String value ){
        int i = 0;
        int counter = 0;
        for(; i < value.length(); i++){
            if(value.charAt(i) > counter){
                counter = (int)value.charAt(i);
            }
        }
        if(counter == 48){
            return 1;
        }
        if(counter == 49){
            return 2;
        }
        if(counter == 50){
            return 3;
        }
        if(counter == 51){
            return 4;
        }
        if(counter == 52){
            return 5;
        }
        if(counter == 53){
            return 6;
        }
        if(counter == 54){
            return 7;
        }
        if(counter == 55){
            return 8;
        }
        if(counter == 56){
            return 9;
        }
        if(counter == 57){
            return 10;
        }
        if(counter == 65){
            return 11;
        }
        if(counter == 66){
            return 12;
        }
        if(counter == 67){
            return 13;
        }
        if(counter == 68){
            return 14;
        }
        if(counter == 69){
            return 15;
        }
        if(counter == 70){
            return 16;
        }
        return counter = counter + 1;
    }

    double convertToBaseTen(String number , int base){
        double runningSum = 0;
        double length = number.length() - 1;
        double counter = 0;
        for(int i = 0; i < number.length(); i++){
            if(number.charAt(i) == 48){
                counter = 0;
            }
            if(number.charAt(i) == 49){
                counter = 1;
            }
            if(number.charAt(i) == 50){
                counter = 2;
            }
            if(number.charAt(i) == 51){
                counter = 3;
            }
            if(number.charAt(i) == 52){
                counter = 4;
            }
            if(number.charAt(i) == 53){
                counter = 5;
            }
            if(number.charAt(i) == 54){
                counter = 6;
            }
            if(number.charAt(i) == 55){
                counter = 7;
            }
            if(number.charAt(i) == 56){
                counter = 8;
            }
            if(number.charAt(i) == 57){
                counter = 9;
            }
            if(number.charAt(i) == 65){
                counter = 10;
            }
            if(number.charAt(i) == 66){
                counter = 11;
            }
            if(number.charAt(i) == 67){
                counter = 12;
            }
            if(number.charAt(i) == 68){
                counter = 13;
            }
            if(number.charAt(i) == 69){
                counter = 14;
            }
            if(number.charAt(i) == 70){
                counter = 15;
            }
            runningSum = runningSum + counter * Math.pow(base,length);
            length--;
        }
        return runningSum;
    }
    public void enter(View view) {
        /*
        double xVal;
        double yVal;
        EditText edit = (EditText) findViewById(R.id.editText);
        EditText edit1 = (EditText) findViewById(R.id.editText2);
        EditText edit2 = (EditText) findViewById(R.id.output);
        String input1 = edit.getText().toString();
        String input2 = edit1.getText().toString();
        xVal = convertToBaseTen(input1,getBase(input1));
        yVal = convertToBaseTen(input1,getBase(input2));
        edit2.setText("" + xVal + yVal);
        */
        TextView FinalAnswer;
        int sum = 0;
        String input1 = String.valueOf(newX);
        String input2 = String.valueOf(newY);

        xConversion = (int)convertToBaseTen(input1,getBase(input1));
        yConversion = (int)convertToBaseTen(input2,getBase(input2));
        sum = xConversion + yConversion;
        FinalAnswer = (TextView)findViewById(R.id.output);
        FinalAnswer.setText("sum is" + " " + sum);

    }
}
