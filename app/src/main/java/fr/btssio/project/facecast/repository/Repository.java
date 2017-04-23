package fr.btssio.project.facecast.repository;

import android.content.Context;

// Classe de base gardant le contexte de l'application
public abstract class Repository {
    protected static Context context = null;

    // Constructeur avec en paramètre le contexte de l'application
    public Repository(Context context) {
        if (Repository.context == null) {
            Repository.context = context;
        }
    }
}
