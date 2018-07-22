package com.example.ckpenep.stackoverflow.error.handler;

import com.example.ckpenep.stackoverflow.R;
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
//            if (e instanceof HttpException) {
//
//            } else if (e instanceof SocketTimeoutException) {
//
//            } else if (e instanceof IOException) {
//
//            } else {
//
//            }

            view.showError(mResourceManager.getString(R.string.network_error));
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

