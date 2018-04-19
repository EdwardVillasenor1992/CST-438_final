package cst438.lookbook;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SearchBookActivity extends android.support.v7.app.AppCompatActivity {
    static final String TAG = "MainActivity";
    private String name;
    private EditText usernameInput;

    private String password;
    private EditText passwordInput;

    private Button submitLoginButton;
    private Button mCreateAccountButton;

    private LookBookDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        //GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestEmail()
                //.build();



        getSupportActionBar().setDisplayOptions(android.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        db = LookBookDB.getInstance(this);
        db.populateInitialData();

        usernameInput = (EditText) findViewById(R.id.username_input);
        passwordInput = (EditText) findViewById(R.id.password_input);

        submitLoginButton = (Button) findViewById(R.id.submit_login);
        submitLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Starting search book activity");

                name = usernameInput.getText().toString();
                password = passwordInput.getText().toString();

                if(name.equals("") || password.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter username and password", Toast.LENGTH_SHORT).show();
                }
                else
                {


                    Toast.makeText(getApplicationContext(), "Login under development", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Username: " + name + " Password: " + password, Toast.LENGTH_SHORT).show();


                    //_____________________________________________________________________________________
                    //passing values to next activity
                    //use:
                    // String name = getIntent().getStringExtra("username");
                    // String password = getIntent().getStringExtra("userPassword");
                    Intent intent = new Intent(getBaseContext(), MainSearchBooks.class);
                    intent.putExtra("username", name);
                    intent.putExtra("userPassword", password);
                    startActivity(intent);
                    //-----------------------------------------------------------------------------------
                }
            }


        });

        mCreateAccountButton = (Button) findViewById(R.id.create_account);

        // create button take users to new page to create account
        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cypher = "";
                Log.d(TAG, "Button works");

                Intent intent = new Intent(SearchBookActivity.this,NewUser.class);
                startActivity(intent);
            }


        });
    }

}
