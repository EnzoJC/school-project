package edu.colegiosprisma.school.entity.pagination;

import java.util.List;

public class Page<T> {
    private List<T> data;
    private int recordsFiltered;
    private int recordsTotal;
    private int draw;

    public Page(List<T> data) {
        this.data = data;
    }
}
