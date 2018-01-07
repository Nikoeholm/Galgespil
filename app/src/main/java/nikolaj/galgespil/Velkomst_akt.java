package nikolaj.galgespil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Velkomst_akt extends AppCompatActivity implements OnClickListener {

        Button buttonstart, buttonregler, buttonindstillinger, buttonhighscorer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velkomst);

        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);

        buttonstart = (Button) findViewById(R.id.button_startspil);
        buttonstart.setOnClickListener(this);

        buttonregler = (Button) findViewById(R.id.button_regler);
        buttonregler.setOnClickListener(this);

        buttonindstillinger = (Button) findViewById(R.id.button_indstillinger);
        buttonindstillinger.setOnClickListener(this);

        buttonhighscorer = (Button) findViewById(R.id.button_highscore);
        buttonhighscorer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_startspil:
                Intent istart = new Intent(this, Spil_akt.class);
                startActivity(istart);
                break;

            case R.id.button_indstillinger:
                Intent indst = new Intent(this, Indstillinger_akt.class);
                startActivity(indst);
                break;

            case R.id.button_regler:
                Intent iregl = new Intent(this, Regler_akt.class);
                startActivity(iregl);
                break;

            case R.id.button_highscore:
                Intent ihigh = new Intent(this, Hichscore_akt.class);
                startActivity(ihigh);
                break;

            default:
                break;
        }
    }
}
