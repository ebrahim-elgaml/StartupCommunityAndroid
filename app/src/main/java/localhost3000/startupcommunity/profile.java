package localhost3000.startupcommunity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;


public class profile extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;


    ListView lv;
    Context context;
    TabHost mytabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("First Item");
        items.add("Second Item");
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String forecast = itemsAdapter.getItem(position);
                //Toast.makeText(getApplicationContext(), forecast, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class).putExtra(Intent.EXTRA_TEXT,forecast);

            }
        });
        mytabHost =(TabHost) findViewById(R.id.TabHost01);
        // Before adding tabs, it is imperative to call the method setup()
        mytabHost.setup();

        // Adding tabs
        // tab1 settings
        TabHost.TabSpec spec = mytabHost.newTabSpec("tab_creation");
        // text and image of tab
        spec.setIndicator("Create adresse",getResources().getDrawable(android.R.drawable.ic_menu_add));
        // specify layout of tab
        spec.setContent(R.id.onglet1);
        // adding tab in TabHost
        mytabHost.addTab(spec);

        // otherwise :
        mytabHost.addTab(mytabHost.newTabSpec("tab_inser").setIndicator("Delete",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.Onglet2));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
