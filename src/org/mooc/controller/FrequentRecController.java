package org.mooc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.mooc.utility.MongodbConn;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

/**
 * Servlet implementation class RecController
 */
public class FrequentRecController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// get userId from request
		String userId = null;
		userId = request.getParameter("userId");
		
		// default userId
		if(userId == null || userId.isEmpty())
			userId = "40f3d4bf-4631-4181-95d4-7714576db407"; 
		
		// select the user's recommend result from mooc.frequentRec
		String databaseName = "mooc";
		String mongoCollectionName = "frequentRec";
		MongoCollection<Document> collection = MongodbConn.getMongoCollection(databaseName, mongoCollectionName);
		
		Bson filter = Filters.eq("userId", userId);
		BasicDBObject sort = new BasicDBObject();
        sort.put("count",-1); // 1,��ʾ���� ��1,��ʾ����
		List<Document> docList = collection.find(filter).sort(sort).into(new ArrayList<Document>());
		
		// Document list ���� Gson ת��Ϊ String
		String json=null;
		Gson gson = new Gson();
		json = gson.toJson(docList);
		
		// ���� json ���� API
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(json.getBytes("utf-8"));
        
        // ����̨��ӡ��������Ϣ
        System.out.println("�ͻ��˵�IP��ַΪ��" + request.getRemoteAddr()); // �ͻ���IP��ַ       
        System.out.println(userId);
        System.out.println(json);
	}

}
