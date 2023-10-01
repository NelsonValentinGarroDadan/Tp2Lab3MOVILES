package com.example.tp2lab3moviles.ui.Registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2lab3moviles.Models.Usuario;
import com.example.tp2lab3moviles.Request.ApiClient;
import com.example.tp2lab3moviles.ui.Login.MainActivity;


public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData UsuarioM;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }
    public LiveData getMutable(){
        if(UsuarioM == null){
            UsuarioM = new MutableLiveData();
        }
        return UsuarioM;
    }
    public void Registrarse(boolean bol){
        Usuario user = new Usuario("","","","","");
        if(bol){
            user = ApiClient.leer(context);
        }

        UsuarioM.setValue(user);
    }
    public void guardar(Usuario usuario){
        //validar datos
        ApiClient.guardar(context,usuario);
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
