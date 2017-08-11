package br.com.alura.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.modelo.Prova;

/**
 * Created by Vinicius on 11/08/2017.
 */

public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        List<String> topicosPort = Arrays.asList("Sujeito", "OBjeto Direto", "Pronomes", "Substantivos");
        Prova provaPortugues = new Prova("Portugues", "09/08/2017", topicosPort);

        List<String> topicosMat = Arrays.asList("Equações 2 g", "Trigonometria");
        Prova provaMetematica = new Prova("Matemática", "12/08/2017", topicosMat);

        List<Prova> provas = Arrays.asList(provaPortugues, provaMetematica);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(this, android.R.layout.simple_list_item_1, provas);

        ListView listaProvas = (ListView) view.findViewById(R.id.provas_lista);
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

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
