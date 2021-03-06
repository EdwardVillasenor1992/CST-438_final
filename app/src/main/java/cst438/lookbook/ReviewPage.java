package cst438.lookbook;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.Html;

public class ReviewPage extends AppCompatActivity{

    String passedID;
    ImageView mSignOut;
    ImageView ivCover;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_page);

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

        final TextView bookSummary = (TextView) findViewById(R.id.bookSummary);
        final TextView bookTitle = (TextView) findViewById(R.id.reviewPageTitle);
        final TextView bookAuthor = (TextView) findViewById(R.id.author);
        ivCover = (ImageView) findViewById(R.id.coverimage);

        //get book id from previous page
        passedID = getIntent().getStringExtra("idBook");

        String bookUrl = "https://www.googleapis.com/books/v1/volumes/" + passedID;

        //Toast.makeText(getApplicationContext(), "Book url:" + bookUrl, Toast.LENGTH_SHORT).show();

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
                        String name = "Title";
                        JSONArray industryIdentifiers;
                        JSONObject ident;
                        JSONObject imageLinks;
                        String imageUrl = null;
                        String authors = null;
                        String isbn10 = "no ISBN";
                        JSONObject jArry = null;
                        try {
                            jArry = jsonObj.getJSONObject("volumeInfo");
                            description = jArry.getString("description");
                            name = jArry.getString("title");
                            authors = jArry.getString("authors");
                            industryIdentifiers = jArry.getJSONArray("industryIdentifiers");
                            ident = industryIdentifiers.getJSONObject(0);
                            isbn10 = ident.getString("identifier");
                            imageLinks = jArry.getJSONObject("imageLinks");
                            imageUrl = imageLinks.getString("thumbnail");
                        } catch (JSONException e) {
                            description = "Could not get book summary";
                            e.printStackTrace();
                        }

                        String htmlTextStr = Html.fromHtml(description).toString();
                        bookTitle.setText(name);
                        bookAuthor.setText(authors);
                        loadImageFromUrl(imageUrl);
                        bookSummary.setText(htmlTextStr);
                        //Toast.makeText(getApplicationContext(), "Book isbn:" + isbn10, Toast.LENGTH_SHORT).show();
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

    private void loadImageFromUrl(String url){
        Picasso.with(this).load(url).placeholder(R.drawable.no_cover)
                .error(R.drawable.no_cover)
                .into(ivCover, new com.squareup.picasso.Callback(){

                    @Override
                    public void onSuccess(){

                    }

                    @Override
                    public void onError(){

                    }
                });
    }


}
