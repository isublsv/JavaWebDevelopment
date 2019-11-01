<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
      <title>Index page</title>
  </head>
  <body>
  <form name="parser-chooser" action="${pageContext.request.contextPath}/controller" method="post"
        enctype="multipart/form-data">
      <label for="xml-document">Choose a xml-document:</label>
      <input type="file" id="xml-document" name="path" accept="application/xml">
      <br/>
      <input type="hidden" name="command" value="builder"/>
      <p>
          <label for="parser">Select xml-parser</label>
          <select id="parser" size="1" name="key">
              <option value="DOM">DOM</option>
              <option value="SAX">SAX</option>
              <option value="STAX">StAX</option>
          </select>
      </p>
      <br/>
      <div style="text-align: left">
          <input type="submit" value="Send">
      </div>
      <br/>
      ${errorMessage}
      <br/>
      ${wrongCommand}
      <br/>
      ${nullPage}
      <br/>
  </form>
  </body>
</html>
