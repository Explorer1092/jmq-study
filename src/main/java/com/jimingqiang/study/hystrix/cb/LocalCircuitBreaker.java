package com.jimingqiang.study.hystrix.cb;

import com.jimingqiang.study.hystrix.state.CloseCBState;

/**
 * Created by QDHL on 2018/10/10.
 *
 * @author mingqiang ji
 */
public class LocalCircuitBreaker extends AbstractCircuitBreaker {
    public LocalCircuitBreaker(String failRateForClose,
                               int idleTimeForOpen,
                               String passRateForHalfOpen, int failNumForHalfOpen){
        this.thresholdFailRateForClose = failRateForClose;
        this.thresholdIdleTimeForOpen = idleTimeForOpen;
        this.thresholdPassRateForHalfOpen = passRateForHalfOpen;
        this.thresholdFailNumForHalfOpen = failNumForHalfOpen;
    }

    @Override
    public void reset() {
        this.setState(new CloseCBState());
    }

    @Override
    public boolean canPassCheck() {
        return getState().canPassCheck(this);
    }

    @Override
    public void countFailNum() {
        getState().countFailNum(this);
    }
}
