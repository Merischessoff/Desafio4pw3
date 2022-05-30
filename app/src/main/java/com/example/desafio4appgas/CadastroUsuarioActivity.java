package com.example.desafio4appgas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.desafio4appgas.dao.UsuarioRepository;
import com.example.desafio4appgas.model.Usuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private TextInputEditText txtEmail;
    private TextInputEditText txtSenha;
    private TextInputEditText txtCpf;
    private TextInputEditText txtEndereco;
    private TextInputEditText txtNome;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNome = findViewById(R.id.txtNome);
                txtCpf = findViewById(R.id.txtCpf);
                txtEndereco = findViewById(R.id.txtEnd);
                txtEmail = findViewById(R.id.txtEmail);
                txtSenha = findViewById(R.id.txtSenha);

                UsuarioRepository usuarioRepository = new UsuarioRepository(getBaseContext());
                String retorno = usuarioRepository.insert(txtNome.getText().toString(), txtCpf.getText().toString(), txtEndereco.getText().toString(), txtEmail.getText().toString(), txtSenha.getText().toString());

                Snackbar snackbar = Snackbar.make(view, retorno, Snackbar.LENGTH_LONG);
                snackbar.show();
                if(retorno.equalsIgnoreCase("Registro Inserido com sucesso")){
                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(view.getContext(), CadastroUsuarioActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}