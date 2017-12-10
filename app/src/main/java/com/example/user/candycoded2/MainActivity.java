package com.example.user.candycoded2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) this.findViewById(R.id.text_view_title);
        textView.setText(R.string.products_title);
        ArrayList<String> candy_list = new ArrayList<>();
        candy_list.add("Tropical Wave");
        candy_list.add("Berry Bouncer");
        candy_list.add("Grape Gummer");
        candy_list.add("Apple of My Eye");
        candy_list.add("Much Minty");
        candy_list.add("So Fresh");
        candy_list.add("Sassy Sandwich Cookie");
        candy_list.add("Uni-pop");
        candy_list.add("Strawberry Surprise");
        candy_list.add("Wish Upon a Star");
        candy_list.add("Planetary Pops");
        candy_list.add("Watermelon Like Whoa");
        candy_list.add("Twist 'n' Shout");
        candy_list.add("Beary Squad Goals");
        candy_list.add("ROYGBIV Spinner");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,R.layout.list_item_candy,R.id.text_view_candy,candy_list);
        ListView listView = (ListView) this.findViewById(R.id.list_view_candy);
        listView.setAdapter(adapter);

        Context context =this;
        String text ="hello toast";
        final int duration= Toast.LENGTH_SHORT;
        final Toast toast =Toast.makeText(context,text,duration);
        toast.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,int i,long l)
            {
                Toast toast1=Toast.makeText(MainActivity.this,""+i,duration);
                toast1.show();
            }
    });

        AsyncHttpClient client=new AsyncHttpClient();
        client.get("https://vast-brushlands-23089.herokuapp.com/main/api", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
                    {
                        Log.e("AsyncHttpClient","response= "+ responseString);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString)
                    {
                        Log.d("AsyncHttpClient","response= "+responseString);
                    }
                }

        );


    }


}
