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

    private Button btnAdd;
    private FrameLayout flFirst;
    private FrameLayout flSecond;
    private FragmentFirst fragmentFirst;
    private FragmentSecond fragmentSecond;

    private SharedViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        fragmentFirst = new FragmentFirst();
        fragmentSecond = new FragmentSecond();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_first, fragmentFirst).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_second, fragmentSecond).commit();
        model = ViewModelProviders.of(this).get(SharedViewModel.class);
        model.getName().observe(this, new Observer<String>() {
            @Override public void onChanged(String s) {
                Log.d("cxg", "s " + s);
                Log.d("cxg", "MainActivity " + Thread.currentThread().getName());
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override public void run() {
                        model.getSelected().postValue(new Item("杰克"));
                    }
                }).start();
            }
        });
    }

    private void findView() {
        btnAdd = findViewById(R.id.btn_add);
        flFirst = findViewById(R.id.fl_first);
        flSecond = findViewById(R.id.fl_second);
    }
}
