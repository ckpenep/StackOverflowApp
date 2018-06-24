package com.example.ckpenep.stackoverflow.error.handler;

import com.example.ckpenep.stackoverflow.error.ErrorOutput;

public interface ErrorHandler {
    void handleError(Throwable e);
    void attachView(ErrorOutput view);
    void detachView();
}
