package com.findit.FindIt.dto;

import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.Recruiter;
import jakarta.persistence.*;
import lombok.Data;


@Data
public class VacancyRegisterDto {



    private String title;

    private String description;

    private String taskLink;

    private String price;


}
