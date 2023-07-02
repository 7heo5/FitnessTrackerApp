package com.example.fitnesstrackerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fitnesstrackerapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    Button b1, b2;
    EditText ed1, ed2;
    int counter = 3;
    private NavController navController;

    public static DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            // Initialize the database
            dbHelper = new DataHelper(getApplicationContext());

//Insert DataHelper Test Data
            dbHelper.Insert("Home", "Party");
            dbHelper.Insert("Dashboard", "Boring");
            dbHelper.Insert("Notifications", "Urgent");

//redirecting to the new login page
            setContentView(R.layout.login);

//setup the button to use the listener, and send to the new method
            b1 = (Button) findViewById(R.id.button1);
            b1.setOnClickListener(this::onClick);

            //find the inputs for the click event
            ed1 = (EditText) findViewById(R.id.editText1);
            ed2 = (EditText) findViewById(R.id.editText2);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    @Override
    public void onClick(View v)
    {
        try {
            // this is cryptographically insecure, and not recommended
            if (ed1.getText().toString().equals("admin") && ed2.getText().toString().equals("admin"))
            {
//if username and password are accepted
                Toast.makeText(getApplicationContext(),
                        "Redirecting...", Toast.LENGTH_SHORT).show();
                binding = ActivityMainBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());
                BottomNavigationView navView = findViewById(R.id.nav_view);
                // Passing each menu ID as a set of Ids because each
                // menu should be considered as top level destinations.
                AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                        .build();
                navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
                NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
                NavigationUI.setupWithNavController(binding.navView, navController);
                return;

            } else {
                Toast.makeText(getApplicationContext(), "Wrong credentials, " + (counter-1) + " tries left", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(), "System error, " + (counter-1) + " tries left", Toast.LENGTH_SHORT).show();
        }
//disable button after 3 attempts for better security
        counter--;
        if (counter <= 0) {
            b1.setEnabled(false);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.navigation_home){
            navController.navigate(R.id.navigation_home);
        }

        if(item.getItemId() == R.id.navigation_dashboard){
            navController.navigate(R.id.navigation_dashboard);
        }

        if(item.getItemId() == R.id.navigation_notifications){
            navController.navigate(R.id.navigation_notifications);
        }

        return super.onOptionsItemSelected(item);
    }
}
