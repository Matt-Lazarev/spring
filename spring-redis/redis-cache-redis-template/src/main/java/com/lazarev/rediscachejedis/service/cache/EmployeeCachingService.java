package com.lazarev.rediscachejedis.service.cache;

import com.lazarev.rediscachejedis.entity.Employee;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeCachingService {
    private static final String EMPLOYEE_KEYS_PATTERN = "employees:*";
    private static final String EMPLOYEE_KEY = "employees:%d";

    private final RedisTemplate<String, Employee> redisTemplate;

    @SneakyThrows
    public Optional<Employee> get(Integer employeeId){
        String key = EMPLOYEE_KEY.formatted(employeeId);
        Employee employee = redisTemplate.opsForValue().get(key);

        return Optional.ofNullable(employee);
    }

    @SneakyThrows
    public void put(Employee employeeInfoDto){
        String key = EMPLOYEE_KEY.formatted(employeeInfoDto.getId());
        redisTemplate.opsForValue().set(key, employeeInfoDto);
    }

    public void evict(Integer employeeId){
        String key = EMPLOYEE_KEY.formatted(employeeId);
        redisTemplate.opsForValue().getAndDelete(key);
    }

    public void invalidateCache() {
        Set<String> keys = redisTemplate.keys(EMPLOYEE_KEYS_PATTERN);
        if(keys != null) {
            redisTemplate.opsForValue().getOperations().delete(keys);
        }
    }

    @PostConstruct
    private void init(){
        invalidateCache();
    }
}
