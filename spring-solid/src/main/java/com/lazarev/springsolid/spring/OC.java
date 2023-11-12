package com.lazarev.springsolid.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public class OC { }

class OC_Wrong{
    interface Filter {
        void apply(Object object);
    }

    @Component
    static class FirstFilter implements Filter{
        @Override
        public void apply(Object object) {}
    }

    @Component
    static class SecondFilter implements Filter{
        @Override
        public void apply(Object object) {}
    }

    @Service
    @RequiredArgsConstructor
    static class MyService {
        private final FirstFilter firstFilter;
        private final SecondFilter secondFilter;

        public void applyFilters(Object object){
            firstFilter.apply(object);
            secondFilter.apply(object);
        }
    }

    @Service
    @RequiredArgsConstructor
    static class MyServiceCorrect {
        private final List<Filter> filters;

        public void applyFilters(Object object){
            filters.forEach(f -> f.apply(object));
        }
    }
}
