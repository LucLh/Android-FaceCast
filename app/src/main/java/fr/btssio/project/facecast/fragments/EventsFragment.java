package fr.btssio.project.facecast.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.btssio.project.facecast.MainActivity;
import fr.btssio.project.facecast.R;
import fr.btssio.project.facecast.adapters.EventAdapter;
import fr.btssio.project.facecast.objects.Event;
import fr.btssio.project.facecast.repository.EventRepository;

public class EventsFragment extends Fragment {

    public static String SERVER_URL;
    private String url;

    JSONObject jsonResponse;
    JSONArray arrayJson;
    private ArrayList<Event> items;
    private ListView lv;
    EventRepository eventRepo;


    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_events, container, false);

        eventRepo = new EventRepository(getActivity().getApplicationContext());
        url = eventRepo.getUrl().toString();

        lv = (ListView) v.findViewById(R.id.list);
        items = new ArrayList<Event>();

        return v;
    }

    public void onStart() {
        super.onStart();
        // Envoi d'une requete dans la file d'attente
        sendRequest();
    }

    private void sendRequest(){
        SERVER_URL = url+"/android/events";
        StringRequest stringRequest = new StringRequest(SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("reponse",""+response);
                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void parseJSON(String response){
        // Traitement de la source Json chargée dans onStart()
        try {
            jsonResponse = new JSONObject(response);
            // Création du tableau général à partir d'un JSONObject
            JSONArray jsonArray = jsonResponse.getJSONArray("event");
            Event currentEvent = null;

            // Pour chaque élément du tableau
            for (int i = 0; i < jsonArray.length(); i++) {
                currentEvent = new Event(null,null,null,null,null,null,null);

                // Création d'un tableau élément à  partir d'un JSONObject
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                // Récupération de l'item qui nous intéresse
                String id = jsonObj.getString("_id");
                String name = jsonObj.getString("name");
                String date = jsonObj.getString("date");
                String numberDays = jsonObj.getString("numberDays");
                String departement = jsonObj.getString("departement");
                String city = jsonObj.getString("city");
                String eventType = jsonObj.getString("_eventType");

                currentEvent.setId(id);
                currentEvent.setName(name);
                currentEvent.setDate(date);
                currentEvent.setNumberDays(numberDays);
                currentEvent.setDepartement(departement);
                currentEvent.setCity(city);
                currentEvent.setEventType(eventType);

                // Ajout dans l'ArrayList
                items.add(currentEvent);
            }

            ArrayAdapter<Event> objAdapter = new EventAdapter(getActivity(),R.layout.row, items);
            lv.setAdapter(objAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
