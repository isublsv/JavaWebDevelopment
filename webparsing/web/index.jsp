<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
      <title>Index page</title>
  </head>
  <body>
  <label for="file">Choose a xml-document:</label>
  <input type="file"
         id="file" name="file"
         accept="application/xml">

  <form name="parser" action="${pageContext.request.contextPath}/controller" method="post">
      <p>
          <label for="parser">Select xml-parser</label>
          <select id="parser" size="1">
              <option value="DOM">DOM</option>
              <option value="SAX">SAX</option>
              <option value="STAX">StAX</option>
          </select>
      </p>
      <div style="text-align: left">
          <input type="submit" value="Send">
      </div>
  </form>
  </body>
</html>
