package fr.btssio.project.facecast.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class EventRepository extends Repository{

    // Constructeur
    public EventRepository(Context context) {
        super(context);
    }

    // Enregistre l'évènement dans les SharedPreferences
    public void setEvent(String event) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(Repository.context);
        Editor prefsEditor = appSharedPrefs.edit();

        prefsEditor.putString("EVENT",event);
        prefsEditor.commit();
    }

    public void setUrl(String url) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(Repository.context);
        Editor prefsEditor = appSharedPrefs.edit();

        prefsEditor.putString("URL",url);
        prefsEditor.commit();
    }

    // Supprime l'événement
    public void unsetEvent() {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(Repository.context);
        Editor prefsEditor = appSharedPrefs.edit();

        prefsEditor.remove("EVENT");
        prefsEditor.commit();
    }

    // Indique si un évènement est configuré ou non
    public boolean isEventConfigured() {
        EventRepository eventRepo = new EventRepository(Repository.context);
        String event = eventRepo.getEvent();

        if (event.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    // Récupére l'événement de l'utilisateur
    public String getEvent() {
        SharedPreferences app = PreferenceManager.getDefaultSharedPreferences(Repository.context);
        return app.getString("EVENT", "");
    }

    // Récupére l'url de l'utilisateur
    public String getUrl() {
        SharedPreferences app = PreferenceManager.getDefaultSharedPreferences(Repository.context);
        return app.getString("URL", "");
    }
}
