package it.polito.dp2.WF.sol4.server.datamanager;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

class NamesHolder {
    private GregorianCalendar lastMod;
    private List<String> names;

    public NamesHolder() {
        this.lastMod = new GregorianCalendar();
        this.names = new LinkedList<>();
        Collections.synchronizedList(this.names);
    }

    public GregorianCalendar getLastMod() {
        return this.lastMod;
    }

    public void addName(String name) {

        synchronized (this) {
            this.lastMod = new GregorianCalendar();
            this.names.add(name);
        }

    }

    public List<String> getNames() {
        return new LinkedList<>(names);
    }
}
