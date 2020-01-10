package xyz.xyz0z0.viewmodeltest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {



    private FragmentMain fragmentMain;
    private SharedViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentMain = new FragmentMain();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragmentMain).commit();
        model = ViewModelProviders.of(this).get(SharedViewModel.class);
        model.getName().observe(this, new Observer<String>() {
            @Override public void onChanged(String s) {
                Log.d("cxg", "s " + s);
                Log.d("cxg", "MainActivity " + Thread.currentThread().getName());
            }
        });

    }


}
