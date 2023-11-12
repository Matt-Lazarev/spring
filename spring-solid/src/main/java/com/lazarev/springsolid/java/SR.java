package com.lazarev.springsolid.java;

public class SR { }

class Service {
    void save() {} //DB-call

    void send() {} //HTTP-call

    void log() {}  //logging
}

class ServiceCorrect {
    private Repository repository;
    private HttpClient httpClient;
    private Logger logger;

    static class Repository {
        void save() {}
    }

    static class HttpClient {
        void send(){}
    }

    static class Logger {
        void log(){}
    }
}


