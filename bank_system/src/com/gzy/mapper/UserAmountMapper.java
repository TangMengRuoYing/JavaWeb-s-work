package com.gzy.mapper;

import com.gzy.entiy.UserAmount;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserAmountMapper {

    /**
     * Get all user account information with pagination
     */
    List<UserAmount> getAllUserAmount(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    /**
     * Add new account information
     */
    int add(@Param("userId") Long userId, @Param("state") Integer state);

    /**
     * Delete account by user ID (logical deletion)
     */
    int deleteById(@Param("userId") Long userId);

    /**
     * Update account information by user ID
     */
    int updateByUserId(@Param("userId") Long userId, @Param("state") Integer state);

    /**
     * Query account information by user ID
     */
    UserAmount queryByUserId(@Param("userId") Long userId);

    /**
     * Conditional query by nickname and name
     */
    List<UserAmount> queryByConditions(@Param("nicknameAmount") String nicknameAmount,
                                       @Param("nameAmount") String nameAmount,
                                       @Param("offset") Integer offset,
                                       @Param("pageSize") Integer pageSize);

    /**
     * Get total record count
     */
    int getTotalCount();
}
