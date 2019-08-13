package com.cn.mq.one;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


/**
 * ActivateMQ 点对点 生产者
 * */
public class Product {
	
	public static final String USERNAME = "admin"; //MQ登录用户名
	public static final String PASSWORD = "admin"; //MQ登录密码
	public static final String BROKERURL = "tcp://127.0.0.1:61616";
	public static final String QUEUENAME = "my-queue"; //队列名称
	
	public static void main(String[] args) throws JMSException {
		start();
	}
	
	public static void start() throws JMSException{
		//获取Activew 会话工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKERURL);
		//根据会话工厂生产一个连接
		Connection connection = connectionFactory.createConnection();
		//启动连接
		connection.start();
		//transacted 第一个参数是否要事物 , 第二个参数设置jms消息可靠性 (Session.AUTO_ACKNOWLEDGE 自动签收)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建队列	 QUEUENAME队列名称
		Queue queue = session.createQueue(QUEUENAME);
		//高置消息队列存放内容 	创建一个生产者	将消息队列名称放入进去
		MessageProducer createProducer = session.createProducer(queue);
		
		for(int i=1 ;i <= 5; i++){
			//设置存放消息队列的内容 设置消息队列的文本
			TextMessage textMessage = session.createTextMessage("this is MQ i=" + i);
			//生产者发送消息 
			createProducer.send(textMessage);
		}
		
		//关闭连接
		session.close();
		connection.close();
	}
	
}
