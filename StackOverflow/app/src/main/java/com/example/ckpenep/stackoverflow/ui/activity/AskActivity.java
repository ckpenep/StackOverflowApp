package com.example.ckpenep.stackoverflow.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.common.RouterProvider;
import com.example.ckpenep.stackoverflow.ui.fragment.container.AchievementsFragmentContainer;
import com.example.ckpenep.stackoverflow.ui.fragment.container.AskFragmentContainer;
import com.example.ckpenep.stackoverflow.ui.fragment.container.InboxFragmentContainer;
import com.example.ckpenep.stackoverflow.ui.fragment.container.MoreFragmentContainer;
import com.example.ckpenep.stackoverflow.ui.fragment.container.QuestionsFragmentContainer;
import com.example.ckpenep.stackoverflow.utils.BottomNavigationViewHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class AskActivity extends MvpAppCompatActivity implements RouterProvider {

    @BindView(R.id.activity_ask_toolbar)
    Toolbar mToolbar;

    @Inject
    NavigatorHolder navigatorHolder;
    @Inject
    Router router;

    private AskFragmentContainer mAskFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        initContainers();
    }

    private void initContainers() {
        Integer sFragmentAsk = 0;

        FragmentManager fm = getSupportFragmentManager();
        mAskFragmentContainer = (AskFragmentContainer) fm.findFragmentByTag("ASK");
        if (mAskFragmentContainer == null) {
            mAskFragmentContainer = AskFragmentContainer.getNewInstance(sFragmentAsk);
            fm.beginTransaction()
                    .add(R.id.activity_ask_container, mAskFragmentContainer, "ASK")
                    .detach(mAskFragmentContainer).commitNow();
        }
    }

    @Override
    public Router getRouter() {
        return null;
    }
}
