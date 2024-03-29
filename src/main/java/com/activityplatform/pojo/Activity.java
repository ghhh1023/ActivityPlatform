package com.activityplatform.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

import java.util.List;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private Integer id;
    private String name;
    private List<Long> imgList;
    private String imgIds;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    private String location;
    private String content;
    private Integer promoterId;
    private String promoterName;
    private String promoterCareer;
    private Boolean isExamine;
}
