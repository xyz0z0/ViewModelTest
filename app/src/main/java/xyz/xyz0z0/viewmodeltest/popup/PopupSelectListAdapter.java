package xyz.xyz0z0.viewmodeltest.popup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import xyz.xyz0z0.viewmodeltest.R;

/**
 * created by chengxugang on 2019/11/15
 */
public class PopupSelectListAdapter<T> extends RecyclerView.Adapter<PopupSelectListAdapter.ViewHolder> {

    private List<T> data = new ArrayList<>();
    private OnListPopupClickListener listener;


    public PopupSelectListAdapter(List<T> list, OnListPopupClickListener listener) {
        this.data.clear();
        this.data.addAll(list);
        this.listener = listener;
    }

    public void setData(List<T> list){
        this.data.clear();
        this.data.addAll(list);
        notifyDataSetChanged();
    }


    @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_popup_select_list, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvContent.setText(data.get(i).toString());
        viewHolder.tvContent.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onClick(viewHolder.getAdapterPosition(), data.get(i).toString());
            }
        });
    }


    @Override public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;


        ViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
        }

    }
}
