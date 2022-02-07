package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.District;
import edu.colegiosprisma.school.entity.Province;
import edu.colegiosprisma.school.service.IDistrictService;
import edu.colegiosprisma.school.service.IProvinceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class DistrictController {
    private final IProvinceService provinceService;
    private final IDistrictService districtService;

    public DistrictController(IProvinceService provinceService, IDistrictService districtService) {
        this.provinceService = provinceService;
        this.districtService = districtService;
    }

    @GetMapping(value = "/districts")
    public @ResponseBody List<District> getDistrictsByProvince(@RequestParam(value = "province") String id) {
        Province province = provinceService.findById(id);
        return districtService.getAllDistrictsByProvince(province);
    }
}
