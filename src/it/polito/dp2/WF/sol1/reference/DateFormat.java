package it.polito.dp2.WF.sol1.reference;

public enum DateFormat {

    DATE_FORMAT("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    private String date;

    DateFormat(String date) {
        this.date = date;

    }

    @Override
    public String toString() {
        return this.date;
    }
}
