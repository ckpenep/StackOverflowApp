package com.example.ckpenep.stackoverflow.error.handler;

import android.util.Log;

import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.error.ErrorOutput;
import com.example.ckpenep.stackoverflow.model.system.ResourceManager;

import javax.inject.Inject;

public class DefaultErrorHandler implements ErrorHandler {

    public static final String LOG = DefaultErrorHandler.class.getSimpleName();

    private ErrorOutput view;

    @Inject
    ResourceManager mResourceManager;

    public DefaultErrorHandler() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void handleError(Throwable e) {
        if(e != null && !e.getMessage().isEmpty() && view != null)
        {
            Log.d("ERROR_HANDLER", e.getMessage());
            view.showError(e.getMessage().toString());
        }
    }

    @Override
    public void attachView(ErrorOutput view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}

