package com.kemisshop.catalogservice.dto;

/*
    wontgn created on 11/2/20 inside the package - com.farmshop.catalogservice.dto
*/
public class ResponseBean {

    private String responseStatus;
    private Integer totalNumberOfPagesForPageRequest;
    private Object responsePayLoad;

    public ResponseBean() {
    }

    // The normal constructor called when the request is not a page request
    public ResponseBean(String responseStatus, Object responsePayLoad) {
        this.responseStatus = responseStatus;
        this.totalNumberOfPagesForPageRequest = 0;
        this.responsePayLoad = responsePayLoad;
    }

    // Second constructor when we need to set the number of pages in a page request
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
}
