package org.airguitar.notificationsample;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.airguitar.notificationsample.util.Constants;

public class GCMRegisterService extends IntentService {
    private static final String TAG = GCMRegisterService.class.getSimpleName();
    private Handler toaster = new Handler(Looper.getMainLooper());

    public GCMRegisterService() {
        super("GCMRegisterService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String oldRegId = GCMRegister.getRegistrationId(this);
        String regId = GCMRegister.registerSync(this, Constants.SENDER_ID);
        if (regId != null) {
            if (oldRegId != null)  onUnregistered(this, oldRegId);
            onRegistered(this, regId);
        }
    }
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "onRegistered: regId = " + registrationId);
        // GCMから発行された端末IDをアプリサーバに登録する。
        //String uri = SERVER_URL + "?action=register" + "&userId=" + USER_ID + "&regId=" + registrationId;
        //Util.doGet(uri);
    }
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "onUnregistered: regId = " + registrationId);
        // GCMから発行された端末IDをアプリサーバから登録解除する。
        //String uri = SERVER_URL + "?action=unregister" + "&userId=" + USER_ID + "&regId=" + registrationId;
        //Util.doGet(uri);
    }
}