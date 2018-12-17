package com.jhkj.sso_demo.controller;

import com.jhkj.face2019_demo.common.JsonData;
import com.jhkj.face2019_demo.dto.DeptLevelDTO;
import com.jhkj.face2019_demo.service.SysDeptService;
import com.jhkj.face2019_demo.service.SysTreeService;
import com.jhkj.face2019_demo.vo.DeptVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther: LinYan
 * @date: 2018/11/30 15:32
 * @description:
 */
@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Autowired
    SysDeptService sysDeptService;
    @Autowired
    SysTreeService sysTreeService;

    @RequestMapping("/save")
    @ResponseBody
    public JsonData save(DeptVO deptVO) {
        if (sysDeptService.save(deptVO)) {
            return JsonData.success();
        } else {
            return JsonData.fail("添加失败");
        }
    }

//    @RequestMapping("/update")
//    @ResponseBody
//    public JsonData update(DeptVO deptVO) {
//        if (sysDeptService.update(deptVO)) {
//            return JsonData.success();
//        } else {
//            return JsonData.fail("修改失败");
//        }
//    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {
        List<DeptLevelDTO> dtoList = sysTreeService.deptTree();
        return JsonData.success(dtoList);
    }

}

