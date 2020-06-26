package com.example.tdingb51a04;

public class Source {
    private int SourceID;
    private String SourceName;

    public void setSourceID(int sourceID) {
        SourceID = sourceID;
    }

    public int getSourceID() {
        return SourceID;
    }

    public void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    public String getSourceName() {
        if(SourceID == 2)
            return SourceName +", "+ "Xanathar's Guide to Everything";
        else
            return SourceName;
    }
}
