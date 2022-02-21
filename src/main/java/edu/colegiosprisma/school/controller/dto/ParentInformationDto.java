package edu.colegiosprisma.school.controller.dto;

import edu.colegiosprisma.school.entity.ParentInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ParentInformationDto {
    private List<ParentInformation> parentInformationList;

    public ParentInformationDto() {
        this.parentInformationList = new ArrayList<>();
    }

    public void addParentInformation(ParentInformation parentInformation) {
        parentInformationList.add(parentInformation);
    }
}
