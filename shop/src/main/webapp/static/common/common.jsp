<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>


<!-----------------------css样式------------------------------------------>
<link href="${ctx}/static/layui/css/layui.css" rel="stylesheet" media="all" >


<!-----------------------js样式-------------------------------------------->
<script src="${ctx}/static/layui/layui.js"></script>
<script src="${ctx}/static/js/jquery-1.7.js"></script>
<script src="${ctx}/static/js/ajaxfileupload.js"  type="text/javascript"></script>
