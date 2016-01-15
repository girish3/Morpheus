package github.girish3.morpheus;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.transition.ChangeBounds;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * Created by Girish on 07/01/16.
 */
public class MorphCircleToRect extends ChangeBounds {

    private static final String PROP_COLOR = "prop_color";
    private static final String PROP_BORDER_RADIUS = "prop_border_radius";
    private int mStartColor;
    private int mEndColor;
    private int mEndRadius;
    private static final int DEFAULT_DURATION = 1000;

    public MorphCircleToRect(int startColor, int endColor, int endRadius) {
        super();
        mStartColor = startColor;
        mEndColor = endColor;
        mEndRadius = endRadius;
    }

    /*public MorphCircleToRect() {
        super();
    }

    public MorphCircleToRect(Context context, AttributeSet attrs) {
        super(context, attrs);
    }*/

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);

        View v = transitionValues.view;

        transitionValues.values.put(PROP_COLOR, mStartColor);
        transitionValues.values.put(PROP_BORDER_RADIUS, v.getWidth() / 2);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);

        transitionValues.values.put(PROP_COLOR, mEndColor);
        transitionValues.values.put(PROP_BORDER_RADIUS, mEndRadius);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        Animator animator = super.createAnimator(sceneRoot, startValues, endValues);

        int startColor = (int) startValues.values.get(PROP_COLOR);
        int endColor = (int) endValues.values.get(PROP_COLOR);
        int startRadius = (int) startValues.values.get(PROP_BORDER_RADIUS);
        int endRadius = (int) endValues.values.get(PROP_BORDER_RADIUS);

        MorpheusDrawable morpheusDrawable  = new MorpheusDrawable(startColor, startRadius);
        endValues.view.setBackground(morpheusDrawable);

        Animator color_animator = ObjectAnimator.ofArgb(morpheusDrawable, morpheusDrawable.COLOR, endColor);
        Animator radius_animator = ObjectAnimator.ofInt(morpheusDrawable, morpheusDrawable.BORDER_RADIUS, endRadius);

        ArrayList<Animator> animators = new ArrayList<>();

        if (endValues.view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) endValues.view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View v = vg.getChildAt(i);
                v.setAlpha(0f);
                Animator anim = ObjectAnimator.ofFloat(v, "alpha", 1f);
                animators.add(anim);
            }
        }

        /*if (startValues.view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) endValues.view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View v = vg.getChildAt(i);
                v.setAlpha(1f);
                Animator anim = ObjectAnimator.ofFloat(v, "alpha", 0f);
                animators.add(anim);
            }
        }*/

        animators.add(animator);
        animators.add(radius_animator);
        animators.add(color_animator);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        animatorSet.setInterpolator(new FastOutSlowInInterpolator());

        if (getDuration() != -1) animatorSet.setDuration(getDuration());
        else animatorSet.setDuration(DEFAULT_DURATION);

        return animatorSet;
    }
}