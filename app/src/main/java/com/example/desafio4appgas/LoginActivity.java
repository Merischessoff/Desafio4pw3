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

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout layoutEmail;
    private TextInputEditText txtEmail;
    private TextInputLayout layoutSenha;
    private TextInputEditText txtSenha;
    private Button btnLogar;
    private Button btnCadastrarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        layoutEmail = findViewById(R.id.layoutEmail);
        txtEmail = findViewById(R.id.txtEmail);
        layoutSenha = findViewById(R.id.layoutSenha);
        txtSenha = findViewById(R.id.txtSenha);
        btnLogar = findViewById(R.id.btnLogar);
        btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos()){
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    if(login()) {
                        startActivity(intent);
                    }else{
                        Snackbar snackbar = Snackbar.make(view, "Login incorreto! Possui cadastro? ", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }else{
                    Snackbar snackbar = Snackbar.make(view, "Informe um E-mail e uma senha ", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        btnCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean validarCampos(){
        if(txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Informe o seu e-mail");
            return false;
        }else{
            layoutEmail.setErrorEnabled(false);
        }

        if(txtSenha.getText().toString().isEmpty()){
            layoutSenha.setErrorEnabled(true);
            layoutSenha.setError("Informe a sua senha");
            return false;
        }else{
            layoutSenha.setErrorEnabled(false);
        }
        Log.d("validacao", "saiu no validar");
        return true;
    }

    private boolean login(){
        System.out.println(txtEmail.getText().toString());
        System.out.println(txtSenha.getText().toString());
        UsuarioRepository usuarioRepository = new UsuarioRepository(getBaseContext());
        Log.d("MainActivity", "método login()" + txtEmail.getText().toString() + " " + txtSenha.getText().toString());
        Usuario usuario = usuarioRepository.loginUsuario(txtEmail.getText().toString(), txtSenha.getText().toString());
        if(!usuario.getCpf().equalsIgnoreCase("")){
            return true;
        }else {
            return false;
        }
    }
}