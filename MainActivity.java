package com.example.myfirstapp;

import android.Manifest;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

   Button btnGetLoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetLoc = (Button) findViewById(R.id.btnGetLoc);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        btnGetLoc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                GPStracker g = new GPStracker(getApplicationContext());
                Location l =g.getLocation();
                if(l != null){
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(),"LAT: "+lat+" \n LONG: "+lon,Toast.LENGTH_LONG).show();

                    Point point = new Point(lon,lat);

                    sendNetworkRequest(point);
                }

            }
        });

    }
    public void sendNetworkRequest(Point point){

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.1.102:4000/ninja")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        Api aapi= retrofit.create(Api.class);
        Call<Point> call = aapi.createPoint(point);

        call.enqueue(new Callback<Point>() {
            @Override
            public void onResponse(Call<Point> call, Response<Point> response) {
                Toast.makeText(MainActivity.this,"Yeah",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Point> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Nope",Toast.LENGTH_SHORT).show();
            }
        });

    }

   /* public void onButtonClick(View v){
        EditText e1 = (EditText)findViewById(R.id.editText);
        EditText e2 = (EditText)findViewById(R.id.editText2);
        TextView t1 = (TextView)findViewById(R.id.textView4);
        int num1 = Integer.parseInt(e1.getText().toString());
        int num2 = Integer.parseInt(e2.getText().toString());
        int sum = num1+num2;
        t1.setText(Integer.toString(sum));

    }*/
}
