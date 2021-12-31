package edu.colegiosprisma.school.entity.pagination;

import java.util.List;

public class PageArray {
    private List<List<String>> data;
    private int recordsFiltered;
    private int recordsTotal;
    private int draw;
}
