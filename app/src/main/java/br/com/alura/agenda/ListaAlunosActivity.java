package br.com.alura.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        //String[] alunos = {"Daniel", "Jéssica", "Joyce", "Vinícius", "Pedro", "Vânia", "Giovanna", "Carlos", "WEb", "Teste", "Heliio"};
        String[] alunos = {"Daniel", "Jéssica", "Joyce", "Vinícius", "Pedro"};

        //CRIA A LIST VIEW DE ALIUOS
        ListView listaAlunos  = (ListView) findViewById(R.id.list_alunos);

        //CONVERTE AS STRINGS EM VIEWS
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);

        Button novoAluno = (Button) findViewById(R.id.novo_aluno);

        novoAluno.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

    }
}
