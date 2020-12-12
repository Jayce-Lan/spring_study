import com.learn.pojo.Account;
import com.learn.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void getList() {
        //获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //获取业务层对象
        AccountService accountService =
                context.getBean("accountService", AccountService.class);
        //执行方法
        List<Account> accountList = accountService.getAccountList();

        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    @Test
    public void getAccount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService = context.getBean("accountService", AccountService.class);
        Account account = accountService.getAccountById(1);
        System.out.println(account);
    }

    @Test
    public void saveAccount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService = context.getBean("accountService", AccountService.class);

        Account account = new Account();
        account.setName("李华");
        account.setMoney((float) 5000);

        accountService.saveAccount(account);
    }

    @Test
    public void updateAccount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService = context.getBean("accountService", AccountService.class);

        Account account = new Account();
        account.setName("李华");
        account.setMoney((float) 4000);
        account.setId(4);

        accountService.updateAccount(account);
    }

    @Test
    public void deleteAccount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService = context.getBean("accountService", AccountService.class);

        accountService.deleteAccount(4);
    }
}
