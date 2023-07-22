package com.example.individual_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


//904759848994-qpfcvvu0c9n7np8td7a7harl6j77v6hr.apps.googleusercontent.com
public class MainActivity extends AppCompatActivity {
    Button signOutButton;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private List<Model> list;
    private Adapter adapter;
    private RecyclerView recyclerView;
    LinearLayout errorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //then initialize adapter and recycleView

        recyclerView = findViewById(R.id.recycle);
        list = new ArrayList<>();
        adapter = new Adapter(this, list);

//then format the recycle view

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

//but also show progress, user to be patient while loading data from server

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait a seconds..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

//then receive data from php file, then response can be success or failure

        final StringRequest request = new StringRequest("http://172.20.10.3/androidnews/reports/news.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();

//make sure database exceptional are handled

                try {
                    JSONArray array = new JSONArray(response);

                    //now loop your incoming data

                    for (int loop = 0; loop < array.length(); loop++) {
                        JSONObject object = array.getJSONObject(loop);
                        list.add(new Model(object.getString("title"),
                                object.getString("reporter"),
                                object.getString("desc"),
                                object.getString("date")));
                    }
                    adapter.notifyDataSetChanged();

                }

//whatever Error happens, then throw here in catch block

                catch (Exception e) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Error code:" + e.getMessage());
                    alertDialog.show();
                }
            }

            //if response is failure give user the message

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                DisplayError("Error");
            }
        });
        Volley.newRequestQueue(this).add(request);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        signOutButton = findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }

        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
    }
    void DisplayError(String putError) {
        Snackbar.make(errorLayout, putError, Snackbar.LENGTH_LONG).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.reports) {
            // Handle profile menu item click
            Intent intent = new Intent(MainActivity.this, ReportActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.aboutus) {
            // Handle instruction menu item click
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void signOut() {
        // Sign out from Google Sign-In
        gsc.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // After signing out, navigate back to the login activity
                navigateToLoginActivity();
            }
        });
    }

    void navigateToLoginActivity() {
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
    }

    // ...
}

