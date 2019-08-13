package com.cn.top;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *  ActivateMQ ��������
 *
 */
public class TopProduct {

	public static final String USERNAME = "admin"; //MQ��¼�û���
	public static final String PASSWORD = "admin"; //MQ��¼����
	public static final String BROKERURL = "tcp://127.0.0.1:61616";
	public static final String TOPICNAME = "myTopic"; //�����������
	
	public static void main(String[] args) throws JMSException {
		start();
	}
	
	public static void start() throws JMSException{
		//��ȡActivew �Ự����
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKERURL);
		//���ݻỰ��������һ������
		Connection connection = connectionFactory.createConnection();
		//��������
		connection.start();
		//transacted ��һ�������Ƿ�Ҫ���� , �ڶ�����������jms��Ϣ�ɿ��� (Session.AUTO_ACKNOWLEDGE �Զ�ǩ��)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//�������� ������������
		Topic topic = session.createTopic(TOPICNAME);
		//������Ϣ���д������ 	����һ��������	����Ϣ�������Ʒ����ȥ
		MessageProducer createProducer = session.createProducer(topic);
		
		for(int i=1 ;i <= 5; i++){
			//���ô����Ϣ���е����� ������Ϣ���е��ı�
			TextMessage textMessage = session.createTextMessage("topic this is MQ i=" + i);
			//�����߷�����Ϣ 
			createProducer.send(textMessage);
		}
		
		//�ر�����
		session.close();
		connection.close();
	}
}
