package com.example.pedro.calculadora;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyBaseAdapter extends BaseAdapter {

    private Context activity;
    private List<Resultado> listResults;

    public MyBaseAdapter (Context activity, ArrayList<Resultado> list){
        this.activity = activity;
        this.listResults = list;
    }

    @Override
    public int getCount() {
        return listResults.size();
    }

    @Override
    public Object getItem(int i) {

        Resultado resultado = listResults.get(i);
        return resultado;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1 = LayoutInflater.from(activity).inflate(R.layout.list_layout,viewGroup,false);
        Resultado result = listResults.get(i);
        TextView title = view1.findViewById(R.id.textView_title);
        title.setText(result.getMaterialType());
        TextView room = view1.findViewById(R.id.text_ambiente_controled);
        room.setText(Resultado.CONTROLED_ROOM);
        TextView room2 = view1.findViewById(R.id.text_ambiente_free);
        room2.setText(Resultado.FREE_ROOM);
        TextView thickControled = view1.findViewById(R.id.text_valor_controled);
        thickControled.setText(result.getThicknessControled());
        TextView thickFree = view1.findViewById(R.id.text_valor_free);
        thickFree.setText(result.getThicknessFree());
        return view1;
    }
}
