package com.example.user.candycoded2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    Candy[] candies;
    private CandyDBHelper candyDbGHelper = new CandyDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // to get certain text and set its value

        TextView textView = (TextView) this.findViewById(R.id.text_view_title);
        textView.setText(R.string.products_title);

        // add one item to our candies list view and the rest will be added using AsyncHttpClient
        // 1) create container in which we store the objects to be displayed    => c
        // 2) create an adapter that takes the container we just created     =>a = ..(...c)
        // 3) get id of the view we want to display the container, so that we can change its content     => v
        // 4) set the adapter of the view    => v.setAdapter(a)
        final ArrayList<String> candy_list = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_candy,R.id.text_view_candy,candy_list);
        ListView listView = (ListView) this.findViewById(R.id.list_view_candy);
        listView.setAdapter(adapter);

        //to display a msg when the app starts using toast

        final Context context =this;
        String text ="hello toast";
        final int duration= Toast.LENGTH_SHORT;
        final Toast toast =Toast.makeText(context,text,duration);
        toast.show();



        //create HttpClient through which we make HTTP requests  => client
        // this client makes get request
        // inside onSuccess method that returns a response(which is our json string)
        // 1) create json builder
        // 2) get individual json objects from the json string      => Candy[] candies = gson.fromJson(responseBody, Candy[].class)
        // 3) create list of candies, such that each candy is a json object  =>  Candy[] candies = gson.fromJson(responseBody, Candy[].class) (note:- candy properties must be the same of json keys)
        // finally add each candy to the adapter

        AsyncHttpClient client=new AsyncHttpClient();
        client.get("https://vast-brushlands-23089.herokuapp.com/main/api", new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody) {
                Log.i("AsyncHttpClient success", "response= " + responseBody);
                Gson gson = new GsonBuilder().create();
                candies = gson.fromJson(responseBody, Candy[].class);
                adapter.clear();
                for (Candy c : candies) {
                    adapter.add(c.name);
                }
                addCandiesToDatabase(candies);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {
                  Log.e("AsyncHttpClient failed", "response= " + responseBody);
            }
        });

        //to add action when an item is selected

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,int i,long l)
            {
                Intent intent= new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("candy_name",candies[i].name);
                intent.putExtra("candy_image", candies[i].image);
                intent.putExtra("candy_price",candies[i].price);
                intent.putExtra("candy_desc",candies[i].description);
                Log.d("MainActivity","candy image: "+candies[i].image);
                startActivity(intent);
            }
        });


    }

    private void addCandiesToDatabase(Candy[] candies)
    {
        SQLiteDatabase db = candyDbGHelper.getWritableDatabase();

        for (Candy c:candies)
        {
            ContentValues values=new ContentValues();
            values.put(CandyContract.CandyEntry.column_name_name,c.name);
            values.put(CandyContract.CandyEntry.column_name_price,c.price);
            values.put(CandyContract.CandyEntry.column_name_desc,c.description);
            values.put(CandyContract.CandyEntry.column_name_image,c.image);
            db.insert(CandyContract.CandyEntry.table_name,null,values);
        }



    }


}
