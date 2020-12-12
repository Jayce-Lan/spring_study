import com.learn.dao.AccountMapper;
import com.learn.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void testUI() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//        AccountMapper accountMapper = context.getBean("accountMapper", AccountMapper.class);
        AccountService accountService = context.getBean("accountService", AccountService.class);
//        AccountService as1 = context.getBean("accountService", AccountService.class);
//        AccountService as2 = context.getBean("accountService", AccountService.class);
//        System.out.println(accountMapper);
//        System.out.println(accountService);
        accountService.saveAccount();
        context.close();
//        System.out.println(as1 == as2);
    }
}
