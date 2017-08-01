package br.com.alura.agenda;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.modelo.Aluno;

import static br.com.alura.agenda.R.id.foto_aluno_form;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    public static  final int CODIGO_CAMERA = 123;
    private String caminhoDaFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Button botaoCamera = (Button) findViewById(R.id.formulario_botao);
        botaoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreCamera    = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // invoca a acao de tirar a foto
                caminhoDaFoto        = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg"; // caminho. para subpastaas alterar null
                File arquivoFoto     = new File(caminhoDaFoto); //cria um file com o caminho da foto
                abreCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto)); // cria a intent passando o arquivo criado
                startActivityForResult(abreCamera, CODIGO_CAMERA); //abre a camera
            }
        });

        helper        = new FormularioHelper(this);
        Intent intent = getIntent();
        Aluno aluno   = (Aluno) intent.getSerializableExtra("aluno");

        if(aluno != null) {
            this.helper.preencheFormulario(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Aluno aluno  = helper.pegaAluno();
                AlunoDAO dao = new AlunoDAO(this);

                if (aluno.getId() != null) {
                    dao.altera(aluno);
                } else {
                    dao.insere(aluno);
                }
                dao.close();
                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
             break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODIGO_CAMERA) {
            if (resultCode == RESULT_OK) {
                Bitmap bm      = BitmapFactory.decodeFile(caminhoDaFoto);
                bm             = Bitmap.createScaledBitmap(bm, 100, 100, true);
                ImageView foto = (ImageView) findViewById(R.id.foto_aluno_form);
                foto.setImageBitmap(bm);
                foto.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }
    }
}