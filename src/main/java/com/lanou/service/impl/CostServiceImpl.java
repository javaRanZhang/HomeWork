package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.controller.MainController;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import com.lanou.utils.MyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dllo on 17/8/30.
 */
@Service
public class CostServiceImpl implements CostService {
    @Resource
    private CostMapper costMapper;

    //显示所有cost
    public PageInfo<Cost> allCostView(Integer pageNum , Integer pageSize) throws MyException {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 2 : pageSize;
        PageHelper.startPage(pageNum,pageSize);

        List<Cost> allCost = costMapper.findAllCost();
        if (allCost == null) {
            throw new MyException("搜寻失败");
        }

        //使用PageInfo对查询结果进行包装
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(allCost);

        return pageInfo;

    }
    //通过ajax判断名字是否有重复
    public Boolean booleanName(String costName) {
       //查询所有
        List<Cost> allCost = costMapper.findAllCost();
        for (Cost cost : allCost) {
            //有一样的返回FALSE
            if (cost.getName().equals(costName)) {
                return false;
            }
        }
        return true;
    }
   //真的要添加了
    public void insert(Cost cost) {
        cost.setStatus("1");
        cost.setCreatime(new Timestamp(System.currentTimeMillis()));
        System.out.println(cost);
        costMapper.insert(cost);
    }
//     删除
    public Boolean deleteFee(Integer id) {
        Boolean tf = costMapper.deleteFee(id);
        return tf;
    }
 //    tong过id查找cost 作为修改的回显
    public Cost findById(Integer id) {
        Cost cost = costMapper.findById(id);
        return cost;
    }
//    真正修改啦
    public Boolean saveModi(Cost cost) {
        Boolean tf = costMapper.saveModi(cost);
        return tf;
    }
//   启用
    public void startFee(Integer id) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        costMapper.startFee(id,timestamp);
    }
}
