package org.etocrm.auth.exception;

import org.etocrm.database.enums.ResponseEnum;


public class RefuseException extends BussinessException {
    public RefuseException(ResponseEnum responseEnum, Throwable cause) {
        super(responseEnum, cause);
    }

    public RefuseException(int code, String msg) {
        super(code, msg);
    }
}
