package nikolaj.galgespil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Spil extends AppCompatActivity implements View.OnClickListener {

    //Variabler erklæres globalt så de kan bruges i hele klassen
    private TextView infotekst, besked;
    public boolean clicked = false;

    // Der oprettes et objekt af klassen Galgelogik
    Galgelogik logik = new Galgelogik();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        infotekst = (TextView) findViewById(R.id.textView_infotekst);
        infotekst.setText("Mon du kan gætte ordet? \n" +
                "Det består af " + logik.getOrdet().length() + " bogstaver! " +
                "\n [ " + logik.getSynligtOrd() + " ]");

        besked = (TextView) findViewById(R.id.textView_besked);


        Button buttontjek = (Button) findViewById(R.id.button_tjek);
        buttontjek.setOnClickListener(this);

        Button buttonspiligen = (Button) findViewById(R.id.button_spiligen);
        buttonspiligen.setOnClickListener(this);

        //Angiver det synlige ord i loggen
        Log.i("Ordet er: ", logik.getOrdet());

    }


    @Override
    public void onClick(View view) {
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

            case R.id.button_spiligen:
                clicked = true;
                break;

            default:
                break;

        }
        opdaterSkærm();

    }


    private void opdaterSkærm() {
        //Gør knappen 'Spil igen' usynlig.
        findViewById(R.id.button_spiligen).setVisibility(View.INVISIBLE);

        infotekst.setText("Mon du kan gætte ordet? \n" +
                "Det består af " + logik.getOrdet().length() + " bogstaver! " +
                "\n [ " + logik.getSynligtOrd() + " ]");

        besked.setText("Du har prøvet " + logik.getBrugteBogstaver().size() + " bogstaver:\n" + logik.getBrugteBogstaver());


        //Angivelse af billede efter antal forkerte.
        ImageView img = (ImageView) findViewById(R.id.imageView4);
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

        /*Hvis spillet er vundet; skrives en besked, 'spil igen' knap bliver synlig, og hvis den
        trykkes, nulstilles logik, og skærm opdateres.*/
        if (logik.erSpilletVundet()) {
            infotekst.setText("Du har vundet! Du gættede ordet: " + logik.getOrdet());
            findViewById(R.id.button_spiligen).setVisibility(View.VISIBLE);
            if (clicked == true) {
                logik.nulstil();
                opdaterSkærm();
            }
            clicked = false;
        }

        if (logik.erSpilletTabt()) {
            infotekst.setText("Du har tabt! Ordet var: " + logik.getOrdet());
            findViewById(R.id.button_spiligen).setVisibility(View.VISIBLE);
            if (clicked == true) {
                logik.nulstil();
                opdaterSkærm();
            }
            clicked = false;
        }
    }


}
