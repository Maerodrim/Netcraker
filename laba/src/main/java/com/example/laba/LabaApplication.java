package com.example.laba;

import com.example.laba.rmi.Bluer;
import com.example.laba.rmi.Blurring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;

@SpringBootApplication
public class LabaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabaApplication.class, args);
    }

    @Bean
    RmiServiceExporter exporter(Bluer p1) {
        Class<Bluer> serviceInterface = Bluer.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(p1);
        exporter.setServiceName("Blurring");
        exporter.setRegistryPort(1099);
        return exporter;
    }
    @Bean
    Bluer bookingService() throws RemoteException {
        return new Blurring();
    }
}
