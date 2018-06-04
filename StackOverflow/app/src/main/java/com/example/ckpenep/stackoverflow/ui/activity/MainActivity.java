package com.example.ckpenep.stackoverflow.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.common.InterfaceCommunicator;
import com.example.ckpenep.stackoverflow.common.RouterProvider;
import com.example.ckpenep.stackoverflow.presentation.presenter.MainPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.MainView;
import com.example.ckpenep.stackoverflow.ui.Screens;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;
import com.example.ckpenep.stackoverflow.ui.fragment.MenuPickerFragment;
import com.example.ckpenep.stackoverflow.ui.fragment.container.AchievementsFragmentContainer;
import com.example.ckpenep.stackoverflow.ui.fragment.container.InboxFragmentContainer;
import com.example.ckpenep.stackoverflow.ui.fragment.container.MoreFragmentContainer;
import com.example.ckpenep.stackoverflow.ui.fragment.container.QuestionsFragmentContainer;
import com.example.ckpenep.stackoverflow.utils.BottomNavigationViewHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;

public class MainActivity extends MvpAppCompatActivity implements MainView, RouterProvider, InterfaceCommunicator {

    private static final String DIALOG_MENU = "DialogMenu";

    @InjectPresenter
    MainPresenter mMainPresenter;

    @BindView(R.id.activity_main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_main_bottom_navigation)
    BottomNavigationView mBottomNavigationView;

    @Inject
    NavigatorHolder navigatorHolder;
    @Inject
    Router router;

    private QuestionsFragmentContainer mQuestionsFragmentContainer;
    private InboxFragmentContainer mInboxFragmentContainer;
    private AchievementsFragmentContainer mAchievementsFragmentContainer;
    private MoreFragmentContainer mMoreFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);


        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        initContainers();
        setupBottomNavigationView();

        if (savedInstanceState == null) {
            mMainPresenter.onTabQuestionClick();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_container);
        if (fragment != null && fragment instanceof BackButtonListener) {
            ((BackButtonListener) fragment).onBackPressed();
        } else {
            mMainPresenter.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                mMainPresenter.onTabSettingsClick();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public Router getRouter() {
        return router;
    }

    private void initContainers() {
        Integer sFragmentQuestion = 0;
        Integer sFragmentInbox = 1;
        Integer sFragmentAchievement = 2;
        Integer sFragmentMore = 3;
        FragmentManager fm = getSupportFragmentManager();
        mQuestionsFragmentContainer = (QuestionsFragmentContainer) fm.findFragmentByTag("QUESTION");
        if (mQuestionsFragmentContainer == null) {
            mQuestionsFragmentContainer = QuestionsFragmentContainer.getNewInstance(sFragmentQuestion);
            fm.beginTransaction()
                    .add(R.id.activity_main_container, mQuestionsFragmentContainer, "QUESTION")
                    .detach(mQuestionsFragmentContainer).commitNow();
        }

        mInboxFragmentContainer = (InboxFragmentContainer) fm.findFragmentByTag("INBOX");
        if (mInboxFragmentContainer == null) {
            mInboxFragmentContainer = InboxFragmentContainer.getNewInstance(sFragmentInbox);
            fm.beginTransaction()
                    .add(R.id.activity_main_container, mInboxFragmentContainer, "INBOX")
                    .detach(mInboxFragmentContainer).commitNow();
        }

        mAchievementsFragmentContainer = (AchievementsFragmentContainer) fm.findFragmentByTag("ACHIEVEMENT");
        if (mAchievementsFragmentContainer == null) {
            mAchievementsFragmentContainer = AchievementsFragmentContainer.getNewInstance(sFragmentAchievement);
            fm.beginTransaction()
                    .add(R.id.activity_main_container, mAchievementsFragmentContainer, "ACHIEVEMENT")
                    .detach(mAchievementsFragmentContainer).commitNow();
        }

        mMoreFragmentContainer = (MoreFragmentContainer) fm.findFragmentByTag("MORE");
        if (mMoreFragmentContainer == null) {
            mMoreFragmentContainer = MoreFragmentContainer.getNewInstance(sFragmentMore);
            fm.beginTransaction()
                    .add(R.id.activity_main_container, mMoreFragmentContainer, "MORE")
                    .detach(mMoreFragmentContainer).commitNow();
        }
    }

    @Override
    public void showDialog() {
        enableDialog();
    }

    private void enableDialog() {
        FragmentManager manager = getSupportFragmentManager();
        MenuPickerFragment dialog = new MenuPickerFragment();
        dialog.show(manager, DIALOG_MENU);
    }

    @Override
    public void sendRequestCode(int code) {
        Log.d("MENU", Integer.toString(code));
    }

    private void setupBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_questions:
                    mMainPresenter.onTabQuestionClick();
                    break;
                case R.id.menu_inbox:
                    mMainPresenter.onTabInboxClick();
                    break;
                case R.id.menu_achievements:
                    mMainPresenter.onTabAchievementClick();
                    break;
                case R.id.menu_more:
                    mMainPresenter.onTabMoreClick();
                    break;
            }
            return true;
        });
    }

    private Navigator navigator = new Navigator() {
        @Override
        public void applyCommands(Command[] commands) {
            for (Command command : commands) applyCommand(command);
        }

        public void applyCommand(Command command) {
            if (command instanceof Back) {
                finish();
            } else if (command instanceof Replace) {
               replace(((Replace) command));
            }
            if (command instanceof Forward) {
                forward((Forward) command);
            }
        }

        private void forward(Forward command) {
            switch (command.getScreenKey()) {
                case Screens.SETTINGS_ACTIVITY_SCREEN:
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    break;
                default:
                    Log.e("Cicerone", "Unknown screen: " + command.getScreenKey());
                    break;
            }
        }

        private void replace(Replace command)
        {
            FragmentManager fm = getSupportFragmentManager();

            switch (command.getScreenKey()) {
                case Screens.QUESTIONS_SCREEN:
                    fm.beginTransaction()
                            .detach(mInboxFragmentContainer)
                            .detach(mAchievementsFragmentContainer)
                            .detach(mMoreFragmentContainer)
                            .attach(mQuestionsFragmentContainer)
                            .commitNow();
                    break;
                case Screens.INBOX_SCREEN:
                    fm.beginTransaction()
                            .detach(mAchievementsFragmentContainer)
                            .detach(mQuestionsFragmentContainer)
                            .detach(mMoreFragmentContainer)
                            .attach(mInboxFragmentContainer)
                            .commitNow();
                    break;
                case Screens.ACHIEVEMENTS_SCREEN:
                    fm.beginTransaction()
                            .detach(mInboxFragmentContainer)
                            .detach(mQuestionsFragmentContainer)
                            .detach(mMoreFragmentContainer)
                            .attach(mAchievementsFragmentContainer)
                            .commitNow();
                    break;
                case Screens.MORE_SCREEN:
                    fm.beginTransaction()
                            .detach(mInboxFragmentContainer)
                            .detach(mQuestionsFragmentContainer)
                            .detach(mAchievementsFragmentContainer)
                            .attach(mMoreFragmentContainer)
                            .commitNow();
                    break;

            }
        }
    };
}