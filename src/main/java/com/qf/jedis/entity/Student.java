package com.qf.jedis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Cjl
 * @date 2021/8/9 17:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements Serializable {
    private Integer id;
    private String name;
    private Integer age;


}
