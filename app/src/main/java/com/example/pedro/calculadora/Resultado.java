package com.example.pedro.calculadora;

public class Resultado {

    private String materialType;
    private String thicknessFree;
    private String thicknessControled;
    protected static final String FREE_ROOM = "Free Room:";
    protected static final String CONTROLED_ROOM = "Controled Room:";

    public void setThicknessFree (String thickness){
        this.thicknessFree = thickness;
    }

    public void setMaterialType (String material){
        this.materialType = material;
    }


    public String getMaterialType (){
        return  materialType;
    }

    public String getThicknessFree (){
        return thicknessFree;
    }

    public String getThicknessControled (){
        return this.thicknessControled;
    }

    public void setThicknessControled (String thickness){
        this.thicknessControled = thickness;
    }

}
