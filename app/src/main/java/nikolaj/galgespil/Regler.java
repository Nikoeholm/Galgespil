package nikolaj.galgespil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Regler extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regler);

        TextView tekstregler = (TextView) findViewById(R.id.textView_regler);
        tekstregler.setText("Du skal gætte det ord, som er skjult med stjerner *. " +
                "Du må gætte på ét bogstav af gangen.\n\n" +
                "Hvis ordet indeholder dit gæt, vises bogstavet " +
                "i det skjulte ord. Gætter du på et bogstav, som ikke er en del af ordet, tegnes " +
                "en kropsdel af en mand, der bliver hængt. \n\n" +
                "Du må gætte forkert 6 gange - derefter " +
                "bliver manden hængt, og spillet er tabt. Gættes alle bogstaver i ordet, inden manden" +
                "bliver hængt, er spillet vundet. God fornøjelse!");

    }
}
