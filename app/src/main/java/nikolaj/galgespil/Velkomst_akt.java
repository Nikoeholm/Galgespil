package nikolaj.galgespil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Velkomst_akt extends AppCompatActivity implements OnClickListener {

    //Knapperne erklæres globalt så de kan tilgås i alle metoder.
    Button buttonstart, buttonregler, buttonindstillinger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velkomst);

        buttonstart = (Button) findViewById(R.id.button_startspil);
        buttonstart.setOnClickListener(this);

        buttonregler = (Button) findViewById(R.id.button_regler);
        buttonregler.setOnClickListener(this);

        buttonindstillinger = (Button) findViewById(R.id.button_indstillinger);
        buttonindstillinger.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == buttonstart) {
            Intent i = new Intent(this, Spil_akt.class);
            startActivity(i);
        }

        if (v == buttonregler) {
            Intent ir = new Intent(this, Regler_akt.class);
            startActivity(ir);
        }
        if (v == buttonindstillinger) {
            Intent in = new Intent(this, Indstillinger_akt.class);
            startActivity(in);
        }
    }
}
