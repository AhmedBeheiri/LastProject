package com.ahmed_beheiri.lastproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ahmed_beheiri.lastproject.Person;

import java.util.ArrayList;

/**
 * Created by ahmed_beheiri on 15/09/17.
 */

public class DBManager {
    private ProjectDatabase PDB;
    private ArrayList<Person> arrayList;
    private SQLiteDatabase database;

    public DBManager(Context context) {
        PDB = new ProjectDatabase(context);
        database = PDB.getWritableDatabase();


    }

    public void close() {
        if (database != null) {
            if (database.isOpen()) {
                database.close();
            }
        }
    }

    public Person Insert(Person person) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Name, person.getName());
        contentValues.put(Contract.Gender, person.getGender());
        contentValues.put(Contract.Type, person.getType());
        contentValues.put(Contract.Age, person.getAge());

        int personid = (int) database.insert(Contract.Tablename, null, contentValues);
        person.setId(personid);

        return person;
    }

    public ArrayList<Person> select(String Sortorder) {
        Cursor results = database.query(Contract.Tablename, new String[]{Contract.ID,
                        Contract.Name, Contract.Age, Contract.Gender, Contract.Type}, null, null, null
                , null, null);

        if (Sortorder.equals("Name")) {
            results = database.query(Contract.Tablename, new String[]{Contract.ID,
                            Contract.Name, Contract.Age, Contract.Gender, Contract.Type}, null, null, null
                    , null, Contract.Name);
        } else if (Sortorder.equals("Age")) {
            results = database.query(Contract.Tablename, new String[]{Contract.ID,
                            Contract.Name, Contract.Age, Contract.Gender, Contract.Type}, null, null, null
                    , null, Contract.Age);
        }
        arrayList = new ArrayList<>();
        if (results.getCount() > 0) {
            Person n;
            while (results.moveToNext()) {
                n = new Person(results.getString(results.getColumnIndex(Contract.Name)),
                        results.getString(results.getColumnIndex(Contract.Type)),
                        results.getString(results.getColumnIndex(Contract.Gender)),
                        results.getInt(results.getColumnIndex(Contract.Age)));
                n.setId((int) results.getInt(results.getColumnIndex(Contract.ID)));

                arrayList.add(n);

            }
            return arrayList;
        } else {
            return null;
        }

    }

    public ArrayList<Person> selectbyname(String Name) {
        Cursor results = database.query(Contract.Tablename, new String[]{Contract.ID, Contract.Name, Contract.Age, Contract.Gender, Contract.Type}
                , "name=?", new String[]{Name}, null, null, null);
        arrayList = new ArrayList<>();
        if (results.getCount() > 0) {
            Person n;
            while (results.moveToNext()) {
                n = new Person(results.getString(results.getColumnIndex(Contract.Name)),
                        results.getString(results.getColumnIndex(Contract.Type)),
                        results.getString(results.getColumnIndex(Contract.Gender)),
                        results.getInt(results.getColumnIndex(Contract.Age)));
                n.setId((int) results.getInt(results.getColumnIndex(Contract.ID)));

                arrayList.add(n);

            }
            return arrayList;
        } else {
            return null;
        }
    }

    public ArrayList<Person> selectbyID(int ID) {
        Cursor results = database.query(Contract.Tablename, new String[]{Contract.ID, Contract.Name, Contract.Age, Contract.Gender, Contract.Type}
                , "id=?", new String[]{String.valueOf(ID)}, null, null, null);
        arrayList = new ArrayList<>();
        if (results.getCount() > 0) {
            Person n;
            while (results.moveToNext()) {
                n = new Person(results.getString(results.getColumnIndex(Contract.Name)),
                        results.getString(results.getColumnIndex(Contract.Type)),
                        results.getString(results.getColumnIndex(Contract.Gender)),
                        results.getInt(results.getColumnIndex(Contract.Age)));
                n.setId((int) results.getInt(results.getColumnIndex(Contract.ID)));

                arrayList.add(n);

            }
            return arrayList;
        } else {
            return null;
        }
    }

    public int delete(int id) {
        int deleted = database.delete(Contract.Tablename, "id=?", new String[]{String.valueOf(id)});
        return deleted;
    }
}
