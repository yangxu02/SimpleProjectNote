package com.linkx.spn.di.components;

import android.content.Context;
import com.linkx.spn.di.modules.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context context();
}
