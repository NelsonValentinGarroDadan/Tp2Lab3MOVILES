package com.example.tp2lab3moviles.ui.Registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tp2lab3moviles.Models.Usuario;
import com.example.tp2lab3moviles.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {
    private ActivityRegistroBinding binding;
    private RegistroActivityViewModel mv;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        intent = getIntent();
        mv.getMutable().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario user) {
                //setter editexts
                binding.dni.setText(user.getDNI());
                binding.nombre.setText(user.getNombre());
                binding.apellido.setText(user.getApellido());
                binding.mail.setText(user.getMail());
                binding.password.setText(user.getPassword());
            }
        });
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dni = binding.dni.getText().toString();
                String nombre = binding.nombre.getText().toString();
                String apellido = binding.apellido.getText().toString();
                String mail = binding.mail.getText().toString();
                String password = binding.password.getText().toString();
                Usuario user = new Usuario(dni,nombre,apellido,mail,password);
                mv.guardar(user);
            }
        });
        boolean bol = intent.getBooleanExtra("Boolean",false);
        mv.Registrarse(bol);

    }
}