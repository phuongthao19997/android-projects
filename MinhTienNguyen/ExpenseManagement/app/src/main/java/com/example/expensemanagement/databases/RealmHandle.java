package com.example.expensemanagement.databases;

import android.util.Log;

import com.example.expensemanagement.models.ExpenseModel;

import java.util.List;

import io.realm.Realm;

public class RealmHandle {
    private static final String TAG = "RealmHandle";
    Realm realm = Realm.getDefaultInstance();
    private static RealmHandle instance;
    public static RealmHandle getInstance(){
        if(instance == null){
            instance = new RealmHandle();
        }
        return instance;
    }

    public void addExpense(ExpenseModel expenseModel){
        realm.beginTransaction();
        realm.copyToRealm(expenseModel);
        realm.commitTransaction();
        Log.d(TAG, "addExpense: " + expenseModel);
    }

    public List<ExpenseModel> expenseModelList(){
        return realm.where(ExpenseModel.class).findAll();
    }

    public void deleteAllData(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }


}
