package br.com.alura.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.modelo.Prova;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        List<String> topicosPort = Arrays.asList("Sujeito", "OBjeto Direto", "Pronomes", "Substantivos");
        Prova provaPortugues = new Prova("Portugues", "09/08/2017", topicosPort);

        List<String> topicosMat = Arrays.asList("Equações 2 g", "Trigonometria");
        Prova provaMetematica = new Prova("Matemática", "12/08/2017", topicosMat);

        List<Prova> provas = Arrays.asList(provaPortugues, provaMetematica);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(this, android.R.layout.simple_list_item_1, provas);

        ListView listaProvas = (ListView) findViewById(R.id.provas_lista);
        listaProvas.setAdapter(adapter);

        listaProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) parent.getItemAtPosition(position);
                Toast.makeText(ProvasActivity.this, "Clicou na prova de " + prova, Toast.LENGTH_SHORT).show();

                Intent vaiParaDetalhes = new Intent(ProvasActivity.this, DetalhesProvaActivity.class);

                vaiParaDetalhes.putExtra("prova", prova);
                startActivity(vaiParaDetalhes);
            }
        });

    }

}
