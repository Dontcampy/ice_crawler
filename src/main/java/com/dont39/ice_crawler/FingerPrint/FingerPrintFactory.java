package com.dont39.ice_crawler.FingerPrint;

public class FingerPrintFactory {
    private static final FingerPrintFactory fpf = new FingerPrintFactory();

    private FingerPrintFactory() {}

    public static FingerPrintFactory getFactory() {
        return fpf;
    }

    public FingerPrint getFingerPrint(String algorithm) {
        if (algorithm == null) {
            return null;
        } else if (algorithm.equals("Normal")) {
            return new Normal();
        } else if (algorithm.equals("Bloom")) {
            return new Bloom();
        }
        return null;
    }
}
