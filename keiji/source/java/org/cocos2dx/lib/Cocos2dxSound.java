package org.cocos2dx.lib;

import android.content.Context;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import twitter4j.HttpResponseCode;

public class Cocos2dxSound {
    private static final int INVALID_SOUND_ID = -1;
    private static final int INVALID_STREAM_ID = -1;
    private static int LOAD_TIME_OUT = HttpResponseCode.INTERNAL_SERVER_ERROR;
    private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 5;
    private static final int MAX_SIMULTANEOUS_STREAMS_I9100 = 3;
    private static final int SOUND_PRIORITY = 1;
    private static final int SOUND_QUALITY = 5;
    private static final float SOUND_RATE = 1.0f;
    private static final String TAG = "Cocos2dxSound";
    private final Context mContext;
    private float mLeftVolume;
    private final HashMap<String, Integer> mPathSoundIDMap = new HashMap();
    private final HashMap<String, ArrayList<Integer>> mPathStreamIDsMap = new HashMap();
    private ConcurrentHashMap<Integer, SoundInfoForLoadedCompleted> mPlayWhenLoadedEffects = new ConcurrentHashMap();
    private float mRightVolume;
    private SoundPool mSoundPool;

    public class OnLoadCompletedListener implements OnLoadCompleteListener {
        public void onLoadComplete(SoundPool soundPool, int i, int i2) {
            if (i2 == 0) {
                SoundInfoForLoadedCompleted soundInfoForLoadedCompleted = (SoundInfoForLoadedCompleted) Cocos2dxSound.this.mPlayWhenLoadedEffects.get(Integer.valueOf(i));
                if (soundInfoForLoadedCompleted != null) {
                    soundInfoForLoadedCompleted.effectID = Cocos2dxSound.this.doPlayEffect(soundInfoForLoadedCompleted.path, i, soundInfoForLoadedCompleted.isLoop, soundInfoForLoadedCompleted.pitch, soundInfoForLoadedCompleted.pan, soundInfoForLoadedCompleted.gain);
                    synchronized (soundInfoForLoadedCompleted) {
                        soundInfoForLoadedCompleted.notifyAll();
                    }
                }
            }
        }
    }

    public class SoundInfoForLoadedCompleted {
        public int effectID = Cocos2dxSound.INVALID_STREAM_ID;
        public float gain;
        public boolean isLoop;
        public float pan;
        public String path;
        public float pitch;

        public SoundInfoForLoadedCompleted(String str, boolean z, float f, float f2, float f3) {
            this.path = str;
            this.isLoop = z;
            this.pitch = f;
            this.pan = f2;
            this.gain = f3;
        }
    }

    public Cocos2dxSound(Context context) {
        this.mContext = context;
        initData();
    }

    private void initData() {
        if (Cocos2dxHelper.getDeviceModel().contains("GT-I9100")) {
            this.mSoundPool = new SoundPool(MAX_SIMULTANEOUS_STREAMS_I9100, MAX_SIMULTANEOUS_STREAMS_I9100, SOUND_QUALITY);
        } else {
            this.mSoundPool = new SoundPool(SOUND_QUALITY, MAX_SIMULTANEOUS_STREAMS_I9100, SOUND_QUALITY);
        }
        this.mSoundPool.setOnLoadCompleteListener(new OnLoadCompletedListener());
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
    }

    public int preloadEffect(String str) {
        Integer num = (Integer) this.mPathSoundIDMap.get(str);
        if (num == null) {
            num = Integer.valueOf(createSoundIDFromAsset(str));
            if (num.intValue() != INVALID_STREAM_ID) {
                this.mPathSoundIDMap.put(str, num);
            }
        }
        return num.intValue();
    }

