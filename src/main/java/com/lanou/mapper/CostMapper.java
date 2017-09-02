package com.lanou.mapper;


import com.lanou.bean.Cost;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dllo on 17/8/30.
 */
@Repository
public interface CostMapper {
    List<Cost> findAllCost();

    void insert(Cost cost);

    Boolean deleteFee(Integer id);

    Cost findById(Integer id);

    Boolean saveModi(Cost cost);

    void startFee(@Param("id") Integer id, @Param("timestamp") Timestamp timestamp);
}
