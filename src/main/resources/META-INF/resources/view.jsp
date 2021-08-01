
<%@ include file="/init.jsp" %>
<h2>Welcome to Liferay Message Bus</h2><br/>
<portlet:renderURL var="renderSendMessage">
	<portlet:param name="mvcRenderCommandName" value="/view/create_schedule" />
</portlet:renderURL>

<aui:button href="<%= renderSendMessage %>" value="Create Dynamic Schedule Jobs" />

