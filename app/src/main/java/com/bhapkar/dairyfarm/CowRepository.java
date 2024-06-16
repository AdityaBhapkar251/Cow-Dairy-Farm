package com.bhapkar.dairyfarm;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.bhapkar.dairyfarm.data.model.Cow;

import java.util.List;

public class CowRepository {

    private CowDao cowDao;
    private LiveData<List<Cow>> allCows;

    public CowRepository(Application application) {
        CowDatabase database = CowDatabase.getInstance(application);
        cowDao = database.cowDao();
        allCows = cowDao.getAllCows();
    }

//    public void insert(Cow cow) {
//        new InsertCowAsyncTask(cowDao).execute(cow);
//    }

    public LiveData<List<Cow>> getAllCows() {
        return allCows;
    }

    public LiveData<List<Cow>> getCowById(int id) {
        return cowDao.getCowById(id);
    }
    public void insert(Cow cow) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            cowDao.insert(cow);
        });
    }

    public void update(Cow cow) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            cowDao.update(cow);
        });
    }

    public void delete(Cow cow) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            cowDao.delete(cow);
        });
    }



//    public void update(Cow cow) {
//        new UpdateCowAsyncTask(cowDao).execute(cow);
//    }

//    public void delete(Cow cow) {
//        new DeleteCowAsyncTask(cowDao).execute(cow);
//    }


    private static class InsertCowAsyncTask extends AsyncTask<Cow, Void, Void> {
        private CowDao cowDao;

        private InsertCowAsyncTask(CowDao cowDao) {
            this.cowDao = cowDao;
        }

        @Override
        protected Void doInBackground(Cow... cows) {
            cowDao.insert(cows[0]);
            return null;
        }
    }
    private static class UpdateCowAsyncTask extends AsyncTask<Cow, Void, Void> {
        private CowDao cowDao;

        private UpdateCowAsyncTask(CowDao cowDao) {
            this.cowDao = cowDao;
        }

        @Override
        protected Void doInBackground(Cow... cows) {
            cowDao.update(cows[0]);
            return null;
        }
    }
    private static class DeleteCowAsyncTask extends AsyncTask<Cow, Void, Void> {
        private CowDao cowDao;

        private DeleteCowAsyncTask(CowDao cowDao) {
            this.cowDao = cowDao;
        }

        @Override
        protected Void doInBackground(Cow... cows) {
            cowDao.delete(cows[0]);
            return null;
        }
    }
}
