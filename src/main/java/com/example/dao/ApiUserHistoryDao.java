package com.example.dao;

import com.example.entity.history.ApiUserHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @项目：test
 * @author ：linmin
 * @创建时间：2018/1/16
 */
public interface ApiUserHistoryDao extends JpaRepository<ApiUserHistoryEntity,Long> {

    /**
     * 根据IP和方法查询记录
     * @param controllerName
     * @param userIP
     * @param methodName
     * @return
     */
    @Query(value="SELECT * FROM api_user_history where user_ip=:userIP and controller_name=:controllerName and method_name=:methodName",
            nativeQuery = true)
    ApiUserHistoryEntity findByCnameAndIpAndMname(@Param("controllerName")String controllerName,
                                                  @Param("userIP") String userIP,
                                                  @Param("methodName")String methodName);
}
