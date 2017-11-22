package nikolaj.galgespil;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/* Highscoren gemmes i en SQLite databasen, og vises som et Listview. Bemærk at highscoren KUN gemmes
   hvis man har vundet, ved at gætte ordet.

   Dele af kodesekvensen er fra:
    https://github.com/FAS16/GalgespilFragmenteret_2/blob/master/app/src/main/java/com/example/fahadali/galgespilfragmenteret/HighscoreAktivitet.java
* */

public class Hichscore_akt extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView highscoreListe;
    ArrayList<Spiller> spillere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hichscorer_akt);

        highscoreListe = (ListView) findViewById(R.id.highscore_list);
        spillere = new ArrayList();
        fyldSpillerArray();

        System.out.println("Spillere med i spillet: "+spillere);

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.liste_element_layout, R.id.listeelem_navn, spillere){

            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);
                TextView rang = view.findViewById(R.id.listeelem_beskrivelse);
                rang.setText((position+1)+". pladsen -"+" antal forkerte: " + spillere.get(position).getScore());
                ImageView trofae = view.findViewById(R.id.listeelem_billede);

                switch (position){
                    case 0:
                        trofae.setImageResource(R.drawable.galge);
                        break;
                    case 1:
                        trofae.setImageResource(R.drawable.galge);
                        break;

                    case 2:
                        trofae.setImageResource(R.drawable.galge);
                        break;
                    default:
                        trofae.setImageResource(R.drawable.galge);
                        break;

                }

                return view;
            }

        };

        highscoreListe.setAdapter(adapter);
    }

    public void fyldSpillerArray() {
        HighscoreDB highscoreDB = new HighscoreDB(this);
        SQLiteDatabase db = highscoreDB.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM spillere ORDER BY score ASC;", null);

            while (cursor.moveToNext()) {

                spillere.add(new Spiller(cursor.getString(1), cursor.getInt(2)));
            }

            cursor.close();
            db.close();
            System.out.println(spillere.toString());

        }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}

