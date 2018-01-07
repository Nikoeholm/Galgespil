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
public class Taber_frag extends Fragment implements View.OnClickListener {

    TextView taberBesked, ordet;
    Button vælgOrd,tilfældigtOrd,afslut;
    Boolean DRord;

    public Taber_frag() {
        // Required empty public constructor
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taber, container, false);
        Spil_akt spil = (Spil_akt) getActivity();
        String ord = spil.getLogik().getOrdet();

        taberBesked = view.findViewById(R.id.taber_info);
        taberBesked.setText("Du har tabt! Ordet var:");

        ordet = view.findViewById(R.id.taber_ordet);
        ordet.setText(ord);

        vælgOrd = view.findViewById(R.id.taber_spiligen_liste);
        vælgOrd.setOnClickListener(this);

        tilfældigtOrd = view.findViewById(R.id.taber_spiligen_tilfældigt);
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
