package com.example.ckpenep.stackoverflow.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.ui.Screens;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;
import com.example.ckpenep.stackoverflow.ui.fragment.AskFragment;
import com.example.ckpenep.stackoverflow.ui.fragment.container.AskFragmentContainer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;

public class AskActivity extends MvpAppCompatActivity  {

    @BindView(R.id.activity_ask_toolbar)
    Toolbar mToolbar;

    @Inject
    NavigatorHolder navigatorHolder;

    private AskFragmentContainer mAskFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        if (savedInstanceState == null) {
            navigator.applyCommands(new Command[]{new Replace(Screens.ASK_SCREEN, null)});
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.activity_ask_container);
        if (fragment != null
                && fragment instanceof BackButtonListener
                && ((BackButtonListener) fragment).onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    private Navigator navigator = new SupportAppNavigator(this, R.id.activity_ask_container) {

        @Override
        protected Intent createActivityIntent(Context context, String screenKey, Object data) {
            return null;
        }

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.ASK_SCREEN:
                    return AskFragment.newInstance();
                case Screens.TAGS_SCREEN:

            }
            return null;
        }
    };
}
