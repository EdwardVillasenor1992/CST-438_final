package cst438.lookbook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookListAdapter extends BaseAdapter{

    private Context mContext;
    private List<Book> mBookList;
    private ImageView ivCover;
    private String idBook;
    private LookBookDB db;


    public BookListAdapter(Context mContext, List<Book> mBookList) {
        this.mContext = mContext;
        this.mBookList = mBookList;
    }

    @Override
    public int getCount() {
        return mBookList.size();
    }

    @Override
    public Object getItem(int i) {
        return mBookList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.item_book_list, null);
        TextView tvTitle = (TextView)v.findViewById(R.id.title);
        final TextView tvAuthor = (TextView)v.findViewById(R.id.author);
        ivCover = (ImageView)v.findViewById(R.id.coverimage);
        Button reviewButton = (Button)v.findViewById(R.id.review_button);
        Button infoButton = (Button)v.findViewById(R.id.book_info_button);
        Button mapButton = (Button)v.findViewById(R.id.location_button);
        Button addButton = v.findViewById(R.id.add_wishlist_button);
        tvTitle.setText(mBookList.get(i).getTitle());
        tvAuthor.setText(mBookList.get(i).getAuthor());
        loadImageFromUrl(mBookList.get(i).getImagelink());
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ReviewPage.class);
                intent.putExtra("idBook", mBookList.get(i).getApiId());
                mContext.startActivity(intent);
            }


        });
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, InfoPage.class);
                intent.putExtra("idBook", idBook);
                mContext.startActivity(intent);
            }


        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MapsActivity.class);
                intent.putExtra("idBook", idBook);
                mContext.startActivity(intent);
            }


        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
//                db = LookBookDB.getInstance(mContext);
//                WishListTable item = new WishListTable();
//                db.wishListTableDao().viewList();

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("1 item added");
                builder.setMessage("new item in wish list");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



            }
        });

        v.setTag(mBookList.get(i).getId());
        return v;
    }

    private void loadImageFromUrl(String url){
        Picasso.with(mContext).load(url).placeholder(R.drawable.no_cover)
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
