package github.girish3.sample;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import github.girish3.morpheus.MorphCircleToRect;
import github.girish3.morpheus.MorphRectToCircle;

public class MenuTransitionActivity extends AppCompatActivity {

    @Bind(R.id.scene_root)
    ViewGroup mSceneRoot;

    private int mColor;
    private int mBorderRadius;
    private Scene mFabScene;
    private Scene mMenuScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_transition);

        ButterKnife.bind(this);

        mColor = ContextCompat.getColor(this, R.color.red);
        mBorderRadius = getResources().getDimensionPixelSize(R.dimen.dialog_border_radius);

        //mMenuScene = Scene.getSceneForLayout(mSceneRoot, R.layout.a_scene, this);
        //mFabScene = Scene.getSceneForLayout(mSceneRoot, R.layout.b_scene, this);

        final View menuView = getLayoutInflater().inflate(R.layout.menu_scene, mSceneRoot, false);
        mMenuScene = new Scene(mSceneRoot, menuView);
        View fabScene = getLayoutInflater().inflate(R.layout.fab_scene, mSceneRoot, false);
        mFabScene = new Scene(mSceneRoot, fabScene);

        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transition transition = new MorphRectToCircle(mColor, mColor, mBorderRadius);
                transition.setDuration(600);
                transition.addTarget(R.id.fab_menu_layout);
                TransitionManager.go(mFabScene, transition);
            }
        });

        fabScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transition transition = new MorphCircleToRect(mColor, mColor, mBorderRadius);
                transition.setDuration(600);
                transition.addTarget(R.id.fab_menu_layout);
                TransitionManager.go(mMenuScene, transition);
            }
        });

        mFabScene.enter();
    }
}
