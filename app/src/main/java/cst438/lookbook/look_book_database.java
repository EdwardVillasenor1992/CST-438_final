package cst438.lookbook;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Database(entities = {UserTable.class}, version = 1, exportSchema = false)
//@TypeConverters({DateConverter.class})

public abstract class look_book_database extends RoomDatabase {
    private static look_book_database slook_book_database;
    public abstract UserTableDao userTableDao();

    private static final Object sLock = new Object();

    public static look_book_database getInstance(Context context){
        if (slook_book_database == null) {
            slook_book_database = Room.databaseBuilder(context.getApplicationContext(),
                    look_book_database.class, "CSUMB_Library.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return slook_book_database;
    }

//    public void populateInitialData() {
//
//        if (userDao().count() == 0) {
//
//            beginTransaction();
//
//            //Calendar cal1 = new GregorianCalendar(1995, 5, 16);
//            String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
//
//
//            User user = new User("admin00!", "admin00!", "A", mydate);
//            User user1 = new User("asdfasdf1!", "asdfasdf1!", "U", mydate);
//            User user2 = new User("asdfasdf2@", "asdfasdf2@", "U", mydate);
//
//
//
//
//            Log.d("db", String.valueOf(user));
//
//            try{
//                long test = userDao().addUser(user);
//                userDao().addUser(user1);
//                userDao().addUser(user2);
//
//
//                Log.d("test", String.valueOf(test));
//                setTransactionSuccessful();
//
//            } finally {
//                endTransaction();
//            }
//        }
//    }


}
