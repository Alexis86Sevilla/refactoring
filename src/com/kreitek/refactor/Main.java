package com.kreitek.refactor.mal;

import com.kreitek.refactor.validators.ValidateCIF;
import com.kreitek.refactor.validators.ValidateDNI;
import com.kreitek.refactor.validators.ValidateNIE;

class  Main
{
    public static void main(String args[])
    {
        PrintBanner print = new PrintBanner("Vamos a refactorizar!");
        print.print();

        // creamos un DNI correcto
        ValidateDNI dniCorrecto = new ValidateDNI("11111111H", null);
        dniCorrecto.validate();

        // creamos un DNI incorrecto
         ValidateDNI dniIncorrecto01 = new ValidateDNI("24324356A", null);
        dniIncorrecto01.validate();

        // creamos un NIE correcto
        ValidateNIE nieCorrecto = new ValidateNIE("X0932707B", null);
        nieCorrecto.validate();

        //creamos un NIE incorrecto
        ValidateNIE nieIncorrecto = new ValidateNIE("Z2691139Z", null);
        nieIncorrecto.validate();

        // creamos un CIF correcto
        ValidateCIF cifCorrecto = new ValidateCIF("W9696294I", null);
        cifCorrecto.validate();

        // creamos un CIF incorrecto
        ValidateCIF cifIncorrecto = new ValidateCIF("W9696294A", null);
        cifIncorrecto.validate();
    }
}