package com.lekhraj.java.spring.SpringCore.bean;




public class ItemImpl_2 implements Item{
    @Override
    public String getDetail() {
        return "Nice product for skin";
    }
    @Override
    public String getName() {
        return "hempz";
    }

    @Override
    public Long getCode() {
        return 2L;
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
