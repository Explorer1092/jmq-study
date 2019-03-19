package com.jimingqiang.study.Bean;

import com.jimingqiang.study.exception.BusinessException;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by QDHL on 2018/9/26.
 *
 * @author mingqiang ji
 */
@Data
public class Order implements Serializable {

    public static final String DEFAULT_PERCENTAGE = "0.3";

    private String orderId; // 订单id

    private Integer orderStatus; // 订单状态 0：未支付，1：已支付，2：订单已取消

    private String orderName; // 订单名字

    public static void main(String[] args) {

        Order order = new Order();

        try {
            order.exceptionTest();

        } catch (Exception e) {
            System.out.println(1);
            e.printStackTrace();
        }

    }

    private void exceptionTest()  {
        throw new BusinessException(1,"22222");
    }


}
