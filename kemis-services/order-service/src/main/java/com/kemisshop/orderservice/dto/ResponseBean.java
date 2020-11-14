package com.kemisshop.orderservice.dto;

/*
    wontgn created on 11/1/20 inside the package - com.kemisshop.orderservice.dto
*/
public class ResponseBean {

    private String responseStatus;
    private Integer totalNumberOfPagesForPageRequest;
    private Object responsePayLoad;


    public ResponseBean(String status, Object responsePayLoad) {
        this.responseStatus = status;
        this.totalNumberOfPagesForPageRequest = 0;
        this.responsePayLoad = responsePayLoad;
    }

    public ResponseBean(String responseStatus, Integer totalNumberOfPagesForPageRequest, Object responsePayLoad) {
        this.responseStatus = responseStatus;
        this.totalNumberOfPagesForPageRequest = totalNumberOfPagesForPageRequest;
        this.responsePayLoad = responsePayLoad;
    }

    public String getStatus() {
        return responseStatus;
    }

    public void setStatus(String status) {
        this.responseStatus = status;
    }

    public Object getResponsePayLoad() {
        return responsePayLoad;
    }

    public void setResponsePayLoad(Object responsePayLoad) {
        this.responsePayLoad = responsePayLoad;
    }
}
