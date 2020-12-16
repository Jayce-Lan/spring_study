import com.learn.config.SpringConfiguration;
import com.learn.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class MyTest {
    @Autowired
    private AccountService service;

    @Test
    public void testTransfer() {
        System.out.println(service.getAccountById(6));
        System.out.println(service.getAccountById(9));
        service.transfer(6, 9, 100f);
        System.out.println("----------------转账后");
        System.out.println(service.getAccountById(6));
        System.out.println(service.getAccountById(9));
    }

}
