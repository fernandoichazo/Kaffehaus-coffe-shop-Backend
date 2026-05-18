package com.ucb.kaffehaus.shared.application.dto;

public class PaginatedResponse<T> {
    private T data;
    private long total;

    public PaginatedResponse() {}

    public PaginatedResponse(T data, long total) {
        this.data = data;
        this.total = total;
    }

    public static <T> PaginatedResponse<T> of(T data, long total) {
        return new PaginatedResponse<>(data, total);
    }

    public T getData() {return data;}
    public void setData(T data) {this.data = data;}
    public long getTotal() {return total;}
    public void setTotal(long total) {this.total = total;}
}
