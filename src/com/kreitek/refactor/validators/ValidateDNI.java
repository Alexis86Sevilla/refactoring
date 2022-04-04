package com.kreitek.refactor.validators;

import java.util.Date;

public class ValidateDNI implements ValidatorsInterface {

    private String numDNI;
    private Date fchValidez;

    public ValidateDNI(String numDNI, Date fchValidez) {
        this.numDNI = numDNI;
        this.fchValidez = fchValidez;
    }

    @Override
    public void validate(){
        String dniChars="TRWAGMYFPDXBNJZSQVHLCKE";
        String intPartDNI = this.numDNI.trim().replaceAll(" ", "").substring(0, 8);
        char ltrDNI = this.numDNI.charAt(8);
        int valNumDni = Integer.parseInt(intPartDNI) % 23;

        if (this.numDNI.length()!= 9 || isNumeric(intPartDNI) == false || dniChars.charAt(valNumDni)!= ltrDNI) {
            System.out.println( "DNI " + this.numDNI + " es: false");
        } else {
            System.out.println( "DNI " + this.numDNI + " es: true");
        }
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
