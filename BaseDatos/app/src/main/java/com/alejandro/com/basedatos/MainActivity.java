package com.alejandro.com.basedatos;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ArrayList<Empresa> lista;
    Button Bregistrer,Blista;
    private int Tid=1;
    private String Tnombre;
    private String Turl;
    private String Ttelefono;
    private String Temail;
    private String Tproducto;
    private String Tclasificacion;
    //private EditText ETid;
    private EditText ETnombre;
    private EditText ETurl;
    private EditText ETtelefono;
    private EditText ETemail;
    private EditText ETproducto;
    private EditText ETclasificacion;

    public Empresa empresa = new Empresa();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bregistrer = (Button) findViewById(R.id.Bcrear);
        Bregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recolectorRegistro();
                if(validacion()==true){
                    System.out.println("Correct validation");
                    mensaje("Correct validation");
                    crearUsuario();
                    Intent browserIntent =
                            new Intent(MainActivity.this, MainActivity.class);
                    setResult(RESULT_OK, browserIntent);
                    startActivity(browserIntent);
                    finish();
                }
                else{
                    System.out.println("Error, is not correct");
                    mensaje("Error, is not correct");
                }
            }
        });
        Blista = (Button) findViewById(R.id.Blista);
        Blista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recolectorRegistro();
                lista=listarEmpresas();

            }
        });

    }

    public ArrayList<Empresa> listarEmpresas(){
        MyHandler dbHandler = new MyHandler(this,null,null,1);
        Empresa empresa = new Empresa();
        lista =new ArrayList<Empresa>();
        for(int i=0;i>10;i++){
            lista.add(dbHandler.findUserid(i));
        }
        return lista;
    }

    public void crearUsuario () {
        MyHandler dbHandler = new MyHandler(this, null, null, 1);
        Empresa empresa =
                new Empresa(Tid,Tnombre,Turl,Ttelefono,Temail,Tproducto,Tclasificacion);

        dbHandler.addEmpresa(empresa);
    }
    public void mensaje(String cadena){
        Toast.makeText(this, cadena, Toast.LENGTH_SHORT).show();
    }

    public boolean validacion(){
        if(Tnombre.equals("")||Turl.equals("")||Ttelefono.equals("")||Temail.equals("")||Tproducto.equals("") ||Tclasificacion.equals("")){
            mensaje("ERROR, Fill in missing fields ");
            return false;
        }
        else{
            mensaje("SEND, The send form is Completed");
            return true;
        }
    }

    public void recolectorRegistro(){
        //ETid = (EditText) findViewById(R.id.iduser);
        ETnombre = (EditText) findViewById(R.id.Nombre);
        ETurl = (EditText) findViewById(R.id.UrlEmpresa);
        ETtelefono = (EditText) findViewById(R.id.telephon);
        ETemail = (EditText) findViewById(R.id.Email);
        ETproducto = (EditText) findViewById(R.id.Productos);
        ETclasificacion = (EditText) findViewById(R.id.Clasificación);
        //Tid = Integer.parseInt(ETid.getText().toString());
        Tnombre = ETnombre.getText().toString();
        Turl = ETurl.getText().toString();
        Ttelefono = ETtelefono.getText().toString();
        Temail = ETemail.getText().toString();
        Tproducto = ETproducto.getText().toString();
        Tclasificacion = ETclasificacion.getText().toString();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
