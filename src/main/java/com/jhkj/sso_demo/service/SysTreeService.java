package com.jhkj.sso_demo.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.jhkj.face2019_demo.dto.DeptLevelDTO;
import com.jhkj.face2019_demo.entity.SysDept;
import com.jhkj.face2019_demo.repository.SysDeptRepository;
import com.jhkj.face2019_demo.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther: LinYan
 * @date: 2018/12/3 9:38
 * @description: 部门树列表类
 */
@Service
public class SysTreeService {

    @Autowired
    SysDeptRepository sysDeptRepository;

    public List<DeptLevelDTO> deptTree() {
        //创建并获取POJO列表
        List<SysDept> deptList = sysDeptRepository.findAll();

        //将查询到的POJO对象转为DTO对象并放入集合中
        List<DeptLevelDTO> dtoList = deptList.stream()
                .map(DeptLevelDTO::adapt)
                .collect(Collectors.toList());

        return deptList2Tree(dtoList);
    }

    /**
     * 将部门层级列表生成相应的树列表
     *
     * @param deptLevelList 部门层级列表
     * @return 排序后的树列表
     */
    public List<DeptLevelDTO> deptList2Tree(List<DeptLevelDTO> deptLevelList) {
        //如果传入的是空集，返回一个空集
        if (CollectionUtils.isEmpty(deptLevelList)) {
            return Lists.newArrayList();
        }

        //K是DTO的Level，V是DeptLevelList，每一层有多个部门。
        //level -> [dept1, dept2, ...]
        Multimap<String, DeptLevelDTO> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelDTO> rootList = Lists.newArrayList();

        for (DeptLevelDTO dto : deptLevelList) {
            levelDeptMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        // 由于没有编写将deptLevelList中的元素添加进Multimap的程序，
        // 所以如果要将if中的代码换成lambda表达式是进行了两次遍历，
        // 效率未必比未重构之前高，所以没有对if语句进行重构
//        List<DeptLevelDTO> rootList = deptLevelList.stream()
//                .filter(dto -> LevelUtil.ROOT.equals(dto.getLevel()))
//                .collect(Collectors.toList());

        //将顶层集合按照seq从小到大排序
        rootList = rootList.stream()
                .sorted(Comparator.comparing(DeptLevelDTO::getSeq))
                .collect(Collectors.toList());

        //从首层递归生成树，传入所有部门的集合、首层标识和<层级，层级部门>的map集合
        transformDeptTree(deptLevelList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    /**
     * 将
     *
     * @param deptLevelList 部门层级列表
     * @param level         部门当前层级
     * @param levelDtoMap   层级数据转化对象集合
     */
    public void transformDeptTree(List<DeptLevelDTO> deptLevelList, String level, Multimap<String, DeptLevelDTO> levelDtoMap) {
        //遍历传入的部门集合
        for (DeptLevelDTO deptLevelDTO : deptLevelList) {
            //处理当前层级的数据，根据当前层级计算出下一层的层级
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDTO.getId());
            //根据计算出的下一层的层级从map集合中获取到nextLevel层级的部门列表
            List<DeptLevelDTO> tempDeptList = (List<DeptLevelDTO>) levelDtoMap.get(nextLevel);

            //如果tempDeptList不为空，进行排序处理
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                //对当前层级的部门进行排序
                tempDeptList = tempDeptList.stream()
                        .sorted(Comparator.comparing(DeptLevelDTO::getSeq))
                        .collect(Collectors.toList());
                //设置下一层部门
                deptLevelDTO.setDeptList(tempDeptList);
                //进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDtoMap);
            }
        }
    }

}
