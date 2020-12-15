import com.learn.dao.AccountMapper;
import com.learn.pojo.Account;
import com.learn.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    private ApplicationContext context;
    private AccountMapper accountMapper;
    private AccountService service;

    @Before
    public void getMapper() {
        context = new ClassPathXmlApplicationContext("bean.xml");
        accountMapper = context.getBean("accountMapper", AccountMapper.class);
        service = context.getBean("accountService", AccountService.class);
    }

    @Test
    public void textTransfer() {
        System.out.println(accountMapper.getAccountById(6));
        System.out.println(accountMapper.getAccountById(9));

        service.transfer(6, 9, 1000f);

        System.out.println("-------转账后-------");
        System.out.println(accountMapper.getAccountById(6));
        System.out.println(accountMapper.getAccountById(9));
    }
}
