import com.learn.pojo.Account;
import com.learn.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void getAccountList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService service = context.getBean("accountService", AccountService.class);
        service.transfer(5, 1, (float) 1000);
    }

    @Test
    public void getList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService service = context.getBean("accountService", AccountService.class);
        for (Account account : service.getAccountList()) {
            System.out.println(account);
        }
    }
}
