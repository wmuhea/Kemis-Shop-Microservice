package com.kemisshop.accountservice.dto;

/*
    wontgn created on 11/1/20 inside the package - com.famshop.authorizationservice.dto
*/

public class ResponseBean {

    private String responseStatus;
    public Integer totalNumberOfPagesForPageRequest;
    private Object responsePayLoad;


    public ResponseBean(String responseStatus, Object responsePayLoad) {
        this.responseStatus = responseStatus;
        this.responsePayLoad = responsePayLoad;
        this.totalNumberOfPagesForPageRequest = 0;
    }

    public ResponseBean(String responseStatus, Integer totalNumberOfPagesForPageRequest, Object responsePayLoad) {
        this.responseStatus = responseStatus;
        this.totalNumberOfPagesForPageRequest = totalNumberOfPagesForPageRequest;
        this.responsePayLoad = responsePayLoad;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public Integer getTotalNumberOfPagesForPageRequest() {
        return totalNumberOfPagesForPageRequest;
    }

    public Object getResponsePayLoad() {
        return responsePayLoad;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public void setResponsePayLoad(String responsePayLoad) {
        this.responsePayLoad = responsePayLoad;
    }
}
