package com.zjsn.user.demo.signDemo;

import com.zjsn.domain.sale.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AllQueryGoodsFun {
    private GoodsName1 goodsName1;
    private GoodsName2 goodsName2;

    public AllQueryGoodsFun(GoodsName1 goodsName1, GoodsName2 goodsName2) {
        this.goodsName1 = goodsName1;
        this.goodsName2 = goodsName2;
    }
    public void startQuery() {
        log.info("开机");
        goodsName1.queryGoodsMethod1();
        goodsName2.queryGoodsMethod2();
        log.info("关机");
    }

    public static <T> T demo(T one) {
        List<?> list = new ArrayList<>();
        return one;
    }

    /**
     * 泛型
     * 通配符上界
     * 可以遍历 因为不管什么什么对象集成 Number 都可以用Number进行遍历
     * 不可以直接加任何对象 除非null
     *
     * @param list
     */
    public static void printIntValue(List<? extends Number> list) {
        for (Number number : list) {
            System.out.println(number.intValue() + "");
        }
    }

    /**
     * 泛型
     * 通配符无界
     * List<Object>与 List<?>并不等同，List<Object>是 List<?>的子类。还有不能往 List<?> list
     * 里添加任意对象，除了 null
     *
     * @param list
     */
    public static void printIntValue3(List<?> list) {
        for (Object object : list) {
            System.out.println("我是"+object);
        }
    }

    /**
     * 泛型
     * 通配符下界
     * 不可以遍历 因为不知道是哪一个子类继承父类,所以不可遍历.
     *
     * @param list
     */
    public static void printIntValue2(List<? super Number> list) {
        list.add(2);
        list.add(2.2f);
        System.out.println(list.toString());
    }
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        printIntValue(integerList);
        List<Float> floatList = new ArrayList<Float>();
        floatList.add(1.2f);
        floatList.add(2.3f);
        printIntValue(floatList);
        List<? super Number> list = new ArrayList<>();
        printIntValue2(list);

        List<Integer> integerList1 = new ArrayList<>();
        integerList1.add(2);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(1L, 2L));
        printIntValue3(integerList1);
        printIntValue3(tickets);
    }

}
