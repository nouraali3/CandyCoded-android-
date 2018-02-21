package com.example.user.candycoded2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=DetailActivity.this.getIntent();
        String candyName="", candyImage="", candyPrice="", candyDecs = "";

        if(intent!= null && intent.hasExtra("candy_name")){
            candyName=intent.getStringExtra("candy_name");
        }
        TextView tv=(TextView) this.findViewById(R.id.text_view_name);
        tv.setText(candyName);

        if(intent!= null && intent.hasExtra("candy_image")){
            candyImage=intent.getStringExtra("candy_image");
        }
//        ImageView iv = (ImageView) this.findViewById(R.id.image_view_candy);
        //todo : modify image view to be placeholder
//        Picasso.with(this).load(candyImage).into(iv);

        if(intent!= null && intent.hasExtra("candy_price")){
            candyPrice=intent.getStringExtra("candy_price");
        }
        tv=(TextView) this.findViewById(R.id.text_view_price);
        tv.setText(candyPrice);

        if(intent!= null && intent.hasExtra("candy_desc")){
            candyDecs=intent.getStringExtra("candy_desc");
        }
        tv=(TextView)this.findViewById(R.id.text_view_desc);
        tv.setText(candyDecs);

        Log.d("DetailActivity","intent data: "+candyImage+", "+candyPrice+", "+candyDecs);

    }
}
