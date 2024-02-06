package fsm.isi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FormulaireActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        //Intent intent = getIntent() ;

        Button btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operationOk();
            }
        });

        Button Annuler = findViewById(R.id.btnAnnuler);
        Annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent();
               operationAnnuler();

            }
        });



    }

    void operationOk( ) {
        Intent intent = new Intent() ; // pour sauvegarder les données
        //TextView Titre = findViewById(R.id.TitreConf);
        //String d = Titre.getText().toString();
       // intent.putExtra("titre", d);
        EditText tnom = findViewById(R.id.NomEE);
        String nom = tnom.getText().toString();
        EditText tprenom = findViewById(R.id.prenomEE);
        String prenom = tprenom.getText().toString();
        EditText tformatiom = findViewById(R.id.formationEE);
        String formatiom = tformatiom.getText().toString();
        intent.putExtra("nom",nom);
        intent.putExtra("prenom",prenom);
        intent.putExtra("formation",formatiom);
        setResult(RESULT_OK, intent);
        // pour retourner à l'activité précedante: dans notre cas c'est l'activity main
        finish();
    }

    void operationAnnuler() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

}