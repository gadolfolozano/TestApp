package pe.com.gadolfolozano.testapp.shake;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Random;

import pe.com.gadolfolozano.testapp.R;

public class ShakeActivity extends AppCompatActivity implements ShakeListener {

    private TextView tvShake;
    private Intent intent;
    private ShakeService shakeService;
    private boolean bound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        tvShake = findViewById(R.id.tv_shake);

        startShakeService();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unBindService();
    }

    private void startShakeService() {
        intent = new Intent(this, ShakeService.class);
        startService(intent);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ShakeService.ShakeServiceBinder binder = (ShakeService.ShakeServiceBinder) service;
            shakeService = binder.getService();
            bound = true;
            shakeService.registerCallBack(ShakeActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    private void bindService() {
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unBindService() {
        if (bound) {
            shakeService.registerCallBack(null);
            unbindService(serviceConnection);
            bound = false;
        }
    }

    @Override
    public void onShake() {
        int color = Color.argb(255, new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
        tvShake.setTextColor(color);
    }
}
