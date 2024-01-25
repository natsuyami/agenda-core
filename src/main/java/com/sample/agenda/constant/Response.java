/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.sample.agenda.constant;

/**
 * @author ryangabriel.bunquin
 * @version $Id: Response.java, v 0.1 2024-01-23 8:08 PM Ryan Gabriel Bunquin Exp $$
 */
public enum Response {
    SUCCESS("00000","Successfully Processed"),
    GENERAL_ERROR("00001", "System encountered unknown error");

    private String errorCode;
    private String errorMsg;

    Response(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
