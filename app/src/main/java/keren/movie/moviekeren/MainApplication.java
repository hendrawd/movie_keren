package keren.movie.moviekeren;

import android.app.Application;

import io.realm.Realm;

/**
 * @author hendrawd on 8/12/17
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
