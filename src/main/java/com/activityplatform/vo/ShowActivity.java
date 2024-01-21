package com.activityplatform.vo;

import com.activityplatform.pojo.Activity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Validated
@Getter
@Setter
@AllArgsConstructor
public class ShowActivity {
    private Integer id;
    private String name;
    private String imgIds;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    private String location;
    private String content;
    private Integer promoterId;
    private String promoterName;
    private String promoterCareer;
    private Boolean isExamine;
    private Boolean isSubscribe;

    public ShowActivity(Activity activity){
        this.id = activity.getId();
        this.name = activity.getName();
        this.imgIds = activity.getImgIds();
        this.time = activity.getTime();
        this.location = activity.getLocation();
        this.content = activity.getContent();
        this.promoterId = activity.getPromoterId();
        this.promoterCareer = activity.getPromoterCareer();
        this.isExamine = activity.getIsExamine();
    }
}
