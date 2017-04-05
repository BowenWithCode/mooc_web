package org.mooc.utility;

import java.util.TreeSet;

import org.bson.Document;
import org.mooc.bean.UserLearnedCourses;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;

/**
 * @author: wuke 
 * @date  : 2016��12��14�� ����8:15:25
 * Title  : JavaBeanToDocument
 * Description : 
 */
public class JavaBeanToDocument {

	public static void main(String[] args) {
		String userId = "001";
		TreeSet<String> courseSet = new TreeSet<String>();
		courseSet.add("a");
		courseSet.add("b");
		
		UserLearnedCourses userCourses = new UserLearnedCourses(userId, courseSet);
		
		MongoCollection<Document> collection = MongodbConn.getMongoCollection("mooc", "userCourses");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(userCourses);
		System.out.println(jsonStr);
		
		Document doc = Document.parse(jsonStr);
		
		collection.insertOne(doc);
	}
}
