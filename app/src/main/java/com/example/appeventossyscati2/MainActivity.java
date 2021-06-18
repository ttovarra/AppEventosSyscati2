package com.example.appeventossyscati2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnPrecios;
    private EditText ide, cliente, tipo, paquete, direccion, precio;

    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String DOS_PUNTOS = ":";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    //Widgets
    EditText fecha;
    ImageButton ibObtenerFecha;

    //Widgets
    EditText horas;
    ImageButton ibObtenerHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrecios = (Button)findViewById(R.id.btnPrecios);

        // Instanciar objetos
        ide = findViewById(R.id.txtIdEvento);
        cliente = findViewById(R.id.txtNombreCliente);
        tipo = findViewById(R.id.txtTipoEvento);
        paquete = findViewById(R.id.txtPaquete);
        direccion = findViewById(R.id.txtDireccion);
        precio = findViewById(R.id.txtPrecio);
        //Widget EditText donde se mostrara la fecha obtenida
        fecha = (EditText) findViewById(R.id.txtFecha);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        /*ibObtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);*/
        //Evento setOnClickListener - clic
        fecha.setOnClickListener(this);

        //Widget EditText donde se mostrara la hora obtenida
        horas = (EditText) findViewById(R.id.txtHora);
        //Widget ImageButton del cual usaremos el evento clic para obtener la hora
        /*ibObtenerHora = (ImageButton) findViewById(R.id.ib_obtener_hora);*/
        //Evento setOnClickListener - clic
        horas.setOnClickListener(this);
/*
        btnPrecios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent acciones, procesos
                Intent precios = new Intent(MainActivity.this,Precios.class);
                startActivity(precios);
            }
        });
*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtFecha:
                obtenerFecha();
                break;
        }

        switch (v.getId()){
            case R.id.txtHora:
                obtenerHora();
                break;
        }
    }

    //Inicia obtención de fecha
    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                fecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }
    //Termina Obtención de fecha

    //Inicia obtención de hora
    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                horas.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }
    //Termina obtención de hora

    //Inicia Alta
    public void altaEvento(View view) {
        AdminSQLiteOpenHelper admin2 = new AdminSQLiteOpenHelper(this, "administracion2", null, 2);
        SQLiteDatabase bd2 = admin2.getWritableDatabase();

        String Ide = ide.getText().toString();
        String Cliente = cliente.getText().toString();
        String Tipo = tipo.getText().toString();
        String Paquete = paquete.getText().toString();
        String Fecha = fecha.getText().toString();
        String Horas = horas.getText().toString();
        String Direccion = direccion.getText().toString();
        String Precio = precio.getText().toString();

        ContentValues registro2 = new ContentValues();
        registro2.put("cod", Ide);
        registro2.put("cliente", Cliente);
        registro2.put("tipo", Tipo);
        registro2.put("paquete", Paquete);
        registro2.put("fecha", Fecha);
        registro2.put("hora", Horas);
        registro2.put("direccion", Direccion);
        registro2.put("precio", Precio);

        bd2.insert("evento", null, registro2);
        bd2.close();

        ide.setText(null);
        cliente.setText(null);
        tipo.setText(null);
        paquete.setText(null);
        fecha.setText(null);
        horas.setText(null);
        direccion.setText(null);
        precio.setText(null);

        Toast.makeText(this, "Exito al ingresar evento "+Ide, Toast.LENGTH_LONG).show();
    }//termina Alta

    //Inicia Consulta
    public void consultaEvento(View view) {
        AdminSQLiteOpenHelper admin2 = new AdminSQLiteOpenHelper(this, "administracion2", null, 2);
        SQLiteDatabase bd2 = admin2.getWritableDatabase();

        String codigoConsulta2 = ide.getText().toString();

        Cursor fila2 = bd2.rawQuery("SELECT cliente, tipo, paquete, fecha, hora, direccion, precio FROM evento WHERE cod = "+codigoConsulta2, null);
        if (fila2.moveToFirst()) {
            cliente.setText(fila2.getString(0));
            tipo.setText(fila2.getString(1));
            paquete.setText(fila2.getString(2));
            fecha.setText(fila2.getString(3));
            horas.setText(fila2.getString(4));
            direccion.setText(fila2.getString(5));
            precio.setText(fila2.getString(6));
        } else {
            Toast.makeText(this, "Error al consultar evento "+codigoConsulta2, Toast.LENGTH_LONG).show();
        }
    }//termina Consulta

    //Inicia Actualización
    public void editarEvento(View view) {
        //genera objetos de base de datos
        AdminSQLiteOpenHelper admin2 = new AdminSQLiteOpenHelper(this, "administracion2", null, 2);
        SQLiteDatabase bd2 = admin2.getWritableDatabase();

        //para guardar los valores en variables objeto desde formulario XML
        String Ide = ide.getText().toString();
        String Cliente = cliente.getText().toString();
        String Tipo = tipo.getText().toString();
        String Paquete = paquete.getText().toString();
        String Fecha = fecha.getText().toString();
        String Horas = horas.getText().toString();
        String Direccion = direccion.getText().toString();
        String Precio = precio.getText().toString();

        //se crea contenedor para almacenar los valores
        ContentValues registro2 = new ContentValues();
        registro2.put("cod", Ide);
        registro2.put("cliente", Cliente);
        registro2.put("tipo", Tipo);
        registro2.put("paquete", Paquete);
        registro2.put("fecha", Fecha);
        registro2.put("hora", Horas);
        registro2.put("direccion", Direccion);
        registro2.put("precio", Precio);

        //instrucción para editar
        int cantidad2 = bd2.update("evento", registro2, "cod="+Ide, null);
        bd2.close();

        if (cantidad2 == 1) {
            Toast.makeText(this, "Evento actualizado",Toast.LENGTH_LONG).show();
            this.ide.setText("");
            this.cliente.setText("");
            this.tipo.setText("");
            this.paquete.setText("");
            this.fecha.setText("");
            this.horas.setText("");
            this.direccion.setText("");
            this.precio.setText("");
        } else {
            Toast.makeText(this, "No se efectuo ningún cambio en BD",Toast.LENGTH_LONG).show();
        }

    }//termina actualización

    //Inicia eliminiar
    public void eliminarEvento(View view) {
        AdminSQLiteOpenHelper admin2 = new AdminSQLiteOpenHelper(this, "administracion2", null, 2);
        SQLiteDatabase bd2 = admin2.getWritableDatabase();

        //Se asigna variable para busqueda y consulta por campo distintivo
        String codigoBaja2 = ide.getText().toString();

        //Se genera instrucción SQL para dar de baja un registro
        int confirmaBaja2 = bd2.delete("evento", "cod="+codigoBaja2,null);

        if (confirmaBaja2 == 1) {
            Toast.makeText(this, "Evento "+codigoBaja2+" eliminado.",Toast.LENGTH_LONG).show();
            this.ide.setText("");
            this.cliente.setText("");
            this.tipo.setText("");
            this.paquete.setText("");
            this.fecha.setText("");
            this.horas.setText("");
            this.direccion.setText("");
            this.precio.setText("");
        } else {
            Toast.makeText(this, "No existe evento "+codigoBaja2+" e la BD.",Toast.LENGTH_LONG).show();
        }

    }//Termina eliminar
}