package br.com.alura.agenda;

import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;
import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by Vinicius on 27/07/2017.
 */
public class FormularioHelper {

    private EditText  campoNome;
    private EditText  campoEndereco;
    private EditText  campoTelefone;
    private EditText  campoSite;
    private RatingBar campoNota;
    private Aluno aluno;

    public FormularioHelper (FormularioActivity activity) {
        this.aluno         = new Aluno();
        this.campoNome     = (EditText) activity.findViewById(R.id.formulario_nome);
        this.campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        this.campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        this.campoSite     = (EditText) activity.findViewById(R.id.formulario_site);
        this.campoNota     = (RatingBar) activity.findViewById(R.id.formulario_nota);
    }

    public Aluno pegaAluno () {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
      return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        this.aluno = aluno;
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEndereco.setText(aluno.getEndereco());
        campoSite.setText(aluno.getSite());
        campoNota.setRating(aluno.getNota().floatValue());
    }
}
