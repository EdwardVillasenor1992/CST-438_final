package cst438.lookbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ViewWishList extends AppCompatActivity {

    private LookBookDB db;
    private ListView mTaskListView;
    private ArrayAdapter<WishListTable> mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wish_list);

        db = LookBookDB.getInstance(this);

        mTaskListView = findViewById(R.id.list_view_users);
        viewUserList();
    }


    private void viewUserList() {
        List<WishListTable> userlist = db.wishListTableDao().viewList();

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<WishListTable>(this, R.layout.text_view_list, R.id.user_info, userlist);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(userlist);
            mAdapter.notifyDataSetChanged();
        }

    }
}
