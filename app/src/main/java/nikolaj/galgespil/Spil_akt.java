package nikolaj.galgespil;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Spil_akt extends Activity implements View.OnClickListener {

    //Variabler erklæres globalt så de kan bruges i hele klassen
    private TextView infotekst, besked, ordtype;
    private Button buttontjek;
    private ImageView img;

    // Der oprettes et objekt af klassen Galgelogik
    Galgelogik logik = new Galgelogik();

    public Galgelogik getLogik() {
        return logik;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        infotekst = (TextView) findViewById(R.id.textView_infotekst);
        besked = (TextView) findViewById(R.id.textView_besked);
        ordtype = (TextView) findViewById(R.id.textView_ordtype);

        buttontjek = (Button) findViewById(R.id.button_tjek);
        buttontjek.setOnClickListener(this);

        img = (ImageView) findViewById(R.id.image_galge);
        img.setImageResource(R.drawable.galge);


        ordtype.setText("Henter ord fra DRs server....");

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    logik.hentOrdFraDr();
                    return "Ordene blev korrekt hentet fra DR's server.";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Ordene blev ikke hentet korrekt.: "+e;
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                ordtype.setText("Status: \n" + resultat);
            }
        }.execute();


        LoadPreferences();
    }


    @Override
    public void onClick(View view) {
        System.out.println("ordet er:" + logik.getOrdet());
        logik.logStatus();
        switch (view.getId()) {

            case R.id.button_tjek:
                EditText et = (EditText) findViewById(R.id.editText_indtast);
                String bogstav = et.getText().toString();
                if (bogstav.length() != 1) {
                    et.setError("Skriv præcis ét bogstav");
                    return;
                }

                logik.gætBogstav(bogstav);
                et.setText("");
                et.setError(null);

                break;

            default:
                break;

        }
        opdaterSkærm();

    }


    public void opdaterSkærm() {

        FragmentManager fragmentmanager = getFragmentManager();
        FragmentTransaction fragmentTransmision = fragmentmanager.beginTransaction();

        LoadPreferences();

//        infotekst.setText("Mon du kan gætte ordet? \n" +
//                "Det består af " + logik.getOrdet().length() + " bogstaver! " +
//                "\n [ " + logik.getSynligtOrd() + " ]");

        besked.setText("Du har prøvet " + logik.getBrugteBogstaver().size() + " bogstaver:\n" + logik.getBrugteBogstaver());
        ordtype.setText("Antal forkerte bogstaver: " + logik.getAntalForkerteBogstaver());


        //Angivelse af billede efter antal forkerte.
        ImageView img = (ImageView) findViewById(R.id.image_galge);
        if (logik.getAntalForkerteBogstaver() == 0) {
            img.setImageResource(R.drawable.galge);
        } else if (logik.getAntalForkerteBogstaver() == 1) {
            img.setImageResource(R.drawable.forkert1);
        } else if (logik.getAntalForkerteBogstaver() == 2) {
            img.setImageResource(R.drawable.forkert2);
        } else if (logik.getAntalForkerteBogstaver() == 3) {
            img.setImageResource(R.drawable.forkert3);
        } else if (logik.getAntalForkerteBogstaver() == 4) {
            img.setImageResource(R.drawable.forkert4);
        } else if (logik.getAntalForkerteBogstaver() == 5) {
            img.setImageResource(R.drawable.forkert5);
        } else if (logik.getAntalForkerteBogstaver() == 6) {
            img.setImageResource(R.drawable.forkert6);
        }

        //Hvis spillet er vundet er vundet eller tabt, startes et fragment


        if (logik.erSpilletVundet()) {
            Vinder_frag vinderFrag = new Vinder_frag();
            fragmentTransmision.add(R.id.fragment, vinderFrag);
            fragmentTransmision.commit();
            findViewById(R.id.button_tjek).setVisibility(View.INVISIBLE);
        }

        if (logik.erSpilletTabt()) {
            Taber_frag taberFrag = new Taber_frag();
            fragmentTransmision.add(R.id.fragment, taberFrag);
            fragmentTransmision.commit();
            findViewById(R.id.button_tjek).setVisibility(View.INVISIBLE);
        }


    }
    private void LoadPreferences(){
        SharedPreferences sharedPref = getSharedPreferences("brugerinfo", Context.MODE_PRIVATE);
        String fornavn = sharedPref.getString("fornavn", "");
        String efternavn = sharedPref.getString("efternavn", "");
   //     boolean switchState = sharedPref.getBoolean("DRord", false);

        infotekst.setText("Mon du kan gætte ordet " + fornavn + " " + efternavn +"? \n" +
                "Det består af " + logik.getOrdet().length() + " bogstaver! " +
                "\n [ " + logik.getSynligtOrd() + " ]");

//        if(switchState == true){
//            System.out.println("Switch checked");
//
//        }
//        else if(switchState == false){
//            System.out.println("Switch not checked");
//        }
    }

}