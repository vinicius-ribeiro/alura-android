package br.com.alura.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by Vinicius on 27/07/2017.
 */
public class FormularioHelper {

    private final EditText  campoNome;
    private final EditText  campoEndereco;
    private final EditText  campoTelefone;
    private final EditText  campoSite;
    private final RatingBar campoNota;
    private final ImageView campoFoto;
    private Aluno aluno;

    public FormularioHelper (FormularioActivity activity) {
        this.aluno         = new Aluno();
        this.campoNome     = (EditText) activity.findViewById(R.id.formulario_nome);
        this.campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        this.campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        this.campoSite     = (EditText) activity.findViewById(R.id.formulario_site);
        this.campoNota     = (RatingBar) activity.findViewById(R.id.formulario_nota);
        this.campoFoto     = (ImageView) activity.findViewById(R.id.foto_aluno_form);
    }

    public Aluno pegaAluno () {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
        aluno.setCaminhoFoto((String) campoFoto.getTag());
      return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEndereco.setText(aluno.getEndereco());
        campoSite.setText(aluno.getSite());
        campoNota.setRating(aluno.getNota().floatValue());
        carregaImagem(aluno.getCaminhoFoto());
        this.aluno = aluno;
    }

    public void carregaImagem(String caminhoDaFoto) {
        if(caminhoDaFoto!= null) {
            Bitmap bm = BitmapFactory.decodeFile(caminhoDaFoto);
            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
            campoFoto.setImageBitmap(bm);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoDaFoto);
        }
    }
}
