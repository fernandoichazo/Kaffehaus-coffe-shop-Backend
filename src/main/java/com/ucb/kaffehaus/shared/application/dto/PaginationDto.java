package com.ucb.kaffehaus.shared.application.dto;

public class PaginationDto {
    private String search;
    private Integer limit;
    private Integer skip;

    public PaginationDto() {}

    public PaginationDto(String search, Integer limit, Integer skip) {
        this.search = search;
        this.limit = limit;
        this.skip = skip;
    }

    public String getSearch() {return search;}
    public void setSearch(String search) {this.search = search;}
    public Integer getLimit() {return limit;}
    public void setLimit(Integer limit) {this.limit = limit;}
    public Integer getSkip() {return skip;}
    public void setSkip(Integer skip) {this.skip = skip;}
}
