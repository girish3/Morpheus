package github.girish3.sample;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import github.girish3.morpheus.MorphCircleToRect;
import github.girish3.morpheus.MorphRectToCircle;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        int borderRadius = getResources().getDimensionPixelSize(R.dimen.dialog_border_radius);
        int fabColor = ContextCompat.getColor(this, R.color.colorAccent);
        int dialogColor = Color.WHITE;

        MorphCircleToRect toDialogTransition = new MorphCircleToRect(fabColor, dialogColor, borderRadius);
        MorphRectToCircle toFabTransition = new MorphRectToCircle(dialogColor, fabColor, borderRadius);

        getWindow().setSharedElementEnterTransition(toDialogTransition);
        getWindow().setSharedElementReturnTransition(toFabTransition);
    }
}
