package cst438.lookbook;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface UserTableDao {
    @Query("select count(*) from userTable")
    int count();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addUser(UserTable user);

    @Query("select * from userTable where first_Name = :first_Name")
    UserTable findUsersByFirstName(String first_Name);

    @Query("select * from userTable where last_name = :last_name")
    UserTable findUsersByLastName(String last_name);

    @Query("select * from userTable where email = :email")
    UserTable findUsersByEmail(String email);

    @Query("select * from userTable where username = :username")
    UserTable findUsersByUsername(String username);

}
