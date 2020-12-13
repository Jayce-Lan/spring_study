import com.learn.config.SpringConfiguration;
import com.learn.pojo.Account;
import com.learn.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 基于注解整合测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
//(locations:指定xml文件路径，加上classpath关键字;classes: 指定注解类文件路径
@ContextConfiguration(classes = SpringConfiguration.class)
public class RunwithTest {
    @Autowired
    private AccountService service;

    @Test
    public void getAccountList() {
        List<Account> accountList = service.getAccountList();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }
}
