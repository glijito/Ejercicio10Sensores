package desarrollojhlibreros.com.ejercicio10sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int images[]={
            R.drawable.sensor1,
            R.drawable.sensosr2,
            R.drawable.sensor4,
            R.drawable.sensor5
        };

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        initRecyclerViewHelp(R.id.listaRecycler,sensorList,images,R.layout.viewlist);
    }

    public void initRecyclerViewHelp(int reciclerIdentificador, List<Sensor> sensors,int images[], int cardViewID ){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = findViewById(reciclerIdentificador);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ReciclerViewListsInicioAdapter(sensors,images,cardViewID,this));
    }

}
