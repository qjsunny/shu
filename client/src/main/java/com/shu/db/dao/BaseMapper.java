package com.shu.db.dao;

import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.Pojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * Created by admin on 2017/1/8.
 */
@Repository
public interface BaseMapper {

    @SuppressWarnings("rawtypes")
    public List selectListByParam(@Param("pojo") Pojo pojo,
                                  @Param("orderObj") Order order, @Param("pager") Pager pager);

    public int selectCountByParam(@Param("pojo") Pojo pojo);

    @SuppressWarnings("rawtypes")
    public List selectListByParamMap(Map<String, Object> param);
}
