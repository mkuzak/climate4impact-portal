package downscaling;

import impactservice.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Predictand;

import org.apache.commons.httpclient.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tools.Debug;


/**
 * Servlet implementation class ImpactService
 */
public class DownscalingService extends HttpServlet {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public DownscalingService() {
      super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
    String pathInfo = request.getPathInfo();
    if(pathInfo.matches("/users/.*/predictands")){
      HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + pathInfo + "?" + request.getQueryString(), "GET");
      BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) 
        sb.append(inputLine);
      in.close();
      response.setContentType("application/json");
      // Get the printwriter object from response to write the required json object to the output stream      
      PrintWriter out = response.getWriter();
      // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
      out.print(sb);
      out.flush();
    }else if(pathInfo.matches("/users/.*/zones/.*/predictors/.*/predictands/.*")){
      HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + pathInfo, "GET");
      BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) 
        sb.append(inputLine);
      in.close();
      response.setContentType("application/json");
      // Get the printwriter object from response to write the required json object to the output stream      
      PrintWriter out = response.getWriter();
      // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
      out.print(sb);
      out.flush();
    }else if(pathInfo.matches("/users/.*/zones/.*/predictands/.*/downscalingMethods")){
      String parameters = "";
      String dMethodType = request.getParameter("dMethodType");
      if(dMethodType != null && !dMethodType.equals(""))
          parameters = "?dMethodType=" + dMethodType;
      HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + pathInfo + parameters, "GET");
      BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) 
        sb.append(inputLine);
      in.close();
      response.setContentType("application/json");
      // Get the printwriter object from response to write the required json object to the output stream      
      PrintWriter out = response.getWriter();
      // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
      out.print(sb);
      out.flush();
    }else if(pathInfo.matches("/zones/.*/predictands/.*/stations")){
      HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + pathInfo, "GET");
      BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) 
        sb.append(inputLine);
      in.close();
      response.setContentType("application/json");
      // Get the printwriter object from response to write the required json object to the output stream      
      PrintWriter out = response.getWriter();
      // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
      out.print(sb);
      out.flush();
    
    }else if(pathInfo.matches("/variables")){
      HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + pathInfo + "?variableType=" + request.getParameter("variableType"), "GET");
      BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) 
        sb.append(inputLine);
      in.close();
      response.setContentType("application/json");
      // Get the printwriter object from response to write the required json object to the output stream      
      PrintWriter out = response.getWriter();
      // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
      out.print(sb);
      out.flush();
    }else if(pathInfo.matches("/validation")){
      HttpURLConnection urlConn = DownscalingAuth.prepareSimpleQuery(DownscalingAuth.BASE_DP_REST_URL + pathInfo + "?idZone=" + 
              request.getParameter("idZone") +"&predictandName="+request.getParameter("predictandName")+"&downscalingMethod="+request.getParameter("downscalingMethod").replace(" ", "%20"), "GET");
      OutputStream out = response.getOutputStream();
      InputStream in = urlConn.getInputStream();
      byte[] buffer = new byte[4096];
      int length;
      while ((length = in.read(buffer)) > 0){
          out.write(buffer, 0, length);
      }
      in.close();
      out.flush();
      response.setContentType("application/pdf");
      response.setHeader("Content-Disposition", "attachment; filename=output.pdf");
    }else if(pathInfo.matches("/datasets")){
      HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + pathInfo + "?zone="+request.getParameter("zone")+"&username="+request.getParameter("username"), "GET");
      BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) 
        sb.append(inputLine);
      in.close();
      response.setContentType("application/json");
      // Get the printwriter object from response to write the required json object to the output stream      
      PrintWriter out = response.getWriter();
      // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
      out.print(sb);
      out.flush();
    }else if(pathInfo.matches("/datasets/.*/scenarios")){
      HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + pathInfo + "?zone="+request.getParameter("zone") + "&sYear="+request.getParameter("sYear") + "&eYear="+request.getParameter("eYear"), "GET");
      BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String inputLine;
      while ((inputLine = in.readLine()) != null) 
        sb.append(inputLine);
      in.close();
      response.setContentType("application/json");
      // Get the printwriter object from response to write the required json object to the output stream      
      PrintWriter out = response.getWriter();
      // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
      out.print(sb);
      out.flush();
    }else if(pathInfo.equals("/downscalings")){
     
    }else{
      response.setStatus(HttpStatus.SC_BAD_REQUEST);
    }
  }
    
  public static Map<String, String> getUserConfigurations(String username){
    try {
      Scanner scanner = null;
      Map<String, String> hashMap = new HashMap<String, String>();
      try {
        String userQueriesFilePath = Configuration.getImpactWorkspace().substring(0, Configuration.getImpactWorkspace().length()-1) + username + "/downscaling/queries";
        scanner = new Scanner(new File(userQueriesFilePath));

        while(scanner.hasNextLine()){
          String line = scanner.nextLine();
          String[] parts = line.split(",");
          hashMap.put(parts[0], parts[1]);
        }
      } catch (FileNotFoundException e) {
      }finally{
        scanner.close();
      }
      return hashMap;
    } catch (Exception e) {
    }
    return null;
  }
  
  public static List<Predictand> getUserPredictands(String username)throws ServletException, IOException, JSONException{
    HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + "/users/" + username + "/predictands", "GET");
    if(urlConn.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
      return null;
    StringBuffer response = new StringBuffer();
    BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    JSONObject myObject = new JSONObject(response.toString());
    JSONArray jsonPredictands = myObject.getJSONArray("values");
    List<Predictand> predictands = new ArrayList<Predictand>();
    for(int i=0; i< jsonPredictands.length(); i++){
      Predictand p = new Predictand.PredictandBuilder(jsonPredictands.getJSONObject(i)).build();
      predictands.add(p);
    }
    return predictands;
  }
  
  public static List<String> getVariableTypes()throws ServletException, IOException, JSONException{
    HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + "/variables/types", "GET");
    if(urlConn.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
      return null;
    StringBuffer response = new StringBuffer();
    BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    JSONObject myObject = new JSONObject(response.toString());
    JSONArray jsonVariableTypes = myObject.getJSONArray("values");
    List<String> variableTypes = new ArrayList<String>();
    for(int i=0; i< jsonVariableTypes.length(); i++){
      variableTypes.add(jsonVariableTypes.getString(i));
    }
    return variableTypes;
  }
  
  public static List<String> getDatasetTypes()throws ServletException, IOException, JSONException{
    HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + "/datasets/types", "GET");
    if(urlConn.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
      return null;
    StringBuffer response = new StringBuffer();
    BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    JSONObject myObject = new JSONObject(response.toString());
    JSONArray jsonVariableTypes = myObject.getJSONArray("values");
    List<String> variableTypes = new ArrayList<String>();
    for(int i=0; i< jsonVariableTypes.length(); i++){
      variableTypes.add(jsonVariableTypes.getString(i));
    }
    return variableTypes;
  }
  
  public static List<String> getDownscalingMethodTypes()throws ServletException, IOException, JSONException{
    HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + "/downscalingMethods/types", "GET");
    if(urlConn.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
      return null;
    StringBuffer response = new StringBuffer();
    BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    JSONObject myObject = new JSONObject(response.toString());
    JSONArray jsonVariableTypes = myObject.getJSONArray("values");
    List<String> variableTypes = new ArrayList<String>();
    for(int i=0; i< jsonVariableTypes.length(); i++){
      variableTypes.add(jsonVariableTypes.getString(i));
    }
    return variableTypes;
  }


  /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String pathInfo = request.getPathInfo();
