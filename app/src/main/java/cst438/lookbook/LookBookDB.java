package cst438.lookbook;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import java.util.Calendar;

@Database(entities = {UserTable.class}, version = 1, exportSchema = false)
//@TypeConverters({DateConverter.class})
public abstract class LookBookDB extends RoomDatabase {
    private static LookBookDB sLookBookDB;
    public abstract UserTableDao userTableDao ();

    private static final Object sLock = new Object();

    public static LookBookDB getInstance(Context context){
        if (sLookBookDB == null) {
            sLookBookDB = Room.databaseBuilder(context.getApplicationContext(),
                    LookBookDB.class, "LookBook_Library.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sLookBookDB;
    }

    public void populateInitialData() {

        if (userTableDao().count() == 0) {

            beginTransaction();

            String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


            UserTable user = new UserTable("F_test", "L_test", "test@gmail.com",
                                            "testtest","test1");



            Log.d("db", String.valueOf(user));

            try{
                long test = userTableDao().addUser(user);
//                userDao().addUser(user1);
//                userDao().addUser(user2);


                Log.d("test", String.valueOf(test));
                setTransactionSuccessful();

            } finally {
                endTransaction();
            }
        }
    }

}
