package xyz.xyz0z0.viewmodeltest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * created by chengxugang on 2020/1/10
 */
public class FragmentThird extends Fragment {

    private SharedViewModel model;
    private TextView tvText1;
    private Item itemData;


    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("cxg", "Third" + "onCreate" + System.currentTimeMillis());
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        model.getSelected().observe(this, new Observer<Item>() {
            @Override public void onChanged(Item item) {
                Log.d("cxg", "Third" + "onChanged" + System.currentTimeMillis());
                Log.d("cxg", "Third " + Thread.currentThread().getName());
                itemData = item;
                tvText1.setText(item.name);
                Toast.makeText(getActivity(), "Third", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("cxg", "Third" + "onViewCreated" + System.currentTimeMillis());
    }


    @Override public void onStart() {
        super.onStart();
        Log.d("cxg", "Third" + "onStart" + System.currentTimeMillis());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Log.d("cxg", "Third" + "onCreateView" + System.currentTimeMillis());
        tvText1 = view.findViewById(R.id.tv_text1);
        tvText1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                itemData.age = "Third";
            }
        });
        testValue();
        return view;
    }

    private void testValue() {
        Item item = model.getLiveSelected().getValue();
        if (item == null) {
            Log.d("cxg", "11null ");
        } else {
            Log.d("cxg", "11not null " + item.name);
        }
    }

    @Override public void onResume() {
        super.onResume();
        Log.d("cxg", "Third" + "onResume" + System.currentTimeMillis());
    }
}
