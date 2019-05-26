package pe.com.gadolfolozano.testapp.shake;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

public class ShakeService extends Service implements SensorEventListener {

    private float accel; // acceleration apart from gravity
    private float accelCurrent; // current acceleration including gravity
    private ShakeListener shakeListener;

    private IBinder serviceBinder = new ShakeServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return serviceBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer,
                SensorManager.SENSOR_DELAY_UI, new Handler());
        return START_STICKY;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Do nothing
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float mAccelLast = accelCurrent;
        accelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
        float delta = accelCurrent - mAccelLast;
        accel = accel * 0.9f + delta; // perform low-cut filter

        if (accel > 11 && shakeListener != null) {
            shakeListener.onShake();
        }
    }

    public void registerCallBack(ShakeListener shakeListener) {
        this.shakeListener = shakeListener;
    }


    public class ShakeServiceBinder extends Binder {

        public ShakeService getService() {
            return ShakeService.this;
        }
    }

}
