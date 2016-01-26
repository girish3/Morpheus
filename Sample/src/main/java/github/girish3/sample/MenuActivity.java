package github.girish3.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void openBasicMorph(View view) {
        Intent intent = new Intent(this, BasicMorphActivity.class);
        startActivity(intent);
    }

    public void openDialogTransition(View view) {
        Intent intent = new Intent(this, DialogTransitionActivity.class);
        startActivity(intent);
    }

    public void openMenuTransition(View view) {
        Intent intent = new Intent(this, MenuTransitionActivity.class);
        startActivity(intent);
    }
}
