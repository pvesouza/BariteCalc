package com.example.pedro.calculadora;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NUMBER_MATERIALS = 4;
    private ArrayList<Resultado> listResults;
    private BaseAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_calc);
        //
        final Button calcular = findViewById(R.id.button_calcular);
        Button limpar = findViewById(R.id.button_limpar);
        final EditText number1 = findViewById(R.id.editText_number1);
        final EditText number2 = findViewById(R.id.editText_number2);
        final Blindagem shield = new Blindagem();
        final FormatLayout format = new FormatLayout(getApplicationContext());
        final LinearLayout layout1 = findViewById(R.id.layout1);
        listView = findViewById(R.id.list_results);
        listResults = new ArrayList<Resultado>();
        final Spinner spinerDose = findViewById(R.id.unidades_dose);
        final Spinner spinnerDistancia = findViewById(R.id.unidades_distancia);
        final Button share = findViewById(R.id.button_share);

        //Formata o Botão da maneira do App
        format.formatButton(limpar,getResources().getString(R.string.button_clean));
        format.formatButton(calcular,getResources().getString(R.string.button_calculate));
        format.setSpinner(spinerDose,R.array.dose_units);
        format.setSpinner(spinnerDistancia,R.array.distance_units);
        format.formatButton(share,getApplicationContext().getString(R.string.button_share_name));

        //Inicializa a Tabela de resultados
        printList();

        //Compartilhar os resultados
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String doseS = number1.getText().toString();
                String distance = number2.getText().toString();

                if (!doseS.equals("") && !distance.equals("")){

                    double dose = Double.parseDouble(doseS);
                    double range = Double.parseDouble(distance);
                    double material = 0.0;
                    String materialType = "";

                    //Testa se a lista de resultados não está vazia
                    if (listResults.size() != 0){
                        listResults.clear();
                    }
                    //Calcula para os 4 Tipos de materiais
                    for (int i = 0; i < NUMBER_MATERIALS; i++){


                        switch (i){
                            case 0:
                                material = Blindagem.TVL_BARITA;
                                materialType = getApplicationContext().getString(R.string.material_type_barite);
                                break;
                            case 1:
                                material = Blindagem.TVL_ACO;
                                materialType = getApplicationContext().getString(R.string.material_type_steal);
                                break;
                            case 2:
                                material = Blindagem.TVL_CHUMBO;
                                materialType = getApplicationContext().getString(R.string.material_type_plumb);
                                break;
                            case 3:
                                material = Blindagem.TVL_CONCRETO;
                                materialType = getApplicationContext().getString(R.string.material_type_concrete);
                                break;
                        }
                        //Seleciona as unidades corretas dos spinners
                        int positionDistancia = spinnerDistancia.getSelectedItemPosition();
                        int positionDose = spinerDose.getSelectedItemPosition();
                        //Determina qual o fator de conversão para a distância e para a dose
                        switch (positionDistancia){
                            //case 0: distância em metros
                            case 0:
                                shield.setFatorConversaoMetros(1.0);
                                break;
                            case 1:
                                //Valor em polegadas
                                shield.setFatorConversaoMetros(0.0254);
                                break;
                            case 2:
                                //Valor em Pés
                                shield.setFatorConversaoMetros(0.3048);
                                break;
                        }

                        //Configura o Fator de conversão para dose

                        switch (positionDose){
                            //Dose em Sivert
                            case 0:
                                shield.setFatorConversaoDose(1.0);
                                break;
                                //Dose em mSv
                            case 1:
                                shield.setFatorConversaoDose(1000);
                                break;
                            case 2:
                                //Dose em microSiverts
                                shield.setFatorConversaoDose(1000000);
                                break;
                        }


                        double thickness = shield.calculateThickness(range,Blindagem.FREE_ENVIRONMENT_DOSE,dose,material);
                        double thickness2 = shield.calculateThickness(range,Blindagem.CONTROLED_ENVIRONMENT_DOSE,dose,material);
                        Resultado result = new Resultado();
                        result.setThicknessControled(shield.formatNumber(thickness2));
                        result.setThicknessFree(shield.formatNumber(thickness));
                        result.setMaterialType(materialType);
                        listResults.add(result);
                    }

                    adapter = new MyBaseAdapter(getApplicationContext(),listResults);
                    listView.setAdapter(adapter);

                }else{
                    Toast.makeText(getApplicationContext(),"Insira dados",Toast.LENGTH_LONG).show();
                }
            }

        });

        //Reset dos dados da aplicação
        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number1.setText("");
                number2.setText("");
                printList();
            }
        });
    }

    private void printList(){
        ArrayList<Resultado> list = new ArrayList<Resultado>();
        String materialType = "";

        for (int i = 0; i < NUMBER_MATERIALS;i++){

            switch (i){
                case 0:
                    materialType = getApplicationContext().getString(R.string.material_type_barite);
                    break;
                case 1:
                    materialType = getApplicationContext().getString(R.string.material_type_steal);
                    break;
                case 2:
                    materialType = getApplicationContext().getString(R.string.material_type_plumb);
                    break;
                case 3:
                    materialType = getApplicationContext().getString(R.string.material_type_concrete);
                    break;
            }

            Resultado result = new Resultado();
            Blindagem shield = new Blindagem();
            result.setThicknessControled(shield.formatNumber(0.0));
            result.setThicknessFree(shield.formatNumber(0.0));
            result.setMaterialType(materialType);
            list.add(result);
        }

        MyBaseAdapter adapter = new MyBaseAdapter(getApplicationContext(),list);
        listView.setAdapter(adapter);
    }
}
