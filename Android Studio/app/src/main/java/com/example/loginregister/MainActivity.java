package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextInputEditText textInputEditTextName,textInputEditTextPrice, textInputEditTextImage, textInputEditTextSell;
    Button btnAdd, btnDisplay, btnDisconnect, btnSell, btnBuy;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputEditTextName = findViewById(R.id.name);
        textInputEditTextPrice = findViewById(R.id.price);
        textInputEditTextImage = findViewById(R.id.image);
        textInputEditTextSell = findViewById(R.id.sell);
        btnAdd = findViewById(R.id.btnAdd);
        btnDisplay = findViewById(R.id.btnDisplay);
        btnBuy = findViewById(R.id.btnBuy);
        btnSell = findViewById(R.id.btnSell);
        btnDisconnect = findViewById(R.id.btnDisconnect);
        progressBar = findViewById(R.id.progress);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, price, image;
                name = String.valueOf(textInputEditTextName.getText());
                price = String.valueOf(textInputEditTextPrice.getText());
                image = String.valueOf(textInputEditTextImage.getText());

                if (!name.equals("") && !price.equals("") && !image.equals(""))
                {

                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler2 = new Handler(Looper.getMainLooper());
                    handler2.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[3];
                            field[0] = "name";
                            field[1] = "price";
                            field[2] = "image";
                            String[] data = new String[3];
                            data[0] = name;
                            data[1] = price;
                            data[2] = image;

//__________________________Faire conversion de price vers BTC et ETH
//__________________________Avec getResponseText qui renvoie prix Btc et eth en
//__________________________dollars et stocker ça dans les valeurs data price_btc & price_eth
//__________________________Avant qu'on envoie tout au putData

                            PutData putData = new PutData("http://192.168.1.24:9999/NFTchat/AddCat.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Added the Cat"))
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NFTCollection.class);
                startActivity(intent);
                finish();
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NFTBuy.class);
                startActivity(intent);
                finish();
            }
        });

        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private String getResponseText() throws IOException
    {
        StringBuilder response  = new StringBuilder();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
                        URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd%2Ceth");
        HttpsURLConnection https = (HttpsURLConnection)url.openConnection();

        if (https.getResponseCode() == HttpsURLConnection.HTTP_OK)
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(https.getInputStream()),8192);
            String strLine;
            while ((strLine = input.readLine()) != null)
            {
                response.append(strLine);
            }
            input.close();
        }
        return response.toString();
    }
}