package fsm.isi2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;


public class CustomArrayAdapter extends ArrayAdapter {

    ArrayList listValues;
    LayoutInflater inflater;
    public CustomArrayAdapter(Context context,
                              ArrayList listValues,
                              LayoutInflater inflater) {
        super(context, -1, listValues);
        this.listValues = listValues;
        this.inflater = inflater;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflater prend le layout à afficher pour la transformer en java (list_row)
        View view = inflater.inflate(R.layout.list_row, parent, false);
        //recupération des champs item1 et item2 du list_row.xml
        ImageView imgView = (ImageView)view.findViewById(R.id.imageView1);
        TextView item1 = (TextView) view.findViewById(R.id.NomPrenomTextView);
        TextView item2 = (TextView) view.findViewById(R.id.FormationTextView);
        // affectation de position de l'arraylist(listavlues) au vector qui va manipuler la liste
        Vector itemValues = (Vector) listValues.get(position);

        // affectation des positions au item de la liste à partir du vector qui va manipuler
        String nom, prenom, formation;
        nom = (String) itemValues.get(0);
        prenom = (String) itemValues.get(1);
        formation = (String) itemValues.get(2);

        // affectation des valeurs récupérés dans la liste_row.xml
        item1.setText(nom + " " + prenom);
        item2.setText(formation);
        imgView.setImageResource(R.mipmap.ic_launcher);
        return view;
    }
}