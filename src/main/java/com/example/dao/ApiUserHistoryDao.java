package com.example.dao;

import com.example.entity.history.ApiUserHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/16
 * @公司：汽车易生活
 */
public interface ApiUserHistoryDao extends JpaRepository<ApiUserHistoryEntity,Long> {

    @Query(value="SELECT * FROM  where user_ip=:userIP and controller_name=:controllerName and method_name=:methodName",
            nativeQuery = true)
    ApiUserHistoryEntity findByCnameAndIpAndMname(@Param("controllerName")String controllerName,
                                                  @Param("userIP") String userIP,
                                                  @Param("methodName")String methodName);
}
