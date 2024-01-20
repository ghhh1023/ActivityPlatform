package com.activityplatform.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private Integer id;
    private String name;
    private List<Integer> imgList;
    private String imgIds;
    private Date time;
    private String location;
    private String content;
    private Integer promoterId;
    private String promoterName;
    private String promoterCareer;
    private Boolean isExamine;

}
