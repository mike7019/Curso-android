package com.example.mike.proyecto1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView titulolbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        if(estalogueado()){
            Intent intent = new Intent(getApplicationContext(),NavigationActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        titulolbl = (TextView) findViewById(R.id.titulolbl);


        callbackManager =CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(getApplicationContext(),NavigationActivity.class);
                startActivity(intent);
            }

            @Override

            public void onCancel() {

                Toast.makeText(getApplicationContext(), "Cancelaste el login", Toast.LENGTH_LONG);
            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(getApplicationContext(),"Cancelaste el login",Toast.LENGTH_LONG);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private boolean estalogueado(){
        return AccessToken.getCurrentAccessToken()!=null;
    }


}
