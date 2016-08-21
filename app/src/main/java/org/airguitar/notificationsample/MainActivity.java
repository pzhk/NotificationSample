package org.airguitar.notificationsample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.airguitar.notificationsample.util.PlayServicesUtils;

public class MainActivity extends AppCompatActivity {

    /**
     * サーバー通信用AsyncTask
     */
    AsyncTask<Void, Void, Void> mRegisterTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // GooglePlayServicesのバージョンチェック
        if (PlayServicesUtils.checkGooglePlayServices(this)) {
            final String regId = GCMRegister.getRegistrationId(this);
            if (regId == null || regId.isEmpty()) {
                // GCMへ端末登録。
                Intent intent = new Intent(this, GCMRegisterService.class);
                startService(intent);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // GooglePlayServicesのバージョンチェック
        PlayServicesUtils.checkGooglePlayServices(this);
    }
}
