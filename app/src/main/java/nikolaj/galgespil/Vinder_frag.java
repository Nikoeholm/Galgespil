package nikolaj.galgespil;


import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Vinder_frag extends Fragment implements View.OnClickListener {

    TextView vinderBesked, ordet, spilIgen;
    Button vælgOrd, tilfældigtOrd;
    Button afslut;
    String spilTag;
    Boolean DRord;

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

        vinderBesked = (TextView) view.findViewById(R.id.vinder_info);
        vinderBesked.setText("Du har vundet! \nDu gættede ordet");

        spilIgen = view.findViewById(R.id.spilIgen);
        spilIgen.setText("Spil igen");

        ordet = (TextView) view.findViewById(R.id.vinder_ordet);
        ordet.setText(ord);


        vælgOrd = (Button) view.findViewById(R.id.vinder_spiligen_liste);
        vælgOrd.setOnClickListener(this);

        tilfældigtOrd = (Button) view.findViewById(R.id.vinder_spiligen_tilfældigt);
        tilfældigtOrd.setOnClickListener(this);

        afslut = view.findViewById(R.id.afslut);
        afslut.setOnClickListener(this);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
        DRord = sharedPref.getBoolean("indstillinger_DR", false);

        return view;


    }

    @Override
    public void onClick(View view) {

        if(tilfældigtOrd == view){
            getActivity().finish();
            startActivity(new Intent(getActivity().getApplicationContext(), Spil_akt.class));
        }
        else if(vælgOrd == view){
            if(DRord == false) {
                getActivity().finish();
                startActivity(new Intent(getActivity().getApplicationContext(), ListView_akt.class));
            }
            else {
                getActivity().finish();
                startActivity(new Intent(getActivity().getApplicationContext(), Indstillinger_akt.class));
            }
        }
        else if (afslut == view){
            getActivity().finish();
        }
            }
}

