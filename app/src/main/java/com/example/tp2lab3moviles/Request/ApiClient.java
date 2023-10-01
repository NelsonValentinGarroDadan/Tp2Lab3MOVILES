package com.example.tp2lab3moviles.Request;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.tp2lab3moviles.Models.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ApiClient {
    private static File Carpeta;

    private static File conectar(Context context){
        if(Carpeta==null){
            Carpeta = context.getFilesDir();
        }
        return Carpeta;
    }
    public static void guardar(Context context, Usuario usuario){
        File archivo = new File (conectar(context),"User.dat");
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(usuario);
            bos.flush();
            bos.close();

        }catch(FileNotFoundException e){
            Toast.makeText(context, "Error al guardar",Toast.LENGTH_LONG).show();
        }catch(IOException e){
            Toast.makeText(context, "Error E/S",Toast.LENGTH_LONG).show();
        }
    }
    public static Usuario leer(Context context){
        Usuario user = new Usuario("","","","","");
        File archivo = new File (conectar(context),"User.dat");
        try {
            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            user = (Usuario)ois.readObject();
            fis.close();

        }catch(FileNotFoundException e){
            Toast.makeText(context, "Error al guardar",Toast.LENGTH_LONG).show();
        }catch(IOException e){
            Toast.makeText(context, "Error E/S",Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "No es un Usuario",Toast.LENGTH_LONG).show();
        }
        return user;

    }
    public static Usuario login (Context context, String mail , String password){
        Usuario userPrefe = leer(context);
        Usuario user = null;
        if(userPrefe.getMail().equals(mail)&&userPrefe.getPassword().equals(password)){
            return userPrefe;
        }else{
            return user;
        }
    }
}
