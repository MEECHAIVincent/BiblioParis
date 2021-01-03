package com.example.biblioparislocal.models;

import java.util.List;

public class ApiBiblio {
    private int nhits;

    private List<ApiRecords> records;

    public int getNhits() {
        return nhits;
    }

    public void setNhits(int nhits) {
        this.nhits = nhits;
    }

    public List<ApiRecords> getRecords() {
        return records;
    }

    public void setRecords(List<ApiRecords> records) {
        this.records = records;
    }
}
