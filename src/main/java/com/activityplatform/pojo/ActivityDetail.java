package com.activityplatform.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDetail {
    private Integer id;
    private Integer aId;
    private Long vId;
    private Long lId;
    private Integer viewersCount;
    private Integer likesCount;
    private Integer subscribersCount;
    private Double signupFee;

}
