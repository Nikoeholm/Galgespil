package nikolaj.galgespil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Nikolaj on 04-01-2018.
 */

public class ListView_akt extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] ord = {"spil", "computer", "programmering", "android", "projekt", "galgespil", "smartphone", "versionering", "string", "layout"};

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, ord);

        ListView listView = new ListView(this);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        setContentView(listView);
    }

    public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
        Toast.makeText(this, "Ord valgt manuelt", Toast.LENGTH_SHORT).show();
        String item = (String) adapterView.getItemAtPosition(position);
        System.out.println("Valgt ord gemt i ListView_akt: " + item);


        Intent istart = new Intent(this, Spil_akt.class);
        istart.putExtra("ValgtOrd", item);
        startActivity(istart);
        finish();
    }

}

