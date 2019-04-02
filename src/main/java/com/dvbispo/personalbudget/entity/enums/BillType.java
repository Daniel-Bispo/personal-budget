package com.dvbispo.personalbudget.entity.enums;

public enum BillType {

    DEBT(0, "debt"),
    CREDIT(1, "credit");

    private int cod;
    private String description;

    private BillType(int cod, String description){
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static BillType toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(BillType r : BillType.values()){
            if(cod.equals(r.getCod())){
                return r;
            }
        }

        throw new IllegalArgumentException("Invalid code: " + cod);
    }
}
