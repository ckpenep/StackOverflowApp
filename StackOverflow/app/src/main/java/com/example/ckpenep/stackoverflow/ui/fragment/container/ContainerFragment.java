package com.example.ckpenep.stackoverflow.ui.fragment.container;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.common.RouterProvider;
import com.example.ckpenep.stackoverflow.di.LocalCiceroneHolder;
import com.example.ckpenep.stackoverflow.model.Question;
import com.example.ckpenep.stackoverflow.ui.Screens;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;
import com.example.ckpenep.stackoverflow.ui.fragment.AchievementFragment;
import com.example.ckpenep.stackoverflow.ui.fragment.HistoryFragment;
import com.example.ckpenep.stackoverflow.ui.fragment.InboxFragment;
import com.example.ckpenep.stackoverflow.ui.fragment.MoreFragment;
import com.example.ckpenep.stackoverflow.ui.fragment.QuestionDetailsFragment;
import com.example.ckpenep.stackoverflow.ui.fragment.QuestionsListFragment;

import javax.inject.Inject;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportAppNavigator;

public class ContainerFragment extends Fragment implements RouterProvider, BackButtonListener {

    public static final String EXTRA_NAME = "extra_name";
    public MvpAppCompatFragment fragment;

    @Inject
    protected LocalCiceroneHolder ciceroneHolder;

    private Navigator navigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_container, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private Cicerone<Router> getCicerone() {
        return ciceroneHolder.getCicerone(getContainerName());
    }

    public Integer getContainerName() {
        return getArguments().getInt(EXTRA_NAME, 0);
    }

    @Override
    public Router getRouter() {
        return getCicerone().getRouter();
    }

    @Override
    public void onResume() {
        super.onResume();
        getCicerone().getNavigatorHolder().setNavigator(getNavigator());
    }

    @Override
    public void onPause() {
        getCicerone().getNavigatorHolder().removeNavigator();
        super.onPause();
    }

    @Override
    public boolean onBackPressed() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.ftc_container);
        if (fragment != null
                && fragment instanceof BackButtonListener
                && ((BackButtonListener) fragment).onBackPressed()) {
            return true;
        } else {
            ((RouterProvider) getActivity()).getRouter().exit();
            return true;
        }
    }

    private Navigator getNavigator() {
        if (navigator == null) {
            navigator = new SupportAppNavigator(getActivity(), getChildFragmentManager(), R.id.ftc_container) {

                @Override
                protected Intent createActivityIntent(Context context, String screenKey, Object data) {
                    return null;
                }

                @Override
                protected Fragment createFragment(String screenKey, Object data) {
                    switch (screenKey) {
                        case Screens.QUESTIONS_SCREEN:
                            return QuestionsListFragment.newInstance();

                        case Screens.INBOX_SCREEN:
                            return InboxFragment.newInstance();

                        case Screens.ACHIEVEMENTS_SCREEN:
                            return AchievementFragment.newInstance();

                        case Screens.MORE_SCREEN:
                            return MoreFragment.newInstance();

                        case Screens.QUESTIONS_DETAILS_SCREEN:
                            return QuestionDetailsFragment.newInstance((Question) data);

                        case Screens.IHISTORY_SCREEN:
                            return HistoryFragment.newInstance();
                    }

                    return null;
                }

                @Override
                protected void exit() {
                    ((RouterProvider) getActivity()).getRouter().exit();
                }
            };
        }
        return navigator;
    }
}
