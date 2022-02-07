package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Department;
import edu.colegiosprisma.school.entity.Province;
import edu.colegiosprisma.school.service.IDepartmentService;
import edu.colegiosprisma.school.service.IProvinceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ProvinceController {
    private final IDepartmentService departmentService;
    private final IProvinceService provinceService;

    public ProvinceController(IDepartmentService departmentService, IProvinceService provinceService) {
        this.departmentService = departmentService;
        this.provinceService = provinceService;
    }

    @GetMapping("/province")
    public @ResponseBody List<Province> getProvincesByDepartment(@RequestParam(name = "department", required = false) String id) {
        Department department = departmentService.findById(id);
        return provinceService.getAllProvincesByDepartment(department);
    }
}
