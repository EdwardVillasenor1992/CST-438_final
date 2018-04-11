package cst438.lookbook;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BookListAdapter extends BaseAdapter{

    private Context mContext;
    private List<Book> mBookList;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.item_book_list, null);
        TextView tvTitle = (TextView)v.findViewById(R.id.title);
        TextView tvAuthor = (TextView)v.findViewById(R.id.author);
        tvTitle.setText(mBookList.get(i).getTitle());
        tvAuthor.setText(mBookList.get(i).getAuthor());
        v.setTag(mBookList.get(i).getId());
        return v;
    }
}