package com.example.pedro.calculadora;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class FormatLayout{

    private Context contextApp;
    private int backgroundButtonColor = Color.rgb(26,26,255);
    private int buttonTextColor = Color.rgb(255,255,255);
    private int textColor = Color.BLACK;
    private float textSize = 40.0f;
    private int marginButton = 10;


    public FormatLayout(Context context){
        contextApp = context;
    }

    //Formatar os componentes de acordo com o padrão do Layout
    public void formatButton (Button button, String name){

        button.setBackground(contextApp.getDrawable(R.drawable.style_button));
        button.setText(name);
        button.setTextColor(buttonTextColor);
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
        params.width = (contextApp.getResources().getDisplayMetrics().widthPixels)/3;
        params.height = ((contextApp.getResources().getDisplayMetrics().heightPixels)/4)/4;
        params.setMargins(marginButton,marginButton,marginButton,marginButton);
        button.setLayoutParams(params);

    }

    public void formatText(TextView textView){
        textView.setGravity(Gravity.CENTER);
        textView.setHeight((int) textSize);
        textView.setTextColor(textColor);
    }

    public void setSpinner (Spinner sp, int resource){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(contextApp,resource,R.layout.my_spinner);
        adapter.setDropDownViewResource(R.layout.my_spinner);
        sp.setAdapter(adapter);
    }
}
