package com.kreitek.refactor.validators;

import java.util.Date;

public class ValidateNIE implements ValidatorsInterface {

    private String numNIE;
    private Date fchValidez;
    private boolean esValido = false;
    private String nieWithNumber;


    public ValidateNIE(String numNIE, Date fchValidez) {
        this.numNIE = numNIE;
        this.fchValidez = fchValidez;
    }

    @Override
    public void validate() {
        if (SearchWrongsCharacters()) {
            replaceLetterWithNumber();
            checkRemainder();
        } else {
            System.out.println("NIE " + this.numNIE + " es: false");
        }
    }

    private boolean SearchWrongsCharacters() {
        int i = 1;
        int caracterASCII = 0;

        if (numNIE.length() == 9 && Character.isLetter(numNIE.charAt(8))
                && numNIE.substring(0, 1).toUpperCase().equals("X")
                || numNIE.substring(0, 1).toUpperCase().equals("Y")
                || numNIE.substring(0, 1).toUpperCase().equals("Z")) {
            do {
                caracterASCII = numNIE.codePointAt(i);
                esValido = (caracterASCII > 47 && caracterASCII < 58);
                i++;
            } while (i < numNIE.length() - 1 && esValido);
            return esValido;
        } else {
            return esValido;
        }
    }

    private void replaceLetterWithNumber(){
        if (esValido && numNIE.substring(0, 1).toUpperCase().equals("X")) {
            nieWithNumber = "0" + numNIE.substring(1, 9);
        } else if (esValido && numNIE.substring(0, 1).toUpperCase().equals("Y")) {
            nieWithNumber = "1" + numNIE.substring(1, 9);
        } else if (esValido && numNIE.substring(0, 1).toUpperCase().equals("Z")) {
            nieWithNumber = "2" + numNIE.substring(1, 9);
        }
    }

    private void checkRemainder() {
        char letra = ' ';
        int miNIE = 0;
        int resto = 0;
        char[] asignacionLetra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F',
                'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        if (esValido) {
            letra = Character.toUpperCase(nieWithNumber.charAt(8));
            miNIE = Integer.parseInt(nieWithNumber.substring(1, 8));
            resto = miNIE % 23;
            esValido = (letra == asignacionLetra[resto]);
        }

        if (esValido) {
            System.out.println("NIE " + this.numNIE + " es: true");
        } else {
            System.out.println("NIE " + this.numNIE + " es: false");
        }
    }

}
