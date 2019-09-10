package com.yasiralijaved.android.arc.components.di;

import android.app.Application;

import com.yasiralijaved.android.arc.component.db.di.DbModule;
import com.yasiralijaved.android.arc.component.http.di.BackendServiceModule;
import com.yasiralijaved.android.arc.components.MyApplication;
import com.yasiralijaved.android.arc.feature.users.di.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        BackendServiceModule.class,
        DbModule.class,
        ViewModelModule.class,
        ActivityModule.class,
        FragmentModule.class,
        AndroidSupportInjectionModule.class})
@Singleton
public interface AppComponent {

    /* We will call this builder interface from our custom Application class.
     * This will set our application object to the AppComponent.
     * So inside the AppComponent the application instance is available.
     * So this application instance can be accessed by our modules
     * such as ApiModule when needed
     * */
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    /*
     * This is our custom Application class
     * */
    void inject(MyApplication myApplication);
}
