package cst438.lookbook;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewUser extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private EditText mEmail;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mConfirmPass;

    private String mUserString;
    private String mPassString;
    private String mEmailString;
    private String mFirstNameString;
    private String mLastNameString;
    private String mConfirmPassString;

    private Button mSignUpBtn;

    private LookBookDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        mFirstName = findViewById(R.id.new_user_fname);
        mLastName = findViewById(R.id.new_user_lname);
        mEmail = findViewById(R.id.new_user_email);
        mPassword = findViewById(R.id.new_user_password);
        mUsername = findViewById(R.id.new_user_username);
        mConfirmPass = findViewById(R.id.new_user_confirmPassword);

        mSignUpBtn = findViewById(R.id.new_user_signup);

        db = LookBookDB.getInstance(this);

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserString = mUsername.getText().toString();
                mPassString = mPassword.getText().toString();
                mEmailString = mEmail.getText().toString();
                mFirstNameString = mFirstName.getText().toString();
                mLastNameString = mLastName.getText().toString();
                mConfirmPassString = mConfirmPass.getText().toString();

                if(checkPassword(mPassString, mConfirmPassString))
                {
                    if(checkUsername(mUserString))
                    {
                        UserTable user = new UserTable(mFirstNameString,mLastNameString,mEmailString,
                                mUserString,mPassString);
                        db.userTableDao().addUser(user);

                        AlertDialog.Builder builder = new AlertDialog.Builder(NewUser.this);
                        builder.setTitle("1 new account");
                        builder.setMessage("your account has been created");
                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                finish();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "password not the same",
                            Toast.LENGTH_LONG).show();
                }


            }


            // method checks to make sure that both passwords are the same.
            public boolean checkPassword(String password, String confirmPassword)
            {
                if(password.equals(confirmPassword))
                    return true;
                else
                {
                    mConfirmPass.setError("passwords do not match");
                    return false;
                }
            }

            public boolean checkUsername(String username)
            {
                if(db.userTableDao().findUsersByUsername(username)==null)
                    return true;
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewUser.this);
                    builder.setTitle("Already in use");
                    builder.setMessage("Please try another username");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    return false;
                }


            }
        });






    }
}
