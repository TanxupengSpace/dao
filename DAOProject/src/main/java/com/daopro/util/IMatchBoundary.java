package com.daopro.util;

public interface IMatchBoundary {
    String prefix();
    String suffix();
    static IMatchBoundary getDefault(){
        return new IMatchBoundary() {
            @Override
            public String prefix() { return "#\\{"; }
            @Override
            public String suffix() {
                return "\\}";
            }
        };
    }
}
