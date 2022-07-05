package com.yayapipistudio.bgservice;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

public final class BGServiceInstance extends Application {
    public static Activity UnityActivity;

    public static void ReceiveUnityActivity(Activity _unityActivity){
        UnityActivity = _unityActivity;
    }

    public void Toast(String msg){
        Toast.makeText(UnityActivity,msg,Toast.LENGTH_SHORT).show();
    }

    public static void StartService(){
        UnityActivity.startForegroundService(new Intent(UnityActivity, ServiceTemplate.class));
    }

    public static void StopService(){
        Intent serviceIntent = new Intent(UnityActivity, ServiceTemplate.class);
        UnityActivity.stopService(serviceIntent);
    }
}
