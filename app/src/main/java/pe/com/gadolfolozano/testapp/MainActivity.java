package pe.com.gadolfolozano.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pe.com.gadolfolozano.testapp.shake.ShakeActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonShake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonShake = findViewById(R.id.button_shake);

        buttonShake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShakeActivity.class));
            }
        });
    }
}
