package nikolaj.galgespil;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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
    Button spiligen;

    public Taber_frag() {
        // Required empty public constructor
        }

//    Galgelogik logik = new Galgelogik();
//    Spil_akt spil = new Spil_akt();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taber, container, false);
        Spil_akt spil = (Spil_akt) getActivity();
        String ord = spil.getLogik().getOrdet();

        taberBesked = (TextView) view.findViewById(R.id.taber_info);
        taberBesked.setText("Du har tabt! Ordet var:");

        ordet = (TextView) view.findViewById(R.id.taber_ordet);
        ordet.setText(ord);

        spiligen = view.findViewById(R.id.taber_spiligen);
        spiligen.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        getActivity().finish();
        startActivity(new Intent(getActivity().getApplicationContext(), Spil_akt.class));

    }
}
