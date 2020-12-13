import com.learn.config.SpringConfiguration;
import com.learn.pojo.Account;
import com.learn.service.AccountService;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void getAccountList() {
        //AnnotationConfigApplicationContext 获取配置类的内容
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AccountService service = context.getBean("accountService", AccountService.class);
        List<Account> accountList = service.getAccountList();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    @Test
    public void chackRunner() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        QueryRunner runner1 = context.getBean("runner", QueryRunner.class);
        QueryRunner runner2 = context.getBean("runner", QueryRunner.class);
        System.out.println(runner1 == runner2);
    }
}
