package edu.colegiosprisma.school.entity.pagination;

import java.util.List;

public class PagingRequest {
    private int start;
    private int length;
    private int draw;
    private List<Order> order;
    private List<Column> columns;
    private Search search;
}
