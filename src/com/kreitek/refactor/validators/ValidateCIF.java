package com.kreitek.refactor.validators;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCIF implements ValidatorsInterface {

    public String numCIF;
    public Date fchValidez;
    final String cifUP;
    final char primerCar;
    final char ultimoCar;

    public ValidateCIF(String numCIF, Date fchValidez) {
        this.numCIF = numCIF;
        this.fchValidez = fchValidez;
        cifUP = numCIF.toUpperCase();
        primerCar = cifUP.charAt(0);
        ultimoCar = cifUP.charAt(cifUP.length() - 1);
    }

    @Override
    public void validate() {

        if (checkPattern()){
            TipoUltCaracter tipUltCar = assignLetterOrNumber();
            int number = calculateControlNumber();
            boolean result = finalCheck(tipUltCar, number);

            if (result){
                System.out.println("CIF " + this.numCIF + " es: true");
            }else
                System.out.println("CIF " + this.numCIF + " es: false");

        }else{
            System.out.println("CIF " + this.numCIF + " es: false");
        }
    }

    private boolean checkPattern(){
        final Pattern mask = Pattern
                .compile("[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[A-Z[0-9]]{1}");
        final Matcher matcher = mask.matcher(cifUP);

        if ("ABCDEFGHJKLMNPQRSUVW".indexOf(cifUP.charAt(0)) == -1) {
            return false; // no cumple el primer char
        }else if (!matcher.matches()) {
            return false; // no cumple la máscara
        }else {
            return true;
        }
    }

    private TipoUltCaracter assignLetterOrNumber(){
        TipoUltCaracter tipUltCar;

        if (primerCar == 'P' || primerCar == 'Q' || primerCar == 'S' || primerCar == 'K' || primerCar == 'W') {
            tipUltCar = TipoUltCaracter.LETRA;
            if (!(ultimoCar >= 'A' && ultimoCar <= 'Z')) {
                tipUltCar = null;
            }
        } else if (primerCar == 'A' || primerCar == 'B' || primerCar == 'E'
                || primerCar == 'H') {
            tipUltCar = TipoUltCaracter.NUMERO;
            if (!(ultimoCar >= '0' && ultimoCar <= '9')) {
                tipUltCar = null;
            }
        } else {
            tipUltCar = TipoUltCaracter.AMBOS;
        }

        return tipUltCar;
    }

    private int calculateControlNumber(){
        final String digitos = cifUP.substring(1, cifUP.length() - 1);

        Integer sumaPares = 0;
        for (int i = 1; i <= digitos.length() - 1; i = i + 2) {
            sumaPares += Integer.parseInt(digitos.substring(i, i + 1));
        }

        Integer sumaImpares = 0;
        for (int i = 0; i <= digitos.length() - 1; i = i + 2) {
            Integer cal = Integer.parseInt(digitos.substring(i, i + 1)) * 2;
            if (cal.toString().length() > 1) {
                cal = Integer.parseInt(cal.toString().substring(0, 1))
                        + Integer.parseInt(cal.toString().substring(1, 2));
            }
            sumaImpares += cal;
        }

         final Integer total = sumaPares + sumaImpares;

         Integer numControl = 10 - (total % 10);
         return numControl;
    }

    private boolean finalCheck(TipoUltCaracter tipUltCar, int numControl){
        int pos = numControl == 10? 0:numControl;
        final char carControl = "JABCDEFGHI".charAt(pos);

        if (tipUltCar == TipoUltCaracter.NUMERO) {
            final Integer ultCar = Integer.parseInt(Character
                    .toString(ultimoCar));
            if (pos != ultCar.intValue()) {
               return false;
            }

        } else if (tipUltCar == TipoUltCaracter.LETRA) {
            if (carControl != ultimoCar) {
                return false;
            }
        } else {
            Integer ultCar = -1;
            ultCar = "JABCDEFGHI".indexOf(ultimoCar);

            if (ultCar < 0){
                ultCar = Integer.parseInt(Character.toString(ultimoCar));
                if (pos != ultCar.intValue()) {
                    return false;
                }
            }
            if ((pos != ultCar.intValue()) && (carControl != ultimoCar)) {
                return false;
            }
        }
        return true;
    }



}
