package com.assertsolutions.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Assert Solutions S.A.S
 *
 */

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -6104876573750302537L;
   
    private List<Error> errors;

    public void clean() {
        errors = null;
    }

    public void addError(Error error) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(error);
    }

   
    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

	@Override
	public String toString() {
		return "ErrorResponse [errors=" + errors + "]";
	}
    
    
    

}
