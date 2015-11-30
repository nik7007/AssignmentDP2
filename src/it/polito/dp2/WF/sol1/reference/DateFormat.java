package it.polito.dp2.WF.sol1.reference;

public enum DateFormat {

    DATE_FORMAT("yyyy-MM-dd HH:mm:ss.SSS z");
    private String date;

    DateFormat(String date) {
        this.date = date;

    }

    @Override
    public String toString() {
        return this.date;
    }
}
