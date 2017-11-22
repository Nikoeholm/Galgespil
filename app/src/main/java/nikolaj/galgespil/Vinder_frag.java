package nikolaj.galgespil;


import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Vinder_frag extends Fragment implements View.OnClickListener {

    TextView vinderBesked, ordet;
    Button spiligen;
    String spilTag;
    int antalForkerte;

    public Vinder_frag() {
        // Required empty public constructor
    }

    //Galgelogik logik = new Galgelogik();
   // Spil_akt spil = new Spil_akt();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vinder, container, false);
        Spil_akt spil = (Spil_akt) getActivity();
        String ord = spil.getLogik().getOrdet();

        vinderBesked= (TextView) view.findViewById(R.id.vinder_info);
        vinderBesked.setText("Du har vundet! \nDu gættede ordet");

        ordet= (TextView) view.findViewById(R.id.vinder_ordet);
        ordet.setText(ord);


        spiligen = (Button) view.findViewById(R.id.vinder_spiligen);
        spiligen.setOnClickListener(this);

        antalForkerte = getArguments().getInt("antalForkerte");

        return view;

    }


    @Override
    public void onClick(View view) {
          SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        spilTag = sharedPref.getString("indstillinger_spilTag", "");

        HighscoreDB highscoreDB = new HighscoreDB(getActivity());
        SQLiteDatabase db = highscoreDB.getWritableDatabase();

//SpilTag og antalforkerte indsættes i Highscoren.
        ContentValues række = new ContentValues();
        række.put(HighscoreDB.NAVN, spilTag );
        række.put(HighscoreDB.SCORE, antalForkerte);
        db.insert(HighscoreDB.TABLE, null, række);
        Toast.makeText(getActivity(), "Tilføjet til Highscore", Toast.LENGTH_LONG).show();

        db.close();

        getActivity().finish();
        startActivity(new Intent(getActivity().getApplicationContext(), Spil_akt.class));


        }

    }

