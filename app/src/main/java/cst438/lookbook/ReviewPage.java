package cst438.lookbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ReviewPage extends AppCompatActivity{

    String passedID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_page);

        //passedID = getIntent().getStringExtra("bookID");

        //Toast.makeText(getApplicationContext(), "ID of passed book: " + passedID, Toast.LENGTH_SHORT).show();
    }

}
