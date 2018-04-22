package com.example.ckpenep.stackoverflow.ui.fragment.container;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.ui.Screens;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;

public class QuestionsFragmentContainer extends ContainerFragment {

    private Cicerone<Router> getCicerone() {
        return ciceroneHolder.getCicerone(getContainerName());
    }

    public static QuestionsFragmentContainer getNewInstance(Integer name) {
        QuestionsFragmentContainer fragment = new QuestionsFragmentContainer();
        Bundle arguments = new Bundle();
        arguments.putInt(EXTRA_NAME, name);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getChildFragmentManager().findFragmentById(R.id.ftc_container) == null) {
            getCicerone().getRouter().replaceScreen(Screens.QUESTIONS_SCREEN, 0);
        }
    }
}
