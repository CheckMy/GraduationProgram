package com.graduation.design.java8;

import com.baomidou.mybatisplus.core.toolkit.support.Property;
import com.graduation.design.rabbit.AnnotationRabbitMq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 方法引用
 *
 * @author xuweizhi
 * @date 2018/11/16 11:33
 */
@Component
public class MethodQuote {

    @Autowired
    private AnnotationRabbitMq annotationRabbitMq;

    // 注意是方法名哦，后面没有括号“()”哒。为啥不要括号，因为这样的是式子并不代表一定会调用这个方法。这种式子一般是用作Lambda表达式，
    // Lambda有所谓懒加载嘛，不要括号就是说，看情况调用方法。
    // 例如
    // 表达式:
    // person -> person.getAge();
    // 可以替换成
    // Person::getAge
    // 表达式
    // () -> new HashMap<>();
    // 可以替换成
    // HashMap::new
    // 这种[方法引用]或者说[双冒号运算]对应的参数类型是Function<T,R> T表示传入类型，R表示返回类型。比如表达式person -> person.getAge();
    // 传入参数是person，返回值是person.getAge()，那么方法引用Person::getAge就对应着Function<Person,Integer>类型。

    @Test
    public void stringStream() {
        String str = "Java真难啊难";
        Order order = new Order("121", "asdxsd", "x", "x", new Date());
        Order order1 = new Order("adsf", "adsdfx", "x", "x", new Date());
        Order order2 = new Order("23", "dfadasfs", "x", "x", new Date());
        Order order3 = new Order("xfa", "xfadadf", "x", "x", new Date());
        Order order4 = new Order("xfadsa", "fdxadf", "x", "x", new Date());
        System.out.println(order);
        List<Order> lists = new ArrayList<>();
        lists.add(order);
        lists.add(order1);
        lists.add(order2);
        lists.add(order3);
        lists.add(order4);
        List<String> collect = lists.stream().map(Order::getOrderId).collect(Collectors.toList());
        lists.forEach(System.out::println);
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        System.out.println(collect);
    }

    @Scheduled(cron = "5,10 * * * * *")
    public void datePrint(){
        annotationRabbitMq.sendOrder();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    /*@Scheduled(cron = )
    public void datePrints(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }*/

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Order {

        private String orderId;

        private String username;

        private String openId;

        private String whys;

        private Date createTime;

        @Override
        public String toString() {
            return "Order{" +
                    "orderId='" + orderId + '\'' +
                    ", username='" + username + '\'' +
                    ", openId='" + openId + '\'' +
                    ", whys='" + whys + '\'' +
                    ", createTime=" + createTime +
                    '}';
        }
    }
}
