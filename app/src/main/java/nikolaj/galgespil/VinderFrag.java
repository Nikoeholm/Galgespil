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
public class VinderFrag extends Fragment implements View.OnClickListener {

    public boolean clicked = false;
    TextView vinderBesked, ordet;
    Button spiligen;

    public VinderFrag() {
        // Required empty public constructor
    }

    //Galgelogik logik = new Galgelogik();
    Spil spil = new Spil();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vinder, container, false);
        Spil spil = (Spil) getActivity();
        String ord = spil.getLogik().getOrdet();

        vinderBesked= (TextView) view.findViewById(R.id.vinder_info);
        vinderBesked.setText("Du har vundet! \nDu gættede ordet");

        ordet= (TextView) view.findViewById(R.id.vinder_ordet);
        ordet.setText(ord);


        spiligen = (Button) view.findViewById(R.id.vinder_spiligen);
        spiligen.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity().getApplicationContext(), Spil.class));


        }

    }

