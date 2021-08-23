package com.example.authentication_with_jwt;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class AuthenticationWithJwtApplication {

    static class HandleMessage implements IMqttMessageListener {
        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            switch (topic){
                case "test":
                    System.out.println("message: " + message);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        String broker = "tcp://test.mosquitto.org:1883";
        String clientId = "6b6abbccf2604f6fac178919832c5ca1";
        try {
            MqttClient mqttClient = new MqttClient(broker, clientId);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);
            mqttClient.connect(connectOptions);
            mqttClient.subscribe("test", new HandleMessage());
            String send = "Linh Pham Hello World MQTT";

            MqttMessage message = new MqttMessage();
            byte[] byteArrray = send.getBytes(StandardCharsets.UTF_8);
            message.setPayload(byteArrray);
            mqttClient.publish("test", message);

        } catch (MqttException e) {
            e.printStackTrace();
            System.out.println("Mqtt connect false: " + e.getMessage());
        }
        SpringApplication.run(AuthenticationWithJwtApplication.class, args);
    }
}
