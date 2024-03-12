package org.isj.ing3.isi.webservice.webservicerest.utils;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;

import java.util.Objects;

public class CHeckNull {

    public static void checkIntegerIsNull(Integer integerNumber) throws IsjException {
        if (Objects.isNull(integerNumber)) {
            throw new IsjException(ErrorInfo.REFERENCE_RESSOURCE_REQUIRED);
        }
    }

    public static void checkLongIsNull(Long integerNumber) throws IsjException {
        if (Objects.isNull(integerNumber)) {
            throw new IsjException(ErrorInfo.REFERENCE_RESSOURCE_REQUIRED);
        }
    }

    public static void checkStringIsNull(String stringValue) throws IsjException {
        if (Objects.isNull(stringValue)) {
            throw new IsjException(ErrorInfo.REFERENCE_RESSOURCE_REQUIRED);
        }
    }
}
