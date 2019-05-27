package pe.com.gadolfolozano.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pe.com.gadolfolozano.testapp.compass.CompassActivity;
import pe.com.gadolfolozano.testapp.shake.ShakeActivity;
import pe.com.gadolfolozano.testapp.stepper.StepperActiviy;

public class MainActivity extends AppCompatActivity {

    private Button buttonShake;
    private Button buttonCompass;
    private Button buttonStepper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonShake = findViewById(R.id.button_shake);
        buttonCompass = findViewById(R.id.button_compass);
        buttonStepper = findViewById(R.id.button_stepper);

        buttonShake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShakeActivity.class));
            }
        });

        buttonCompass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CompassActivity.class));
            }
        });

        buttonStepper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StepperActiviy.class));
            }
        });
    }
}
