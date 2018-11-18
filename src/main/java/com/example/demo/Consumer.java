package com.example.demo;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class Consumer {

	//private static String url=ActiveMQConnection.DEFAULT_BROKER_URL;
	private static String broker_Url="tcp://localhost:61616";
	private static String subject="Test";
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext jndi = new InitialContext();
		ConnectionFactory factory =new  ActiveMQConnectionFactory(broker_Url);
		Connection connection = null;
		try {
			connection = factory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			 Destination destination = new ActiveMQQueue(subject);
			//Destination destination = session.createQueue(subject);
			MessageConsumer consumer = session.createConsumer(destination);

			Message message = consumer.receive();
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Message Received from the Broker is : " + textMessage.getText());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}
	
		
	}

	

