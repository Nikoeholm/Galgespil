package nikolaj.galgespil;

        import android.content.Context;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class Indstillinger_akt extends AppCompatActivity {

    EditText fornavnInput, efternavnInput;
    TextView fuldeNavn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger_akt);

        fornavnInput = (EditText) findViewById(R.id.fornavnInput);
        efternavnInput = (EditText) findViewById(R.id.efternavnInput);
        fuldeNavn = (TextView) findViewById(R.id.fuldeNavn);

    }

    //Save the users info

    public  void saveinfo(View view){
        SharedPreferences sharedPref = getSharedPreferences("brugerinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("fornavn", fornavnInput.getText().toString());
        editor.putString("efternavn", efternavnInput.getText().toString());
        editor.apply();

        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
    }

    //Print out the saved data
    public void DisplayData(View view){
        SharedPreferences sharedPref = getSharedPreferences("brugerinfo", Context.MODE_PRIVATE);

        String name = sharedPref.getString("fornavn", "");
        String pw = sharedPref.getString("efternavn", "");

        fuldeNavn.setText("Gemt er: " + name +" " +  pw);

    }



}