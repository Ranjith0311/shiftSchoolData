package com.sift.consultancy.school.schoolData.Model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SchoolInfo {

    private long NCESSCH;
    private long leaID;
    private String leaName;
    private String schoolName;
    private String city;
    private String state;
    private float latitude;
    private float longitude;
    private int metroCentric;
    private int urbanCentric;
    private int status;
}
