package com.xinlan.touchimage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {
    private SwipeRefreshLayout mSwiperefresh;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = (ImageView) findViewById(R.id.img);
        mSwiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        //mSwiperefresh.setProgressBackgroundColorSchemeColor(R.color.abc_primary_text_disable_only_material_light);
        mSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myUpdateOperation();
            }
        });
    }

    /**
     * 刷新
     */
    private void myUpdateOperation() {
        new MyTask().execute(1);//启动耗时任务
    }

    private static boolean isLan = false;

    private final class MyTask extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            isLan = !isLan;
            if (mSwiperefresh.isRefreshing()) {
                mSwiperefresh.setRefreshing(false);
            }//end if

            int imageId = isLan ? R.drawable.lan : R.drawable.ic_launcher;
            imgView.setImageResource(imageId);
        }
    }//end inner class

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_refresh) {
            mSwiperefresh.setRefreshing(true);
            myUpdateOperation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
