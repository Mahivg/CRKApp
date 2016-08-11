package com.auidbook.prototype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.auidbook.prototype.Model.DataStorage.SessionManager;

public class BaseLoggedInUserActivity extends AppCompatActivity {
    protected SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getApplicationContext());
        if (!sessionManager.checkLogin()) {
            this.finish();
        }
    }
}
