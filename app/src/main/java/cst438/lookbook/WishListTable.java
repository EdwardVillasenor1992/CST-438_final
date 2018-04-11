package cst438.lookbook;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "wishListTable")
public class WishListTable {

    @PrimaryKey(autoGenerate = true)
    public int mId;

    @ColumnInfo(name = "Author")
    public String mAuthor;

    @ColumnInfo(name = "ISBN")
    public String mISBN;

    @ColumnInfo(name = "Title")
    public String mTitle;

    public WishListTable(String mAuthor, String mISBN, String mTitle) {
        this.mAuthor = mAuthor;
        this.mISBN = mISBN;
        this.mTitle = mTitle;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmISBN() {
        return mISBN;
    }

    public void setmISBN(String mISBN) {
        this.mISBN = mISBN;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
