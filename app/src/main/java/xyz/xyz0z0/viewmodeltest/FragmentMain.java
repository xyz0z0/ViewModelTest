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
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.SimpleCallback;
import java.util.ArrayList;
import java.util.List;
import xyz.xyz0z0.viewmodeltest.popup.OnListPopupClickListener;
import xyz.xyz0z0.viewmodeltest.popup.OnListPopupHelpListener;

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
    private Button btnShowPopup;
    private Item itemData;


    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        model.getSelected().observe(this, new Observer<Item>() {
            @Override public void onChanged(Item item) {
                itemData = item;
                Log.d("cxg", "Main " + Thread.currentThread().getName());
                Toast.makeText(getActivity(), "Main", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void findView(View view) {
        btnAdd = view.findViewById(R.id.btn_add);
        flFirst = view.findViewById(R.id.fl_first);
        flSecond = view.findViewById(R.id.fl_second);
        btnShowPopup = view.findViewById(R.id.btn_show_popup);
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


    private void showPopup(View v) {
        String title = "测试弹窗";
        List<String> items = new ArrayList<>();
        items.add("测试1");
        ListPopupView popupView = new ListPopupView<String>(v.getContext())
            .setStringData(title, items)
            .setOnSelectListener(new OnListPopupClickListener() {
                @Override public void onClick(int position, String value) {

                }
            })
            .setOnHelpListener(new OnListPopupHelpListener() {
                @Override public void onRefreshHelpClick() {

                }
            });
        new XPopup.Builder(v.getContext())
            .setPopupCallback(new SimpleCallback() {
                @Override public void onCreated() {
                    super.onCreated();
                    v.postDelayed(new Runnable() {
                        @Override public void run() {
                            items.add("测试1");
                            items.add("测试2");
                            popupView.changeData("修改", items);
                        }
                    }, 2000);
                }
            })
            .asCustom(popupView)
            .show();

    }


    private void initView() {
        btnShowPopup.setOnClickListener(v -> {
            // showPopup(v);
            // Log.d("cxg", "item " + itemData.test);
            // Log.d("cxg", "item " + itemData.name);
            getActivity().getSupportFragmentManager().beginTransaction().hide(fragmentFirst).commit();
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override public void run() {
                        Log.d("cxg", "btnAdd");
                        getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(
                                R.anim.slide_right_in,
                                R.anim.slide_left_out,
                                R.anim.slide_left_in,
                                R.anim.slide_right_out
                            )
                            .add(R.id.fl_container, fragmentThird)
                            .addToBackStack("11").commit();
                    }
                }).start();
            }
        });
        fragmentFirst = new FragmentFirst();
        fragmentSecond = new FragmentSecond();
        fragmentThird = new FragmentThird();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fl_first, fragmentFirst).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fl_second, fragmentSecond).commit();

    }

}
