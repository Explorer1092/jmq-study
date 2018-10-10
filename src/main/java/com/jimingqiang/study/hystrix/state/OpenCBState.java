package com.jimingqiang.study.hystrix.state;

import com.jimingqiang.study.hystrix.cb.AbstractCircuitBreaker;

/**
 * Created by QDHL on 2018/10/10.
 * 熔断器-打开状态
 * @author mingqiang ji
 */
public class OpenCBState implements CBState {
    /**
     * 进入当前状态的初始化时间
     */
    private long stateTime = System.currentTimeMillis();

    @Override
    public String getStateName() {
        // 获取当前状态名称
        return this.getClass().getSimpleName();
    }
    @Override
    public void checkAndSwitchState(AbstractCircuitBreaker cb) {
        // 打开状态，检查等待时间是否已到，如果到了就切换到半开状态
        long now = System.currentTimeMillis();
        long idleTime = cb.thresholdIdleTimeForOpen * 1000L;
        if (stateTime + idleTime <= now){
            cb.setState(new HalfOpenCBState());
        }
    }
    @Override
    public boolean canPassCheck(AbstractCircuitBreaker cb) {
        // 检测状态
        checkAndSwitchState(cb);
        return false;
    }
    @Override
    public void countFailNum(AbstractCircuitBreaker cb) {
        // nothing
    }
}