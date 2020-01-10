package xyz.xyz0z0.viewmodeltest;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * created by chengxugang on 2020/1/10
 */
public class SharedViewModel extends ViewModel {

    private LiveData<String> name;

    public LiveData<String> getName() {
        Log.d("cxg","getName");
        if (name == null) {
            name = new MutableLiveData<String>();
            loadUsers();
        }
        return name;
    }

    private void loadUsers() {

    }

    private final MutableLiveData<Item> selected = new MutableLiveData<Item>();

    public void setSelected(Item item) {
        selected.setValue(item);
    }

    public MutableLiveData<Item> getSelected() {
        return selected;
    }

}
