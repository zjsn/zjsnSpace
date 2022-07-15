package com.zjsn.user.demo.jvm;

public class NameCheckerTest {
    enum color {
        red,blue,yellow;
    }

    static final int _FORTY_TWO = 42;

    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void BADLY_NAMED_CODE() {
        return;
    }
    public void NOTcamelCASEmethodNAME() {
        return;
    }
}
