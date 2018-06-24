package com.example.ckpenep.stackoverflow.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ckpenep.stackoverflow.db.DataDao;
import com.example.ckpenep.stackoverflow.db.DatabaseHelper;
import com.example.ckpenep.stackoverflow.error.handler.DefaultErrorHandler;
import com.example.ckpenep.stackoverflow.error.handler.ErrorHandler;
import com.example.ckpenep.stackoverflow.model.system.ResourceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }

    @Singleton
    @Provides
    public DatabaseHelper provideMyDatabase(Context context){
        return Room.databaseBuilder(context, DatabaseHelper.class, "my-db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    public DataDao provideUserDao(DatabaseHelper myDatabase){
        return myDatabase.getDataDao();
    }

    @Singleton
    @Provides
    ErrorHandler provideSubscriberFactory() {
        return new DefaultErrorHandler();
    }

    @Singleton
    @Provides
    public ResourceManager provideResourceManager(Context context) { return new ResourceManager(context); }
}
