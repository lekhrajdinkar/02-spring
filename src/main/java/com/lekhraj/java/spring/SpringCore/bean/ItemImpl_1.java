package com.lekhraj.java.spring.SpringCore.bean;




public class ItemImpl_1 implements Item{
    @Override
    public String getDetail() {
        return "Nice product for hair";
    }
    @Override
    public String getName() {
        return "GK-1";
    }

    @Override
    public Long getCode() {
        return 1L;
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
