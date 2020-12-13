import com.learn.pojo.Account;
import com.learn.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void getAccountList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService = context.getBean("accountService", AccountService.class);

        List<Account> accountList = accountService.getAccountList();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }
}
