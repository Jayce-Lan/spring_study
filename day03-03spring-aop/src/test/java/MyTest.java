import com.learn.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void testAOP() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService service = context.getBean("accountService", AccountService.class);
        service.saveAccount();
        service.update(1);
        service.deleteAccount();
    }
}
