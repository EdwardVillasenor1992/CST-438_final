package cst438.lookbook;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchBookActivity extends Activity implements View.OnClickListener {
    static final String TAG = "MainActivity";
    private String name;
    private EditText usernameInput;

    private String password;
    private EditText passwordInput;

    private Button submitLoginButton;
    private Button mCreateAccountButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        usernameInput = (EditText) findViewById(R.id.username_input);
        passwordInput = (EditText) findViewById(R.id.password_input);

        submitLoginButton = (Button) findViewById(R.id.submit_login);
        submitLoginButton.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.submit_login:
                name = usernameInput.getText().toString();
                password = passwordInput.getText().toString();

                if(name.equals("") || password.equals(""))
                {
                    Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }
                else
                {


                    Toast.makeText(this, "Login under development", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Username: " + name + " Password: " + password, Toast.LENGTH_SHORT).show();


                    //_____________________________________________________________________________________
                    //passing values to next activity
                    //use:
                    // String name = getIntent().getStringExtra("username");
                    // String password = getIntent().getStringExtra("userPassword");
                    Intent intent = new Intent(getBaseContext(), SearchBookActivity.class);
                    intent.putExtra("username", name);
                    intent.putExtra("userPassword", password);
                    startActivity(intent);
                    //-----------------------------------------------------------------------------------
                }

                break;

        }


    }
}
