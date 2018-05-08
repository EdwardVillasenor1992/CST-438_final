package cst438.lookbook;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class InfoPage extends AppCompatActivity{

   String passedID;
   String isbn10 = "no ISBN";
   String gurl ="https://www.bing.com";
   ImageView mSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_page);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout_sign_out);

        // Initialize all the view variables.
        mSignOut = (ImageView)findViewById(R.id.signout_icon);
        mSignOut.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchBookActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //final TextView bookTitle = (TextView) findViewById(R.id.goodReadsBookTitle);
        final WebView goodReadsPage = (WebView) findViewById(R.id.webView);

        //get book id from previous page
        passedID = getIntent().getStringExtra("idBook");
        String bookUrl = "https://www.googleapis.com/books/v1/volumes/" + passedID;

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
                        JSONObject jArry = null;
                        try {
                            jArry = jsonObj.getJSONObject("volumeInfo");
                            name = jArry.getString("title");
                            industryIdentifiers = jArry.getJSONArray("industryIdentifiers");
                            ident = industryIdentifiers.getJSONObject(0);
                            isbn10 = ident.getString("identifier");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //bookTitle.setText(name);
                        String url = "https://www.goodreads.com/api/reviews_widget_iframe?did=bOCKTwOhONI3aygQf2zdqw&amp;format=html&amp;isbn=" + isbn10 + "&amp;links=660&amp;min_rating=&amp;review_back=fff&amp;stars=000&amp;text=000";
                        //String url ="https://www.goodreads.com/api/reviews_widget_iframe?did=DEVELOPER_ID&amp;format=html&amp;isbn=1781100217&amp;links=660&amp;min_rating=&amp;review_back=fff&amp;stars=000&amp;text=000";
                        gurl = "https://www.google.com";
                        goodReadsPage.loadUrl(url);
                        queue.stop();
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        error.printStackTrace();
                        queue.stop();
                    }
                }
        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}
