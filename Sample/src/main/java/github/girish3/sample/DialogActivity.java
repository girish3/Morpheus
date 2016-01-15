package github.girish3.sample;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import github.girish3.morpheus.MorphCircleToRect;
import github.girish3.morpheus.MorphRectToCircle;

public class DialogActivity extends AppCompatActivity {

    @Bind(R.id.dialog_layout)
    ViewGroup mDialogLayout;

    @OnClick(R.id.close)
    public void onLayoutClick(View view) {
        finishAfterTransition();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        ButterKnife.bind(this);

        int borderRadius = getResources().getDimensionPixelSize(R.dimen.dialog_border_radius);
        int fabColor = ContextCompat.getColor(this, R.color.green);
        int dialogColor = Color.WHITE;

        MorphCircleToRect toDialogTransition = new MorphCircleToRect(fabColor, dialogColor, borderRadius);
        toDialogTransition.setDuration(500);
        toDialogTransition.addTarget(mDialogLayout);
        MorphRectToCircle toFabTransition = new MorphRectToCircle(dialogColor, fabColor, borderRadius);
        toFabTransition.setDuration(500);
        toFabTransition.addTarget(mDialogLayout);

        getWindow().setSharedElementEnterTransition(toDialogTransition);
        getWindow().setSharedElementReturnTransition(toFabTransition);
    }
}
