package Lim.boardApp.domain;


import Lim.boardApp.repository.CustomerRepository;
import Lim.boardApp.repository.TextRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class CustomerAndTextTest {

    @Autowired
    TextRepository textRepository;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    public void init(){

        Customer customer1 = Customer.builder()
                .loginId("loginId1")
                .age(20)
                .cname("c1")
                .password("p1")
                .build();
        Customer customer2 = Customer.builder()
                .loginId("loginId2")
                .age(30)
                .cname("c2")
                .password("p2")
                .build();

        Text text1 = Text.builder()
                .customer(customer1)
                .content("content1")
                .title("title1")
                .build();
        Text text2 = Text.builder()
                .customer(customer2)
                .content("content2")
                .title("title2")
                .build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        textRepository.save(text1);
        textRepository.save(text2);


    }

    @AfterEach
    public void deleteAll(){
        textRepository.deleteAll();
        customerRepository.deleteAll();
    }

    //Select
    @DisplayName("text를 통해 customer의 정보 select")
    @Test
    public void selectCustomerThroughText(){

    }


}
