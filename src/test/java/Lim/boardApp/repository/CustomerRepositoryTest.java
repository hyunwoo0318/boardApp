package Lim.boardApp.repository;

import Lim.boardApp.domain.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/* TEST DATA
CID  	LOGINID  	PASSWORD  	CNAME  	AGE
  1	    test1	    123123	    tester	20
*/
@SpringBootTest
@Transactional
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @DisplayName("customer 엔티티와 테이블이 잘 매핑되는지 확인")
    @Test
    public void customerSelectTest(){
        Customer findCustomer = customerRepository.getReferenceById(1L);

        assertEquals(findCustomer.getAge(), 20);
        assertEquals(findCustomer.getPassword(), "123123");
        assertEquals(findCustomer.getCname(), "tester");
        assertEquals(findCustomer.getLoginId(), "test1");
    }

    @DisplayName("customer 테이블의 loginID의 unique속성 테스트")
    @Test
    public void loginIdUniqueTest(){
        Customer dupCustomer = new Customer();
        dupCustomer.setPassword("123123123");
        dupCustomer.setAge(32);
        dupCustomer.setCname("duplicateTester");
        dupCustomer.setLoginId("test1");

        Assertions.assertThrows(DataIntegrityViolationException.class, ()->{customerRepository.save(dupCustomer);});
    }

    @DisplayName("findByLoginId() 테스트")
    @Test
    public void findByLoginIdTest(){
        List<Customer> customers = customerRepository.findByLoginId("test1");
        Customer customer = customers.get(0);

        assertEquals(customer.getAge(), 20);
        assertEquals(customer.getPassword(), "123123");
        assertEquals(customer.getCname(), "tester");
    }

    //TODO : customer를 만들어서 잘 들어갔는지 확인 jdbc로 쿼리 직접 날리기.



}