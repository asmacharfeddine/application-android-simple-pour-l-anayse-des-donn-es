package fsm.isi2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    //listEtudiants
    ArrayList listEtds = new ArrayList();
    //lvEtudiant : id=ListeEtudiants ds activitymain.xml
    ListView listeEtudiants;
    // 1 -instantiation de la classe CustomArrayAdapter :
    /*Associer CustomArrayAdapter à la ListView de MainActivity. Ensuite créer et associer à cette
     ListView les deux Listeners suivants*/
    CustomArrayAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnAjouter=findViewById(R.id.btnAjouter);
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FormulaireActivity.class);
                startActivityForResult(intent, 0);
            }
        });


    }

    protected void onActivityResult(int req, int result, @Nullable Intent d) {
        super.onActivityResult(req, result, d);

        if (result == RESULT_OK) {
            String nom = d.getStringExtra("nom");
            String prenom = d.getStringExtra("prenom");
            String formation = d.getStringExtra("formation");

            // on ajoute un vecteur car on a une liste de vecteurs: chaque vecteur contient nom, prenom  et formation (pas simplemenet un nom) sig objet
            Vector v1 = new Vector();
            v1.add( nom);
            v1.add( prenom);
            v1.add(formation );

            // CustomArrayAdapter car on va ajouter une image apres
            // 2 -Associer CustomArrayAdapter à la ListView de MainActivity
             adapter = new CustomArrayAdapter(this, listEtds, getLayoutInflater());

             // avoir notre listView à partir du fichier activity_main.xml
             listeEtudiants = findViewById(R.id.ListeEtudiants);
             // entrer l'adaptateur ds notre listview (adaptateur manipule la liste view)
            listeEtudiants.setAdapter(adapter);

            // ajout d'un item à notre listview
            listEtds.add(v1);
            //  pour mise à jour de l'adaptateur
            adapter.notifyDataSetChanged();

            //appui simple : question d
            listeEtudiants.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(MainActivity.this, ""+listEtds.get(position),
                            Toast.LENGTH_LONG).show();

                }

                           });

            // traitement de click sur un item pour etre affiché dans le list_row.xml graçe au customArrayAdapter
            listeEtudiants.setOnItemLongClickListener(
                    new AdapterView.OnItemLongClickListener(){
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view,
                                                       int position, long id) {
                            Toast.makeText(MainActivity.this, ""+listEtds.get(position),
                                    Toast.LENGTH_LONG).show();
                            //System.out.println("Long click : pos=" + position);

                            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                            final Vector itemValues = (Vector)
                                    listeEtudiants.getItemAtPosition(position);
                            // les valeurs de l'alerte
                            alert.setTitle((String) itemValues.get(0));
                            alert.setItems(new String[]{"Supprimer", "Annuler"},
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (which == 0) {
                                                //bouton Supprimer
                                                // tsupprimi mil adaptateur bch listeView yetsuprima minha
                                                System.out.println("Supprimer");
                                                listEtds.remove(position);
                                                adapter.notifyDataSetChanged();
                                            }
                                            else if (which == 1) {
                                                //bouton Detailles
                                                System.out.println("Annuler");
                                            }
                                        }
                                    });
                            alert.show();
                            return true;
                        }


                    });



} else  {
            Toast.makeText(this, "Operation annuler", Toast.LENGTH_LONG).show();
        }
    }
    // apres la fermeture de Oncreate
    // par defaut : Cette méthode est appelée par le système au moment de la création du
    //menu de l’activité.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.miNouveau:
                //Toast.makeText(this, "item +", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, FormulaireActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.miSupprimer:
                //Toast.makeText(this, "item -", Toast.LENGTH_LONG).show();
                SupprimerEtudiant();
                break;
            case R.id.miQuitter:
                finish();
                break;
        }
        return true;
    }

 void SupprimerEtudiant(){
        listEtds.removeAll(listEtds);
     listeEtudiants.setAdapter(adapter);



 }
}







