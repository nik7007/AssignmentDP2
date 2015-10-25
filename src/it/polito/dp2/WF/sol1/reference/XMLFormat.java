package it.polito.dp2.WF.sol1.reference;


public enum XMLFormat {

    ELEM_ROOT("root"),
    ELEM_WORKFLOW("workflow"),
    ELEM_SIMPLE_ACTION("simple_action"),
    ELEM_PROCESS_ACTION("process_action"),
    ELEM_SUB_ACTION("sub_action"),
    ELEM_PROCESS("process"),
    ELEM_ACTION_STATUS("action_status"),
    ELEM_ACTOR("actor"),
    ATT_NAME("name"),
    ATT_ROLE("role"),
    ATT_AUTO("auto"),
    ATT_SUB_WORKFLOW("sub_workflow"),
    ATT_NAME_REF("name_ref"),
    ATT_DATE("date"),
    ATT_TAKEN_IN_CHARGE("taken_in_charge"),
    ATT_TERMINATED("terminated");


    private String value;

    XMLFormat(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return this.value;
    }


}

