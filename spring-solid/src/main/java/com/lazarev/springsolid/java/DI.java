package com.lazarev.springsolid.java;

public class DI { }

class DI_Wrong{
    static class Computer{
        private final CPU cpu;
        private final IOSystem ioSystem;

        public Computer(CPU cpu, IOSystem ioSystem){
            this.cpu = cpu;
            this.ioSystem = ioSystem;
        }
    }

    static class CPU { }

    static class IOSystem { }
}
