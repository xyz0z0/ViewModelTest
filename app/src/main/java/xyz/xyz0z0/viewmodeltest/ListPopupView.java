package xyz.xyz0z0.viewmodeltest;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.util.XPopupUtils;
import java.util.ArrayList;
import java.util.List;
import xyz.xyz0z0.viewmodeltest.popup.NoLastLineDecoration;
import xyz.xyz0z0.viewmodeltest.popup.OnListPopupClickListener;
import xyz.xyz0z0.viewmodeltest.popup.OnListPopupHelpListener;
import xyz.xyz0z0.viewmodeltest.popup.PopupSelectListAdapter;

public class ListPopupView<T> extends CenterPopupView {
    private RecyclerView recyclerView;
    private TextView tv_title;
    private String title;
    private ImageView ivHelp;
    private List<T> data;
    private PopupSelectListAdapter adapter;
    private OnListPopupClickListener listener;
    private OnListPopupHelpListener helpListener;

    private int helpIconVisibility = View.GONE;


    public ListPopupView(@NonNull Context context) {
        super(context);
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_picker_list;
    }


    @Override
    protected void initPopupContent() {
        super.initPopupContent();
        recyclerView = findViewById(R.id.recyclerView);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title);
        ivHelp = findViewById(R.id.iv_help);
        ivHelp.setVisibility(helpIconVisibility);
        ivHelp.setOnClickListener(v -> {
            new XPopup.Builder(getContext())
                .atView(v)
                .hasShadowBg(false)
                .asAttachList(new String[] { "数据不对，强制刷新" }, null,
                    new OnSelectListener() {
                        @Override
                        public void onSelect(int position, String text) {
                            if (position == 0) {
                                if (helpListener != null) {
                                    dismiss();
                                    helpListener.onRefreshHelpClick();
                                }
                            }
                        }
                    })
                .show();
        });
        adapter = new PopupSelectListAdapter<T>(data, new OnListPopupClickListener() {
            @Override public void onClick(int position, String value) {
                dismiss();
                if (listener != null) {
                    listener.onClick(position, value);
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new NoLastLineDecoration(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public ListPopupView setStringData(String title, List<T> data) {
        this.title = title;

        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        return this;
    }


    public ListPopupView changeData(String title, List<T> data) {
        this.title = title;
        if (tv_title != null) {
            tv_title.setText(title);
        }
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        if (adapter != null) {
            adapter.setData(data);
            adapter.notifyDataSetChanged();
        }
        return this;
    }


    public ListPopupView setOnSelectListener(OnListPopupClickListener onListPopupClickListener) {
        this.listener = onListPopupClickListener;
        return this;
    }


    public ListPopupView setOnHelpListener(OnListPopupHelpListener onHelpListener) {
        this.helpListener = onHelpListener;
        if (onHelpListener != null) {
            this.helpIconVisibility = View.VISIBLE;
        } else {
            this.helpIconVisibility = View.GONE;
        }
        return this;
    }


    @Override
    protected int getMaxWidth() {
        return popupInfo.maxWidth == 0 ? (int) (super.getMaxWidth() * .86f)
                                       : popupInfo.maxWidth;
    }


    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .7f);
    }
}
