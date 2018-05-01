package cst438.lookbook;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface WishListTableDao {
    @Query("select count(*) from wishListTable")
    int count();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addBook(WishListTable wishList);

    @Query("select * from wishListTable where Title = :title")
    UserTable findByTitle(String title);

    @Query("select * from wishListTable where Author = :author")
    UserTable findByAuthor(String author);

    @Query("select * from wishListTable where ISBN = :isbn")
    UserTable findByISBN(String isbn);

    @Delete
    void deleteItem(WishListTable wishList);

}
