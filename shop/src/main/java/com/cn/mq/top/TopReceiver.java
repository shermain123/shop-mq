package com.cn.mq.top;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *ActivateMQ 发布订阅 消费 
 */
public class TopReceiver {


	public static final String USERNAME = "admin"; //MQ登录用户名
	public static final String PASSWORD = "admin"; //MQ登录密码
	public static final String BROKERURL = "tcp://127.0.0.1:61616";
	public static final String TOPICNAME = "myTopic"; //队列名称
	
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
		
		//创建主题	 TOPICNAME主题队列名称
		Topic topic = session.createTopic(TOPICNAME);
		//创建一个消费者	将消费对象放入进去
		MessageConsumer consumer = session.createConsumer(topic);
		
		//没有消息退出
		while(true){
			//等待消息
			Message receiver = consumer.receive();
			TextMessage textMessage = (TextMessage) receiver;
			
			if(textMessage != null){
				//获取消费者内容
				String contrant = textMessage.getText();
				System.out.println("内容是：" + contrant);
			}else{
				break;
			}
			
		}
		session.close();
		connection.close();
	}
}
