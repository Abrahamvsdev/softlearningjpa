package com.example.softlearning.applicationcore.entity.sharedkernel.model.operations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations.Check;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;

public abstract class Operation {

    protected LocalDateTime initDate, finishDate; // Fecha de la operacion
    protected String description; // Descripcion de la operacion, no de la compra
    protected int reference; // Referencia de la operacion, Un numero que clasifique la operacion
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");

    protected Operation() {
    }


    public void operation(int reference, String description,String initDate) throws BuildException 
    {
        StringBuilder errors = new StringBuilder();
        int errorCode;

        if ((errorCode = this.setRef(reference)) != 0) {
            errors.append("Bad reference: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        this.setDescription(description);
        if ((errorCode = this.setInitDate(initDate)) != 0) {
            errors.append("Bad initDate: ").append(Check.getErrorMessage(errorCode)).append("\n");
        }
        if (errors.length() > 0) {
            throw new BuildException("No es posible crear la operación: \n" + errors.toString());
        }
    }

    
    public String getInitDate() {
        return this.initDate.format(formatter);
    }

    public String getFinishDate() {
        if (this.finishDate != null) {
            return this.finishDate.format(formatter);
        }
        return "";
    }

    public String getDescription() {
        return description;
    }

    public int getRef() {
        return reference;
    }
    
    public int setInitDate(String initDate) {
        int errorCode = Check.isValidDateComplete(initDate);
        if (errorCode == 0) {
            this.initDate = LocalDateTime.parse(initDate, formatter);
        }
        return errorCode;
    }

    public int setFinishDate(String finishDate) {
        int errorCode = Check.isValidDateComplete(finishDate);
        if (errorCode == 0) {
            LocalDateTime parsedFinishDate = LocalDateTime.parse(finishDate, formatter);
            if (this.initDate != null && parsedFinishDate.isBefore(this.initDate)) {
                return -8; // La fecha final no puede ser anterior a la inicial
            }
            this.finishDate = parsedFinishDate;
        }
        return errorCode;
    }

    public int setDescription(String description) {
        this.description = description;
        return 0;
    }

    public int setRef(int ref) {
        if (ref < 1000) 
            return -15;  

        if (ref > 10000) 
            return -16;
        
        this.reference = ref;
        return 0;
    }

}
