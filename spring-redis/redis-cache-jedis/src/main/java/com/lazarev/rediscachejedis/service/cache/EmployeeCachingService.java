package com.lazarev.rediscachejedis.service.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lazarev.rediscachejedis.entity.Employee;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeCachingService {
    private static final String EMPLOYEE_KEYS_PATTERN = "employees:*";
    private static final String EMPLOYEE_KEY = "employees:%d";

    private final Jedis jedis;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public Optional<Employee> get(Integer employeeId){
        String key = EMPLOYEE_KEY.formatted(employeeId);
        String value = jedis.get(key);

        if(value != null){
            return Optional.of(objectMapper.readValue(value, Employee.class));
        }
        return Optional.empty();
    }

    @SneakyThrows
    public void put(Employee employee){
        String key = EMPLOYEE_KEY.formatted(employee.getId());
        String value = objectMapper.writeValueAsString(employee);

        jedis.set(key, value);
    }

    public void evict(Integer employeeId){
        String key = EMPLOYEE_KEY.formatted(employeeId);
        jedis.del(key);
    }

    public void invalidateCache() {
        Set<String> keys = jedis.keys(EMPLOYEE_KEYS_PATTERN);
        jedis.del(keys.toArray(new String[0]));
    }
}
