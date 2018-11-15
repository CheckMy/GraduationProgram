package com.graduation.design.controller;


import com.graduation.design.mapper.AceMapper;
import com.graduation.design.model.Ace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuweizhi
 * @since 2018-11-15
 */
@RestController
@RequestMapping("/ace")
public class AceController {

    @Autowired
    private AceMapper aceMapper;

    @RequestMapping("/index")
    public Ace index(){
        Ace ace = new Ace();
        ace.setName("xxxx");
        int insert = aceMapper.insert(ace);
        return ace;
    }

}
