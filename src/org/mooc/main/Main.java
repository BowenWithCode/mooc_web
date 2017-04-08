package org.mooc.main;

import org.mooc.processing.courses.CrawlCourses;
import org.mooc.processing.logs.ProcesssLogs;
import org.mooc.processing.users.CrawlUsers;
import org.mooc.recommend.frequentPattern.GenAprioriDataset;
import org.mooc.recommend.frequentPattern.GenFrequentCourses;
import org.mooc.recommend.frequentPattern.GenFrequentRec;
import org.mooc.recommend.frequentPattern.GenUserLearnedCourses;

/**
* @author : wuke
* @date   : 2016��12��26������6:38:33
* Title   : Main
* Description : Need to be rewritten.
*/
public class Main {	
	public static void main(String[] args) {
		Main.first(args);
	}
	
	static void first(String[] args) {
		/* �û����γ̡���־�����ݻ�ȡ */
		CrawlUsers.main(args);      // ��ȡ�û�,����MongoDB
		System.out.println("******************************�û���Ϣ��ȡ�ɹ���******************************");
		
		CrawlCourses.main(args);    // ��ȡ�γ�,����MongoDB
		System.out.println("******************************�γ���Ϣ��ȡ�ɹ���******************************");
		
		ProcesssLogs.main(args); // ��ȡ��־,����MongoDB,��Ҫ��org.mooc.processing.logs.ProcesssLogsJsonFileStoreMongodb���޸���־�ļ�·��
		System.out.println("******************************��־��Ϣ��ȡ�ɹ���******************************");
		
		/* Ƶ����Ƽ� */
		GenUserLearnedCourses.main(args); // user_learned_courses
		System.out.println("***************************�û���ѧ�γ����ɳɹ���***************************");
		
		GenAprioriDataset.main(args);     // process the user_learned_courses records into the form that fit the method MyApriori
		GenFrequentCourses.main(args);    // call MyApriori(), generate frequent pattern courses
		System.out.println("*************************�γ�Ƶ������ɳɹ���*************************");
		
		GenFrequentRec.main(args);        // generate frequent recommendations for every user
		System.out.println("**************************Ϊÿ���û������Ƽ�����ɹ���**************************");		
	}
}