package com.portal.jetbulb;

public enum RomanNum {
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);

    private int val;

    RomanNum(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
