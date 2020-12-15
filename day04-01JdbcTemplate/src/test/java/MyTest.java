import com.learn.dao.AccountMapper;
import com.learn.pojo.Account;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    private ApplicationContext context;
    private AccountMapper accountMapper;

    @Before
    public void getMapper() {
        context = new ClassPathXmlApplicationContext("bean.xml");
        accountMapper = context.getBean("accountMapper", AccountMapper.class);
    }

    @Test
    public void getAccountList() {
        List<Account>accountList = accountMapper.getAccount();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    @Test
    public void updateAccount() {
        Account account = accountMapper.getAccountById(8);
        System.out.println(account);
        account.setMoney(2000f);

        accountMapper.updateAccount(account);
        account = accountMapper.getAccountById(8);
        System.out.println(account);
    }
}
