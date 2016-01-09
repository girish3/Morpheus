package github.girish3.sample;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SampleActivity extends AppCompatActivity {

    @OnClick(R.id.fab)
    void onFabClick(View view) {
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(SampleActivity.this, view, getResources().getString(R.string.dialog_transition)).toBundle();
        Intent intent = new Intent(this, DialogActivity.class);
        startActivity(intent, bundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ButterKnife.bind(this);
    }
}
