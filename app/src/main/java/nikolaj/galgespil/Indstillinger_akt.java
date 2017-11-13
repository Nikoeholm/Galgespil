package nikolaj.galgespil;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class Indstillinger_akt extends PreferenceActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Der laves en container, som udskiftes med XML filen (PreferencenScreen)
        getFragmentManager().beginTransaction().replace(android.R.id.content, new Indstillinger_Preference()).commit();
    }

    public static class Indstillinger_Preference extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.activity_indstillinger);
        }
    }

}