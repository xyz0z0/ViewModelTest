package xyz.xyz0z0.viewmodeltest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * created by chengxugang on 2020/1/10
 */
public class FragmentFirst extends Fragment {

    private SharedViewModel model;
    private TextView tvText1;
    private Item item;


    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        model.getSelected().observe(this, new Observer<Item>() {
            @Override public void onChanged(Item item) {
                Log.d("cxg", "First " + Thread.currentThread().getName());
                tvText1.setText(item.name);
                Toast.makeText(getActivity(), "First", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        tvText1 = view.findViewById(R.id.tv_text1);
        tvText1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                item = new Item("我是第一个页面的");
                model.getSelected().postValue(item);
            }
        });
        return view;
    }

}
