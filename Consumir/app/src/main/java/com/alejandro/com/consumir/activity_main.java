package com.alejandro.com.consumir;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class activity_main extends Activity {

    EditText gradosC;
    TextView gradosF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gradosC=(EditText) findViewById(R.id.editText1);
        gradosF=(TextView) findViewById(R.id.mostrar);

    }

    public void onclickInsertar(View v) {

        String NAMESPACE = "http://www.w3schools.com/webservices/";
        String URL = "http://www.w3schools.com/webservices/tempconvert.asmx?WSDL";
        String METHOD_NAME = "CelsiusToFahrenheit";
        String SOAP_ACTION = "http://www.w3schools.com/webservices/CelsiusToFahrenheit";
	/*Definir los parametros del webservice, son los datos que sacamos de la
	 * descripcion del servicio.*/

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("Celsius",gradosC.getText().toString());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

	/*Se crea el paquete SOAP a enviar con la petición al webservice. que no es mas que valor
	 * que queremos convertir a grados Fahrenheit en este ejemplo, de esto se encarga el metodo
	 * addProperty("<Propiedad>",<Valor>);
    NOTA: A la propiedad envelope.dotNet se le da un valor de “true” debido a que el servicio Web
    al cual nos queremos conectar esta codificado en .NET, no siempre es asi.
    */

        String respuesta;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject result=(SoapObject) envelope.getResponse();
            respuesta = result.getPropertyAsString(0);
            gradosF.setText("Grados Fahrenheit: "+respuesta);
        }
        catch(Exception e){
            e.printStackTrace();
        }

	/*se capturan la respuesta y se muestra en el TextView*/




    }
}
