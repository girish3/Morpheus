package github.girish3.sample;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.girish3.morpheus.MorphCircleToRect;
import github.girish3.morpheus.MorphRectToCircle;

public class BasicMorphActivity extends AppCompatActivity {

    @Bind(R.id.scene_root)
    ViewGroup sceneRoot;
    private Scene mRectScene;
    private Scene mCircleScene;
    private Scene mCurrentScene;
    private int mRectColor;
    private int mCircleColor;
    private int mBorderRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_morph);
        ButterKnife.bind(this);

        mRectScene = Scene.getSceneForLayout(sceneRoot, R.layout.a_scene, this);
        mCircleScene = Scene.getSceneForLayout(sceneRoot, R.layout.b_scene, this);

        mRectColor = ContextCompat.getColor(this, R.color.red);
        mCircleColor = ContextCompat.getColor(this, R.color.green);
        mBorderRadius = getResources().getDimensionPixelSize(R.dimen.dialog_border_radius);
        mRectScene.enter();
        mCurrentScene = mRectScene;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Transition transition;

                if (mCurrentScene == mRectScene) {
                    transition = new MorphRectToCircle(mRectColor, mCircleColor, mBorderRadius);
                    mCurrentScene = mCircleScene;
                }
                else {
                    transition = new MorphCircleToRect(mCircleColor, mRectColor, mBorderRadius);
                    mCurrentScene = mRectScene;
                }

                TransitionManager.go(mCurrentScene, transition);

                handler.postDelayed(this, 2000);
            }
        }, 1000);
    }
}
