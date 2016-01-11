package it.polito.dp2.WF.sol4.server.datamanager;


import it.polito.dp2.WF.lab4.gen.ProcessType;

import java.util.GregorianCalendar;

public class ProcessHolder {

    private GregorianCalendar lastMod;
    private ProcessType process;

    public ProcessHolder(ProcessType process) {
        this.lastMod = new GregorianCalendar();
        this.process = process;
    }

    public GregorianCalendar getLastMod() {
        return lastMod;
    }

    public ProcessType getProcess() {
        return process;
    }

    public void setProcess(ProcessType process) {
        lastMod = new GregorianCalendar();
        this.process = process;
    }

}
