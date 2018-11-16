package com.graduation.design.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuweizhi
 * @since 2018-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Nav implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "nav_id", type = IdType.AUTO)
    private Long navId;

    private String navName;

    private String link;

    private String user;

    private Boolean isActive;

    private Boolean isShowChild;

    private String icon;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;


}
