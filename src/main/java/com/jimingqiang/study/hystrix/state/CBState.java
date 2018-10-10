package com.jimingqiang.study.hystrix.state;

import com.jimingqiang.study.hystrix.cb.AbstractCircuitBreaker;

/**
 * Created by QDHL on 2018/10/10.
 *
 * @author mingqiang ji
 *
 * 熔断器状态
 */
public interface CBState {
    /**
     * 获取当前状态名称
     */
    String getStateName();

    /**
     * 检查以及校验当前状态是否需要扭转
     */
    void checkAndSwitchState(AbstractCircuitBreaker cb);

    /**
     * 是否允许通过熔断器
     */
    boolean canPassCheck(AbstractCircuitBreaker cb);

    /**
     * 统计失败次数
     */
    void countFailNum(AbstractCircuitBreaker cb);
}
