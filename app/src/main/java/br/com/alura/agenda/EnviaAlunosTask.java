package br.com.alura.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.alura.agenda.converter.AlunoConverter;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by Vinicius on 07/08/2017.
 */

public class EnviaAlunosTask extends AsyncTask<Object, Object, String> {

    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunosTask (Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
         dialog = ProgressDialog.show(context, "Aguarde!", "Processando Alunos..", true, true);
    }

    @Override
    protected String doInBackground(Object... objects) {
        AlunoDAO dao       = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converteParaJSON(alunos);

        WebClient cliente = new WebClient();
        String resposta  = cliente.post(json);

        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) { //executado depois do doinbackground
        dialog.dismiss();
        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();
    }
}
