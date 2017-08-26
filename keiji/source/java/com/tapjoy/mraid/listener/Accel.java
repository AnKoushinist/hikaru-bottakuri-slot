package com.tapjoy.mraid.listener;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.tapjoy.mraid.controller.MraidSensor;
import java.util.List;
import twitter4j.TwitterResponse;

public class Accel implements SensorEventListener {
    MraidSensor a;
    int b = 0;
    int c = 0;
    int d = 0;
    private SensorManager e;
    private int f = 3;
    private long g;
    private int h;
    private long i;
    private long j;
    private float[] k;
    private float[] l = new float[]{0.0f, 0.0f, 0.0f};
    private boolean m;
    private boolean n;
    private float[] o = new float[]{0.0f, 0.0f, 0.0f};
    private float[] p = new float[]{-1.0f, -1.0f, -1.0f};

    public Accel(Context context, MraidSensor mraidSensor) {
        this.a = mraidSensor;
        this.e = (SensorManager) context.getSystemService("sensor");
    }

    public void setSensorDelay(int i) {
        this.f = i;
        if (this.b > 0 || this.c > 0) {
            stop();
            a();
        }
    }

    public void startTrackingTilt() {
        if (this.b == 0) {
            a();
        }
        this.b++;
    }

    public void stopTrackingTilt() {
        if (this.b > 0) {
            int i = this.b - 1;
            this.b = i;
            if (i == 0) {
                stop();
            }
        }
    }

    public void startTrackingShake() {
        if (this.c == 0) {
            setSensorDelay(1);
            a();
        }
        this.c++;
    }

    public void stopTrackingShake() {
        if (this.c > 0) {
            int i = this.c - 1;
            this.c = i;
            if (i == 0) {
                setSensorDelay(3);
                stop();
            }
        }
    }

    public void startTrackingHeading() {
        if (this.d == 0) {
            List sensorList = this.e.getSensorList(2);
            if (sensorList.size() > 0) {
                this.e.registerListener(this, (Sensor) sensorList.get(0), this.f);
                a();
            }
        }
        this.d++;
    }

    public void stopTrackingHeading() {
        if (this.d > 0) {
            int i = this.d - 1;
            this.d = i;
            if (i == 0) {
                stop();
            }
        }
    }

    private void a() {
        List sensorList = this.e.getSensorList(1);
        if (sensorList.size() > 0) {
            this.e.registerListener(this, (Sensor) sensorList.get(0), this.f);
        }
    }

    public void stop() {
        if (this.d == 0 && this.c == 0 && this.b == 0) {
            this.e.unregisterListener(this);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case TwitterResponse.READ /*1*/:
                this.o = this.l;
                this.l = (float[]) sensorEvent.values.clone();
                this.n = true;
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                this.k = (float[]) sensorEvent.values.clone();
                this.m = true;
                break;
        }
        if (this.k != null && this.l != null && this.n && this.m) {
            this.n = false;
            this.m = false;
            float[] fArr = new float[9];
            SensorManager.getRotationMatrix(fArr, new float[9], this.l, this.k);
            this.p = new float[3];
            SensorManager.getOrientation(fArr, this.p);
            this.a.onHeadingChange(this.p[0]);
        }
        if (sensorEvent.sensor.getType() == 1) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.g > 500) {
                this.h = 0;
            }
            if (currentTimeMillis - this.i > 100) {
                if ((Math.abs(((((this.l[0] + this.l[1]) + this.l[2]) - this.o[0]) - this.o[1]) - this.o[2]) / ((float) (currentTimeMillis - this.i))) * 10000.0f > 1000.0f) {
                    int i = this.h + 1;
                    this.h = i;
                    if (i >= 2 && currentTimeMillis - this.j > 2000) {
                        this.j = currentTimeMillis;
                        this.h = 0;
                        this.a.onShake();
                    }
                    this.g = currentTimeMillis;
                }
                this.i = currentTimeMillis;
                this.a.onTilt(this.l[0], this.l[1], this.l[2]);
            }
        }
    }

    public float getHeading() {
        return this.p[0];
    }

    public void stopAllListeners() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        try {
            stop();
        } catch (Exception e) {
        }
    }
}
