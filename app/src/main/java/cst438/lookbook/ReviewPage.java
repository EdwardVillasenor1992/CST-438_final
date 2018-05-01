package cst438.lookbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.Html;

public class ReviewPage extends AppCompatActivity{

    String passedID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_page);
        final TextView bookSummary = (TextView) findViewById(R.id.bookSummary);
        final TextView bookTitle = (TextView) findViewById(R.id.reviewPageTitle);
        final TextView bookReviews = (TextView) findViewById(R.id.bookReviews);

        //get book id from previous page
        passedID = getIntent().getStringExtra("idBook");

        String bookUrl = "https://www.googleapis.com/books/v1/volumes/" + passedID;

        Toast.makeText(getApplicationContext(), "Book url:" + bookUrl, Toast.LENGTH_SHORT).show();

        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest
        (
                Request.Method.GET, bookUrl,

                new Response.Listener<String>()
                {
                    JSONObject jsonObj;
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            jsonObj = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String description = null;
                        String name = "Book Summary";
                        JSONArray industryIdentifiers;
                        JSONObject ident;
                        String isbn10 = "no ISBN";
                        JSONObject jArry = null;
                        try {
                            jArry = jsonObj.getJSONObject("volumeInfo");
                            description = jArry.getString("description");
                            name = jArry.getString("title");
                            industryIdentifiers = jArry.getJSONArray("industryIdentifiers");
                            ident = industryIdentifiers.getJSONObject(0);
                            isbn10 = ident.getString("identifier");
                        } catch (JSONException e) {
                            description = "Could not get book summary";
                            e.printStackTrace();
                        }

                        bookTitle.setText(name);
                        bookSummary.setText(description);
                        Toast.makeText(getApplicationContext(), "Book isbn:" + isbn10, Toast.LENGTH_SHORT).show();
                        queue.stop();
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        bookSummary.setText("Error getting book summary");
                        error.printStackTrace();
                        queue.stop();
                    }
                }
        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}
