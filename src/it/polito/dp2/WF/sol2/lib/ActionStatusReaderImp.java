package it.polito.dp2.WF.sol2.lib;


import it.polito.dp2.WF.ActionStatusReader;
import it.polito.dp2.WF.Actor;

import java.util.Calendar;

public class ActionStatusReaderImp implements ActionStatusReader {

    private String name;
    private Actor actor;
    private Calendar terminationTime;

    public ActionStatusReaderImp(String name) {
        this.name = name;
    }

    public void takeInCharge(Actor actor) {
        this.actor = actor;
    }

    public void terminate(Calendar terminationTime) {
        this.terminationTime = terminationTime;
    }

    @Override
    public String getActionName() {
        return name;
    }

    @Override
    public boolean isTakenInCharge() {
        return actor != null;
    }

    @Override
    public boolean isTerminated() {
        return terminationTime != null;
    }

    @Override
    public Actor getActor() {
        return actor == null ? null : (Actor) actor.clone();
    }

    @Override
    public Calendar getTerminationTime() {
        return terminationTime == null ? null : (Calendar) terminationTime.clone();
    }
}
