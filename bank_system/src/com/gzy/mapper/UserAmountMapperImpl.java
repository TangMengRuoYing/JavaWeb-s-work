package com.gzy.mapper;

import com.gzy.entiy.UserAmount;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public class UserAmountMapperImpl implements UserAmountMapper{

    /**
     * 获取所有用户的账户信息
     */
    @Select("SELECT ua.*, ui.nickname AS nicknameAmount, ui.name AS nameAmount " +
            "FROM user_amount AS ua " +
            "LEFT JOIN banksystem.user_info ui ON ua.user_id = ui.id " +
            "WHERE ua.is_deleted = 0 " +
            "LIMIT #{offset}, #{pageSize}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "state", column = "state"),
            @Result(property = "isDelete", column = "is_deleted"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "updateBy", column = "update_by"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "nicknameAmount", column = "nicknameAmount"),
            @Result(property = "nameAmount", column = "nameAmount")
    })
    List<UserAmount> getAllUserAmount(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    /**
     * 新增账户信息
     */
    @Insert("INSERT INTO user_amount(user_id, state, create_time, update_time) " +
            "VALUES(#{userId}, #{state}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean add(@Param("userId") Long userId, @Param("state") Integer state);

    /**
     * 删除账户信息
     */
    @Update("UPDATE user_amount SET is_deleted = 1, update_time = NOW() WHERE user_id = #{userId}")
    int deleteById(@Param("userId") Long userId);

    /**
     * 修改账户信息
     */
    @Update("UPDATE user_amount SET state = #{state}, update_time = NOW() WHERE user_id = #{userId}")
    int updateByUserId(@Param("userId") Long userId, @Param("state") Integer state);

    /**
     * 根据用户ID查询账户信息
     */
    @Select("SELECT * FROM user_amount WHERE user_id = #{userId} AND is_deleted = 0")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "state", column = "state"),
            @Result(property = "isDelete", column = "is_deleted"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "updateBy", column = "update_by"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    UserAmount queryByUserId(@Param("userId") Long userId);

    /**
     * 条件查询(用户名，姓名)
     */
    @Select({
            "<script>",
            "SELECT ua.*, ui.nickname AS nicknameAmount, ui.name AS nameAmount",
            "FROM user_amount AS ua",
            "LEFT JOIN user_info ui ON ua.user_id = ui.id",
            "WHERE ua.is_deleted = 0",
            "<if test='nicknameAmount != null and nicknameAmount != \"\"'>",
            "AND ui.nickname LIKE CONCAT('%', #{nicknameAmount}, '%')",
            "</if>",
            "<if test='nameAmount != null and nameAmount != \"\"'>",
            "AND ui.name LIKE CONCAT('%', #{nameAmount}, '%')",
            "</if>",
            "LIMIT #{offset}, #{pageSize}",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "state", column = "state"),
            @Result(property = "isDelete", column = "is_deleted"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "updateBy", column = "update_by"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "nicknameAmount", column = "nicknameAmount"),
            @Result(property = "nameAmount", column = "nameAmount")
    })
    List<UserAmount> queryByConditions(@Param("nicknameAmount") String nicknameAmount,
                                       @Param("nameAmount") String nameAmount,
                                       @Param("offset") Integer offset,
                                       @Param("pageSize") Integer pageSize);

    /**
     * 查询总记录数
     */
    @Select("SELECT COUNT(*) FROM user_amount WHERE is_deleted = 0")
    int getTotalCount();
}


}
