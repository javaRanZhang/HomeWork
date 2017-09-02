package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.utils.MyException;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dllo on 17/8/30.
 */
public interface CostService {
    //显示所有cost
    PageInfo<Cost> allCostView(Integer pageNum, Integer pageSize) throws MyException;

    Boolean booleanName(String costName);

    void insert(Cost cost);

    Boolean deleteFee(Integer id);
//    tong过id查找cost 作为修改的回显
    Cost findById(Integer id);

    Boolean saveModi(Cost cost);
//  启用
    void startFee(Integer id);
}
