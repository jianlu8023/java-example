package com.github.jianlu8023.format.advice.request.log;

import lombok.extern.slf4j.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.*;

import javax.servlet.http.*;
import java.util.*;

@Slf4j
@Aspect
// @Order(1) //如果有多个 可以定义来控制顺序 数字越小执行顺序靠前
// @Component
public class RequestLogAdvice {

    /*
定义切入点的时候需要一个包含名字和任意参数的签名，还有一个切入点表达式，如execution(public * com.example.aop...(..))

切入点表达式的格式：execution([可见性]返回类型[声明类型].方法名(参数)[异常])
其中[]内的是可选的，其它的还支持通配符的使用：
1) *：匹配所有字符
2) ..：一般用于匹配多个包，多个参数
3) +：表示类及其子类
4)运算符有：&&,||,!

切入点表达式关键词用例：
1）execution：用于匹配子表达式。
//匹配com.cjm.model包及其子包中所有类中的所有方法，返回类型任意，方法参数任意
@Pointcut(“execution(* com.cjm.model...(..))”)
public void before(){}

2）within：用于匹配连接点所在的Java类或者包。
//匹配Person类中的所有方法
@Pointcut(“within(com.cjm.model.Person)”)
public void before(){}
//匹配com.cjm包及其子包中所有类中的所有方法
@Pointcut(“within(com.cjm..*)”)
public void before(){}

3） this：用于向通知方法中传入代理对象的引用。
@Before(“before() && this(proxy)”)
public void beforeAdvide(JoinPoint point, Object proxy){
//处理逻辑
}

4）target：用于向通知方法中传入目标对象的引用。
@Before(“before() && target(target)
public void beforeAdvide(JoinPoint point, Object proxy){
//处理逻辑
}

5）args：用于将参数传入到通知方法中。
@Before(“before() && args(age,username)”)
public void beforeAdvide(JoinPoint point, int age, String username){
//处理逻辑
}

6）@within ：用于匹配在类一级使用了参数确定的注解的类，其所有方法都将被匹配。
@Pointcut(“@within(com.cjm.annotation.AdviceAnnotation)”)
－ 所有被@AdviceAnnotation标注的类都将匹配
public void before(){}

7）@target ：和@within的功能类似，但必须要指定注解接口的保留策略为RUNTIME。
@Pointcut(“@target(com.cjm.annotation.AdviceAnnotation)”)
public void before(){}

8）@args ：传入连接点的对象对应的Java类必须被@args指定的Annotation注解标注。
@Before(“@args(com.cjm.annotation.AdviceAnnotation)”)
public void beforeAdvide(JoinPoint point){
//处理逻辑
}

9）@annotation ：匹配连接点被它参数指定的Annotation注解的方法。也就是说，所有被指定注解标注的方法都将匹配。
@Pointcut(“@annotation(com.cjm.annotation.AdviceAnnotation)”)
public void before(){}

10）bean：通过受管Bean的名字来限定连接点所在的Bean。该关键词是Spring2.5新增的。
@Pointcut(“bean(person)”)
public void before(){}
    */


    // 为了记录执行时间 方便调试 如果不需要可以去掉
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    // 定义一个切入点
    @Pointcut("@annotation(com.github.jianlu8023.format.annotation.RequestLog)")
    public void pointCut() {
    }

    // 在进入方法前执行 可以对参数进行限制或者拦截
    // 通常在这边做日志存储存到数据库中
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("请求来源 {}", request.getRemoteAddr());
        log.info("请求URL {}", request.getRequestURL().toString());
        log.info("请求方式 {}", request.getMethod());
        log.info("响应方法 {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求参数 {}", Arrays.toString(joinPoint.getArgs()));
        startTime.set(System.currentTimeMillis());
    }

    // 环绕执行
    // 定义需要匹配的切点表达式，同时需要匹配参数
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 这句必须有 往下执行方法
        return pjp.proceed();
    }

    // 在方法执行后执行 可以打印返回的数据 判断数据是否是自己需要的
    @After("pointCut()")
    public void after(JoinPoint point) {
        if (startTime.get() == null) {
            startTime.set(System.currentTimeMillis());
        }
        log.info("耗时 {} 毫秒" , (System.currentTimeMillis() - startTime.get()));
        // log.info("返回数据 {}", point.getArgs());
    }

}
