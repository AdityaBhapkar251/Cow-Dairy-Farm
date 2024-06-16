package com.bhapkar.dairyfarm;// CowViewModel.java
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.bhapkar.dairyfarm.data.model.Cow;

import java.util.List;

public class CowViewModel extends AndroidViewModel {

    private CowRepository repository;
    private LiveData<List<Cow>> allCows;
    private MutableLiveData<Integer> cowIdLiveData = new MutableLiveData<>();
    private LiveData<Cow> cowLiveData;

    public CowViewModel(Application application) {
        super(application);
        repository = new CowRepository(application);
        allCows = repository.getAllCows();
        cowLiveData = Transformations.switchMap(cowIdLiveData, id -> repository.getCowById(id));
    }

    public void insert(Cow cow) {
        repository.insert(cow);
    }

    public void update(Cow cow) {
        repository.update(cow);
    }

    public void delete(Cow cow) {
        repository.delete(cow);
    }

    public LiveData<List<Cow>> getAllCows() {
        return allCows;
    }

    public void setCowId(int id) {
        cowIdLiveData.setValue(id);
    }

    public LiveData<Cow> getCowById() {
        return cowLiveData;
    }
}
