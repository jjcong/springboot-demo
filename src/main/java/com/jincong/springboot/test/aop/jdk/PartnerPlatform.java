package com.jincong.springboot.test.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * PartnerPlatform 陪玩平台，管理玩家与陪玩者
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/12/6
 */
public class PartnerPlatform {

    private static final String RECEIVE_MONEY = "receiveMoney";
    private static List<Partner> partners = new ArrayList<>();

    static {
        partners.add(new IndividualPartner("肖洁洁"));
        partners.add(new IndividualPartner("田苟"));
        partners.add(new IndividualPartner("高总裁"));
    }

    /**
     * 获取陪玩者，如果期望佣金不理想，陪玩者拒绝服务
     * @param money 预算
     * @return
     */
    public static Partner getPartner(int money) {

        Partner partner = partners.remove(0);

        // 使用Proxy的InvocationHandler创建代理对象
        return (Partner) Proxy.newProxyInstance(partner.getClass().getClassLoader(), partner.getClass().getInterfaces(), new InvocationHandler() {
            private int budget = money;
            private boolean canService = false;
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (RECEIVE_MONEY.equals(method.getName())) {
                    int paidMoney = ((int) args[0]);
                    this.canService = paidMoney / 2 >= budget;
                }

                if (canService) {
                    return method.invoke(partner, args);
                }

                return null;
            }
        });
    }
}
