<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" import="impactservice.ImpactUser" import="impactservice.LoginManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>

    <jsp:include page="../includes-ext.jsp" />
    <link rel="stylesheet" href="/impactportal/account/login.css" type="text/css" />
    <script type="text/javascript" src="/impactportal/account/js/login.js"></script>
    <%
    	ImpactUser user = null;
    	try{
    		user = LoginManager.getUser(request);
    	}catch(Exception e){
    	}
    	if(user!=null){
    %>
    
    <script type="text/javascript" src="../js/components/processors/wpsOverview.js"></script>
    
    <script type="text/javascript">
    Ext.Loader.setConfig({
      enabled: true
  });

  Ext.QuickTips.init();
  jQuery(document).ready(function($) {
    Ext.onReady(function(){
      var wpsOverViewItems = wpsOverView();
    	var container = Ext.create('Ext.container.Container', {
 	        layout: 'fit',
 			renderTo:'container',
 		   	//minHeight:500,
 		    scripts:true,
 		    autoScroll:false, 
 		    items:[wpsOverViewItems]
    	});
    });
  });
    </script>
    <% } %>
     <script type="text/javascript">
    jQuery(document).ready(function($) {
	    $(".clickable-row").click(function() {
	        window.document.location = $(this).data("href");
	    });
	});
    </script>
  </head>
  <body>
		<jsp:include page="../header.jsp" /> 
		<!-- Contents -->
		<jsp:include page="loginmenu.jsp" />
		<div class="impactcontent"> 
		
		<div class="breadcrumb"><a href="login.jsp">Account</a> » Processing </div>
		
		<%
		if (user==null){
			 %>
			 <h1>Processing - Please sign in ...</h1>
			 	<ul>
			 	<li>Before you can start processing, you need to <a href="#" onclick="generateLoginDialog(true)">sign in</a>.</li>
			 	<!-- <li>See  <a href="/impactportal/documentation/processing.jsp">processing documentation section.</a></li>-->
			 	</ul>
			 	
			 <%
			
		  		
		 	}
		if(user!=null){
		%>
			<h1>Processing wizards</h1>
			<!-- <a href="/impactportal/account/logout.jsp?redirect=%2Fimpactportal%2Faccount%2Fprocessing.jsp%23">logout</a>-->
			<p>Choose a wizard to help you guide through processing, analysis and data extraction options.</p>
			<div class="drupal">
			<table class="drupal"><tr><th>Name</th><th>Description</th></tr>
			<tr class='clickable-row' data-href='wizard_convert.jsp'><td><code class="codejobsicon"></code>&nbsp;Convert and subset</td><td>Extracts a region in space and time, regrids and converts to other formats. Uses the WCS_subsetting WPS in the background.</td></tr>
			<tr class='clickable-row' data-href='wizard_drschecker.jsp'><td><code class="codejobsicon"></code>&nbsp;CLIPC DRS Checker</td><td>Checks files against the CLIPC DRS metadata standard.</td></tr>
			</table>
			</div>
		
			<h1>Web Processing Services</h1>
			<p>Web processing services are processing services are used by the wizard but can be controlled manually.</p>
			<div id="container"></div>
		
		<%} %>
	
		</div>
		<!-- /Contents -->
	  <jsp:include page="../footer.jsp" />
  </body>

</html>
