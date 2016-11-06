package com.linkx.spn.di.components;

import android.app.Activity;
import android.app.Application;
import com.linkx.spn.di.ActivityScope;
import com.linkx.spn.di.modules.ActivityModule;
import dagger.Component;

@ActivityScope
@Component(dependencies = Application.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
