package desarrollojhlibreros.com.ejercicio10sensores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;


/**
 * La clase ReciclerViewListsInicioAdapter es una objeto que nos ayuda a comunicar los datos
 * dinamicos(codificados) que se mostraran en pantalla del layout para obtener un control sobre la vista recycler view
 *
 *
 * La clase ReciclerViewListsInicioAdapter hereda de RecyclerView y recibira unicamente objetos de
 * tipo RecyclerView<>ReciclerViewListsInicioAdapter</> serializando los objetos que puedan entrar en el
 */


public class ReciclerViewListsInicioAdapter extends RecyclerView.Adapter<ReciclerViewListsInicioAdapter.ViewHolder>{

    /**
     *  Se declaran las variables que controlara el adaptador del ReciclerViewListsInicioAdapter
     *
     */
    private List<Sensor> sensors;
    private int imagens[] ;
    private int carviewerId;
    private Activity activityMainContainer;



    /**
     * Se inicialisa los parametos para el constructor de la clase
     * @param carviewerId recibe el identificador de las tarjetas a desplegar por el recyclerview
     */
    public ReciclerViewListsInicioAdapter(List<Sensor> sensors, int[] imagens, int carviewerId, Activity activityMainContainer) {
        this.sensors = sensors;
        this.imagens = imagens;
        this.carviewerId = carviewerId;
        this.activityMainContainer = activityMainContainer;
    }


    public ReciclerViewListsInicioAdapter(){
        this(null,null,0,null);
    }

    /**
     * @param parent recibe la instancia de la "actividad,fragmento o viewObject" en donde se inflara el cardview
     * @param viewType sin utilidad para este adaptador
     * @return class ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(carviewerId,parent,false);
        return new ViewHolder(view);
    }


    /**
     * @param holder ;se envia el objeto vista que controlaremos a traves del adaptador
     * @param position; si hay o existen muchos elementos, "position" nos permite identificar cada uno de ellos en
     *                base a un Array o un arrayList
     *               setOnClickListener nos permite interactuar con cada elemento creado
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        try{

            if(position>imagens.length)
                Glide.with(activityMainContainer).load(imagens[(int)Math.random()*4]).into(holder.imagen);
            else
                Glide.with(activityMainContainer).load(imagens[position]).into(holder.imagen);


            holder.sensor.setText(sensors.get(position).getType());
            holder.proveedor.setText(sensors.get(position).getName());
            holder.potencia.setText(String.valueOf(sensors.get(position).getPower()));
            holder.rango.setText(String.valueOf(sensors.get(position).getMaximumRange()));
            holder.resolucion.setText(String.valueOf(sensors.get(position).getResolution()));


            holder.imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(imagens[0]==v.findViewById(R.id.imagen).getId()){

                    }

                    if(imagens[2]==v.findViewById(R.id.imagen).getId()){
                        activityMainContainer.startActivity(new Intent(activityMainContainer,MiAceleradorActivity.class));
                    }

                }
            });
        }catch(Throwable throwable){
            Toast.makeText(activityMainContainer,"Error en la carga del layout: LIST INICIO:  "+throwable.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    /**
     * @return list.size() //tamaño de la lista(datos) a mostrar
     */
    @Override
    public int getItemCount() {
        return sensors.size();
    }
    /**
     * Creacion de la clase vista(View que controlaremos en el layout).
     * Se instancia en el constructor cada objeto(view) que contendra el padre (layout o view)
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView sensor;
        TextView proveedor;
        TextView potencia;
        TextView resolucion;
        TextView rango;

        public ViewHolder(View itemView){
            super(itemView);
            imagen=itemView.findViewById(R.id.imagen);
            sensor=itemView.findViewById(R.id.lbSensor);
            proveedor=itemView.findViewById(R.id.lbProveedor);
            potencia=itemView.findViewById(R.id.lbPotencia);
            resolucion=itemView.findViewById(R.id.lbresolucion);
            rango=itemView.findViewById(R.id.lbrango);
        }
    }

}
