package com.example.webflux.dao;

import com.example.webflux.dto.Employee;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class EmployeeDao {

    public static void sleepExecution(int x) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static Employee createEmployee(int x) {
        int rand=new Random().nextInt(15)+x;
        String salary= "$"+rand * 9000;
        return new Employee(x, "employee" + x,salary);
    }

    public List<Employee> fetchAllEmployee() {
        return IntStream.rangeClosed(1, 50)
                .peek(EmployeeDao::sleepExecution)
                .peek(i -> System.out.println("processing count ::" + i))
                .mapToObj(EmployeeDao::createEmployee)//.mapToObj(i->new Employee(i,"employee"+i))
                .collect(Collectors.toList());
    }

    public Flux<Employee> fetchAllEmployeeWithFlux() {
        return Flux.range(1, 50)
                .delayElements(Duration.ofMillis(1000))
                .doOnNext(x -> System.out.println("processing flux count ::" + x))
                .map(EmployeeDao::createEmployee);
    }

}
