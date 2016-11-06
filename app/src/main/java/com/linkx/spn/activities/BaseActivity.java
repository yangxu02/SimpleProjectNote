package com.linkx.spn.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.linkx.spn.AndroidApplication;
import com.linkx.spn.di.components.ApplicationComponent;
import com.linkx.spn.di.modules.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
