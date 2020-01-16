package xyz.xyz0z0.viewmodeltest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

/**
 * created by chengxugang on 2020/1/10
 */
public class SharedViewModel extends ViewModel {

    private MutableLiveData<Item> selected = new MutableLiveData<>();
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> age = new MutableLiveData<>();
    private MediatorLiveData<String> person = new MediatorLiveData<>();

    public SharedViewModel() {
        person.addSource(name, new Observer<String>() {
            @Override public void onChanged(String s) {
                if (name.getValue() == null || age.getValue() == null) {
                    person.setValue("");
                } else {
                    person.setValue(name.getValue() + " == " + age.getValue());
                }
            }
        });
        person.addSource(age, new Observer<String>() {
            @Override public void onChanged(String s) {
                if (name.getValue() == null || age.getValue() == null) {
                    person.setValue("");
                } else {
                    person.setValue(name.getValue() + " == " + age.getValue());
                }
            }
        });
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(String value) {
        this.name.setValue(value);
    }

    public MutableLiveData<String> getAge() {
        return age;
    }

    public void setAge(String value) {
        this.age.setValue(value);
    }

    public MediatorLiveData<String> getPerson() {
        return person;
    }

    public MutableLiveData<Item> getSelected() {
        return selected;
    }

    public void setSelected(Item item) {
        selected.setValue(item);
    }

    public LiveData<Item> getLiveSelected() {
        return selected;
    }

}
