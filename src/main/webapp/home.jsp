<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout" %>

<layout:extends name="master">
  <layout:put block="content" type="REPLACE">
    <div class="full-screen-home">
      <div class="container-fluid">
        <div id="icon-row" class="row">
          <div class="col-xs-12 text-center">
            <img id="home-icon" src="/img/laundry-logo.png"/>
          </div>
        </div>
        <div id="view-orders-row" class="row">
          <div class="col-xs-12 text-center">
            <a href="/orders"><p class="btn" id="view-orders-button">VIEW ORDERS</p></a>
          </div>
          </div>
      </div>
    </div>
  </layout:put>
</layout:extends>
