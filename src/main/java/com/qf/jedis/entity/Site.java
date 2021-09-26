package com.qf.jedis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Cjl
 * @date 2021/8/9 19:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Site implements Serializable {
    private Integer siteId;
    private String siteName;
    private Integer userId;
}
