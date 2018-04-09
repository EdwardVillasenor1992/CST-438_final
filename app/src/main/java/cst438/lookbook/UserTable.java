package cst438.lookbook;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "userTable")
public class UserTable {
    @PrimaryKey(autoGenerate = true)
    public int mId;

    @ColumnInfo(name = "first_Name")
    public String mFirstName;

    @ColumnInfo(name = "last_name")
    public String mLastName;

    @ColumnInfo(name = "email")
    public String mEmail;

    @ColumnInfo(name="username")
    public String mUsername;

    @ColumnInfo(name = "user_password")
    public String mPassword;


    public UserTable(int mId, String mFirstName, String mLastName, String mEmail, String mUsername, String mPassword) {
        this.mId = mId;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    @Override
    public String toString() {
        return "UserTable{" +
                "mId=" + mId +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mUsername='" + mUsername + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }
}