//	  ImpactUser loggedInUser = LoginManager.getUser(request);
    if(pathInfo.equals("/subscribe")){//Configuration.getImpactWorkspace()
      HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + "/users", "POST");
      urlConn.setRequestProperty("Content-Type", "application/json");
      JSONObject user = new JSONObject();
      try {
        user.put("username", request.getParameter("username"));
        user.put("password", "");
        user.put("email", request.getParameter("email"));
        user.put("openID", "");
      } catch (JSONException e) {
        e.printStackTrace();
      }
      OutputStreamWriter wr= new OutputStreamWriter(urlConn.getOutputStream());
      wr.write(user.toString());
      wr.flush();
      wr.close();
      if(urlConn.getResponseCode() ==HttpURLConnection.HTTP_CREATED){
          Debug.print("201 - User subscribed");  
          response.sendRedirect("../downscaling/downscaling.jsp");
      }else{
          System.out.println(urlConn.getResponseMessage());  
      }  
      
    }else if(pathInfo.equals("/downscalings/downscale")){
      String params = "?username="+request.getParameter("username")+"&idZone="+request.getParameter("idZone")+"&predictandName="+
          request.getParameter("predictandName")+"&dMethodName="+request.getParameter("dMethodName").replace(" ", "%20")+"&scenarioName="+request.getParameter("scenarioName")+
          "&cells="+request.getParameter("cells").replace(" ", "%20");
      HttpURLConnection urlConn = DownscalingAuth.prepareQuery(DownscalingAuth.BASE_DP_REST_URL + "/downscale" + params, "POST");
      response.getWriter().print(urlConn.getResponseMessage());
      response.setStatus(urlConn.getResponseCode());
      
    }else if(pathInfo.equals("/downscalings")){
      
      Map<String, String[]> parameterMap = request.getParameterMap();
      String username = parameterMap.get("username")[0];
      String configName = parameterMap.get("configName")[0]; 
      String userWorkspacePath = Configuration.getImpactWorkspace().substring(0, Configuration.getImpactWorkspace().length()-1) + username + "/downscaling";
      if(!hasConfig(username, configName)){
        File downscalingDirectory = new File(userWorkspacePath);
        if(!downscalingDirectory.exists())
          downscalingDirectory.mkdir();
        userWorkspacePath += "/queries";
        String query =  configName + ",";
        for(String key : parameterMap.keySet()){
          if(!key.equals("username") && !key.equals("configName"))
            query += key+"="+parameterMap.get(key)[0] + "&";
        }
        query = query.substring(0, query.length()-1);
        FileWriter file = null;
        PrintWriter pw = null;
        try
        {
          file = new FileWriter(userWorkspacePath, true);
          pw = new PrintWriter(file);
          pw.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
             if (null != file)
                file.close();
             } catch (Exception e2) {
                e2.printStackTrace();
             }
        }
      }else{
        response.getWriter().print("{'errors':['Existing name'],'success':false}");
        response.setStatus(403);
      }
    }else{
      response.setStatus(HttpStatus.SC_BAD_REQUEST);
    }
	}
	
	private boolean hasConfig(String username, String configName){
	  Map<String, String> userConfigsMap = getUserConfigurations(username);
	  if(userConfigsMap != null)
	    return userConfigsMap.containsKey(configName);
	  return false;
	}
}

