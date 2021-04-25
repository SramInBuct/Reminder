package com.example.reminder.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;

public class AudioUtil {
    private static int lastModel = -10;
    /**
     * 音频外放
     */
    public static void changeToSpeaker(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        //注意此处，蓝牙未断开时使用MODE_IN_COMMUNICATION而不是MODE_NORMAL
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.stopBluetoothSco();
        audioManager.setBluetoothScoOn(false);
        audioManager.setSpeakerphoneOn(true);
    }

    /**
     * 切换到蓝牙音箱
     */
    public static void changeToHeadset(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.startBluetoothSco();
        audioManager.setBluetoothScoOn(true);
        audioManager.setSpeakerphoneOn(false);
    }

    /**
     * 切换到听筒
     */
    public static void changeToReceiver(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setSpeakerphoneOn(false);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
    }


    @SuppressLint("WrongConstant")
    public static void dispose(Context context, AudioManager.OnAudioFocusChangeListener focusRequest) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(lastModel);
        if (audioManager.isBluetoothScoOn()) {
            audioManager.setBluetoothScoOn(false);
            audioManager.stopBluetoothSco();
        }
        audioManager.unloadSoundEffects();
        if (null != focusRequest) {
            audioManager.abandonAudioFocus(focusRequest);
        }
    }


    @SuppressLint("WrongConstant")
    public static void getModel(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        lastModel = audioManager.getMode();
    }

    public static void changeToNormal(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_NORMAL);
    }

    @SuppressLint("WrongConstant")
    public static boolean isWiredHeadsetOn(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        AudioDeviceInfo[] devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);
        for (AudioDeviceInfo device : devices) {
            if (device.getType() == AudioDeviceInfo.TYPE_WIRED_HEADPHONES
            ||device.getType() == AudioDeviceInfo.TYPE_WIRED_HEADSET) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBluetoothA2dpOn(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.isBluetoothA2dpOn();
    }

    /**
     * context 传入的是MicroContext.getApplication()
     * @param context
     */
    public static void choiceAudioModel(Context context) {
        if (isWiredHeadsetOn(context)) {
            changeToReceiver(context);
        } else if (isBluetoothA2dpOn(context)) {
            changeToHeadset(context);
        } else {
            changeToSpeaker(context);
        }
    }

//    public static void pauseMusic(Context context, AudioManager.OnAudioFocusChangeListener focusRequest) {
//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//        audioManager.requestAudioFocus(focusRequest, AudioManager.STREAM_MUSIC, AUDIOFOCUS_GAIN);
//    }


}
