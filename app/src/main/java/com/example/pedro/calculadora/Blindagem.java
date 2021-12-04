package com.example.pedro.calculadora;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Blindagem {

    protected static double TVL_CHUMBO = 0.052;
    protected static double TVL_ACO = 0.10;
    protected static double TVL_CONCRETO = 0.028;
    protected static double TVL_BARITA = 0.019;
    protected static double FREE_ENVIRONMENT_DOSE = 0.02;
    protected static double CONTROLED_ENVIRONMENT_DOSE = 0.4;

    private double fatorConversaoMetros = 1.0;
    private double fatorConversaoDose = 1.0;

    //Calcula a espessura da blindagem
    public double calculateThickness (double range, double outDose, double rxDose, double tvl){
        double thickness = 0.0;
        thickness = Math.log10((rxDose/fatorConversaoDose)/(Math.pow((range*fatorConversaoMetros),2.0)*(outDose/1000)))*tvl;
        thickness *= 100;
        return thickness;
    }

    public void setFatorConversaoMetros(double fatorConversaoMetros) {
        this.fatorConversaoMetros = fatorConversaoMetros;
    }

    public void setFatorConversaoDose(double fatorConversaoDose){
        this.fatorConversaoDose = fatorConversaoDose;
    }

    //Formata o resultado para duas casas decimais
    public String formatNumber (double number){
        String formatedNumber = "";
        DecimalFormat df = new DecimalFormat("###.##");
        df.setRoundingMode(RoundingMode.UP);
        formatedNumber = df.format(number);
        return formatedNumber;
    }
}
