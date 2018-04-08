package cst438.lookbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewUser extends AppCompatActivity {
    private Button mSignUp;

    public void MY_FANCY_FUN(View v) {
        Toast.makeText(this, "WHO LET THE DOGS OUT?! WHO WHO WHO WHO!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);



    }
}
