package com.example.vabil.resistorcalculator;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    Spinner band1Spinner;
    Spinner band2Spinner;
    Spinner band3Spinner;
    Spinner multiplierSpinner;
    Spinner toleranceSpinner;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapter2;
    ArrayAdapter<CharSequence> adapter3;
    TextView resistorValue;
    TextView textView2;
    Button button;
    int band1=0;
    int band2=0;
    int band3=0;
    double[] multiplierArray = {1,10,100,1000,10000,100000,1000000,10000000,0.1,0.01};
    int[] band1Array ={0,100,200,300,400,500,600,700,800,900};
    int[] band2Array = {0,10,20,30,40,50,60,70,80,90};
    int[] band3Array = {0,1,2,3,4,5,6,7,8,9};
    double value;
    int tolerancePosition;
    int oldPosition=1;
    public String[] toleranceValues = {"±1%","±2%","±0.5%","±0.25%","±0.10%","±0.05%","±5%","±10%"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById((R.id.button));
        resistorValue = (TextView) findViewById(R.id.resistor_value);
        textView2 = (TextView) findViewById(R.id.outputText);
        adapter = ArrayAdapter.createFromResource(this, R.array.resistorColours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.multiplier, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3 = ArrayAdapter.createFromResource(this, R.array.tolerance, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        band1Spinner = (Spinner) findViewById(R.id.band1);
        band1Spinner.setAdapter(adapter);
        band1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                band1 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        band2Spinner = (Spinner) findViewById(R.id.band2);
        band2Spinner.setAdapter(adapter);
        band2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                band2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        band3Spinner = (Spinner) findViewById(R.id.band3);
        band3Spinner.setAdapter(adapter);
        band3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                band3 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        multiplierSpinner = (Spinner) findViewById(R.id.multiplier);
        multiplierSpinner.setAdapter(adapter2);
        multiplierSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                oldPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        toleranceSpinner = (Spinner) findViewById(R.id.tolerance);
        toleranceSpinner.setAdapter(adapter3);
        toleranceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tolerancePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value=(band1Array[band1]+band2Array[band2]+band3Array[band3])*multiplierArray[oldPosition];
                String valueString = Double.toString(value);
                resistorValue.setText(valueString+" Ω");
                textView2.setText(toleranceValues[tolerancePosition]);

            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
