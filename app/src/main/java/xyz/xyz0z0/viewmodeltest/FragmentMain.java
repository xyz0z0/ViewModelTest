package xyz.xyz0z0.viewmodeltest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * created by chengxugang on 2020/1/10
 */
public class FragmentMain extends Fragment {

    private SharedViewModel model;
    private Button btnAdd;
    private FrameLayout flFirst;
    private FrameLayout flSecond;
    private FragmentFirst fragmentFirst;
    private FragmentSecond fragmentSecond;
    private FragmentThird fragmentThird;


    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        model.getSelected().observe(this, new Observer<Item>() {
            @Override public void onChanged(Item item) {
                Log.d("cxg", "Main " + Thread.currentThread().getName());
                Toast.makeText(getActivity(), "Main", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void findView(View view) {
        btnAdd = view.findViewById(R.id.btn_add);
        flFirst = view.findViewById(R.id.fl_first);
        flSecond = view.findViewById(R.id.fl_second);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        findView(view);
        initView();
        // tvText1 = view.findViewById(R.id.tv_text1);
        return view;
    }


    private void initView() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override public void run() {
                        Log.d("cxg", "btnAdd");
                        getFragmentManager().beginTransaction().add(R.id.fl_container, fragmentThird)
                            .addToBackStack("11").commit();
                    }
                }).start();
            }
        });
        fragmentFirst = new FragmentFirst();
        fragmentSecond = new FragmentSecond();
        fragmentThird = new FragmentThird();
        getFragmentManager().beginTransaction().add(R.id.fl_first, fragmentFirst).commit();
        getFragmentManager().beginTransaction().add(R.id.fl_second, fragmentSecond).commit();

    }

}
