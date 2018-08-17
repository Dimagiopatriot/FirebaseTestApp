package com.sdmitriy.firebasetestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sdmitriy.firebasetestapp.fragment.secondpart.StartPartTwoFragment;
import com.sdmitriy.firebasetestapp.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PartTwoActivity extends AppCompatActivity implements StartPartTwoFragment.ToolbarManager {

    @BindView(R.id.toolbarTop)
    Toolbar actionToolbar;

    @BindView(R.id.screen_name)
    TextView screenName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        window.setAttributes(winParams);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_part_two);
        ButterKnife.bind(this);

        actionToolbar.setNavigationIcon(null);
        actionToolbar.setTitle("");
        setSupportActionBar(actionToolbar);
        Utils.navigateToFragment(this, new StartPartTwoFragment(), R.id.fragment_container, "StartPartTwo", false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public void changeNavigationIcon(int iconDrawable) {
        actionToolbar.setNavigationIcon(iconDrawable);
    }

    @Override
    public void disableNavigationIcon() {
        actionToolbar.setNavigationIcon(null);
    }

    @Override
    public void setTitle(String title) {
        screenName.setText(title);
    }
}