    public void unloadEffect(String str) {
        ArrayList arrayList = (ArrayList) this.mPathStreamIDsMap.get(str);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.mSoundPool.stop(((Integer) it.next()).intValue());
            }
        }
        this.mPathStreamIDsMap.remove(str);
        Integer num = (Integer) this.mPathSoundIDMap.get(str);
        if (num != null) {
            this.mSoundPool.unload(num.intValue());
            this.mPathSoundIDMap.remove(str);
        }
    }

    public int playEffect(String str, boolean z, float f, float f2, float f3) {
        Integer num = (Integer) this.mPathSoundIDMap.get(str);
        if (num != null) {
            return doPlayEffect(str, num.intValue(), z, f, f2, f3);
        }
        Integer valueOf = Integer.valueOf(preloadEffect(str));
        if (valueOf.intValue() == INVALID_STREAM_ID) {
            return INVALID_STREAM_ID;
        }
        SoundInfoForLoadedCompleted soundInfoForLoadedCompleted = new SoundInfoForLoadedCompleted(str, z, f, f2, f3);
        this.mPlayWhenLoadedEffects.putIfAbsent(valueOf, soundInfoForLoadedCompleted);
        synchronized (soundInfoForLoadedCompleted) {
            try {
                soundInfoForLoadedCompleted.wait((long) LOAD_TIME_OUT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int i = soundInfoForLoadedCompleted.effectID;
        this.mPlayWhenLoadedEffects.remove(valueOf);
        return i;
    }

    public void stopEffect(int i) {
        this.mSoundPool.stop(i);
        for (String str : this.mPathStreamIDsMap.keySet()) {
            if (((ArrayList) this.mPathStreamIDsMap.get(str)).contains(Integer.valueOf(i))) {
                ((ArrayList) this.mPathStreamIDsMap.get(str)).remove(((ArrayList) this.mPathStreamIDsMap.get(str)).indexOf(Integer.valueOf(i)));
                return;
            }
        }
    }

    public void pauseEffect(int i) {
        this.mSoundPool.pause(i);
    }

    public void resumeEffect(int i) {
        this.mSoundPool.resume(i);
    }

    public void pauseAllEffects() {
        if (!this.mPathStreamIDsMap.isEmpty()) {
            for (Entry value : this.mPathStreamIDsMap.entrySet()) {
                Iterator it = ((ArrayList) value.getValue()).iterator();
                while (it.hasNext()) {
                    this.mSoundPool.pause(((Integer) it.next()).intValue());
                }
            }
        }
    }

    public void resumeAllEffects() {
        if (!this.mPathStreamIDsMap.isEmpty()) {
            for (Entry value : this.mPathStreamIDsMap.entrySet()) {
                Iterator it = ((ArrayList) value.getValue()).iterator();
                while (it.hasNext()) {
                    this.mSoundPool.resume(((Integer) it.next()).intValue());
                }
            }
        }
    }

    public void stopAllEffects() {
        if (!this.mPathStreamIDsMap.isEmpty()) {
            for (Entry value : this.mPathStreamIDsMap.entrySet()) {
                Iterator it = ((ArrayList) value.getValue()).iterator();
                while (it.hasNext()) {
                    this.mSoundPool.stop(((Integer) it.next()).intValue());
                }
            }
        }
        this.mPathStreamIDsMap.clear();
    }

    public float getEffectsVolume() {
        return (this.mLeftVolume + this.mRightVolume) / 2.0f;
    }

    public void setEffectsVolume(float f) {
        float f2 = SOUND_RATE;
        float f3 = 0.0f;
        if (f >= 0.0f) {
            f3 = f;
        }
        if (f3 <= SOUND_RATE) {
            f2 = f3;
        }
        this.mRightVolume = f2;
        this.mLeftVolume = f2;
        if (!this.mPathStreamIDsMap.isEmpty()) {
            for (Entry value : this.mPathStreamIDsMap.entrySet()) {
                Iterator it = ((ArrayList) value.getValue()).iterator();
                while (it.hasNext()) {
                    this.mSoundPool.setVolume(((Integer) it.next()).intValue(), this.mLeftVolume, this.mRightVolume);
                }
            }
        }
    }

    public void end() {
        this.mSoundPool.release();
        this.mPathStreamIDsMap.clear();
        this.mPathSoundIDMap.clear();
        this.mPlayWhenLoadedEffects.clear();
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        initData();
    }

    public int createSoundIDFromAsset(String str) {
        int load;
        try {
            if (str.startsWith("/")) {
                load = this.mSoundPool.load(str, 0);
            } else {
                load = this.mSoundPool.load(this.mContext.getAssets().openFd(str), 0);
            }
        } catch (Throwable e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
            load = INVALID_STREAM_ID;
        }
        return load == 0 ? INVALID_STREAM_ID : load;
    }

    private float clamp(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f, f3));
    }

    private int doPlayEffect(String str, int i, boolean z, float f, float f2, float f3) {
        float clamp = (SOUND_RATE - clamp(f2, 0.0f, SOUND_RATE)) * (this.mLeftVolume * f3);
        float clamp2 = (this.mRightVolume * f3) * (SOUND_RATE - clamp(-f2, 0.0f, SOUND_RATE));
        int play = this.mSoundPool.play(i, clamp(clamp, 0.0f, SOUND_RATE), clamp(clamp2, 0.0f, SOUND_RATE), SOUND_PRIORITY, z ? INVALID_STREAM_ID : 0, clamp(SOUND_RATE * f, 0.5f, 2.0f));
        ArrayList arrayList = (ArrayList) this.mPathStreamIDsMap.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.mPathStreamIDsMap.put(str, arrayList);
        }
        arrayList.add(Integer.valueOf(play));
        return play;
    }

    public void onEnterBackground() {
        this.mSoundPool.autoPause();
    }

    public void onEnterForeground() {
        this.mSoundPool.autoResume();
    }
}
