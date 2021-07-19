package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.City;

/**
 * 城市 DAO 接口类
 *
 * Created by bysocket on 07/02/2017.
 */

/**
 * 这里不加@Mapper的话，Application那里一定要加@MapperScan("org.spring.springboot.dao")
 */
public interface CityDao {

    /**
     * 1-增
     */


    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(@Param("cityName") String cityName);
}
